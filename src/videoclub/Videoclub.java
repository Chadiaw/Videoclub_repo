/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub;

import java.io.InputStream;
import static java.lang.Math.ceil;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import videoclub.model.Adherent;
import videoclub.model.CatalogueProduits;
import videoclub.model.DatabaseManager;
import videoclub.model.Employe;
import videoclub.model.LigneLocation;
import videoclub.model.LogEntry;
import videoclub.model.LogLocations;
import videoclub.model.LogVideoclub;
import videoclub.model.Transaction;
import videoclub.securite.LoginManager;

/**
 *
 * @author cheik
 */
public class Videoclub extends Application {

    private Stage stage;
    private Employe employeConnecte;
    private ArrayList<Employe> listeEmployes;
    private ObservableList<Adherent> listeAdherents = FXCollections.observableArrayList();
    private Transaction transactionEnCours = null;
    private ViewManager viewManager = new ViewManager();

    // Log
    private HistoriqueSession historiqueSession = new HistoriqueSession();
    private LogVideoclub logVideoclub = new LogVideoclub();
    private LogLocations logLocations = new LogLocations();

    private ObservableList<LogEntry> articlesAjoutes = FXCollections.observableArrayList();

    public ObservableList<LogEntry> getArticlesAjoutes() {
        return articlesAjoutes;
    }

    public HistoriqueSession getHistoriqueSession() {
        return historiqueSession;
    }

    public LogVideoclub getLogVideoclub() {
        return logVideoclub;
    }

    public LogLocations getLogLocations() {
        return logLocations;
    }

    public ViewManager getViewManager() {
        return this.viewManager;
    }

    public void setTransactionEnCours(Transaction transaction) {
        this.transactionEnCours = transaction;
    }

    public Transaction getTransactionEnCours() {
        return transactionEnCours;
    }

    // Instance unique de l'application (Videoclub)
    private static Videoclub instance;

    public static Videoclub getInstance() {
        return instance;
    }

    public Videoclub() {
        super();
        synchronized (Videoclub.class) {
            if (instance != null) {
                throw new UnsupportedOperationException(
                        getClass() + " is singleton but constructor called more than once");
            }
            instance = this;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(Videoclub.class, (java.lang.String[]) null);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;
            stage.setTitle("Vidéoclub");
            gotoLogin();
            primaryStage.show();

            // Liste des employes chargée séparément car nécessaire pour la connexion
            listeEmployes = DatabaseManager.chargerEmployes();
            if (listeEmployes.isEmpty()) {
                throw new Exception("Liste d'employes vide");
            }
        } catch (Exception ex) {
            Logger.getLogger(Videoclub.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void stop() throws Exception {
        // Méthode appelée à la fermeture de l'application, il faut sauvegarder toutes données
        sauvegarderDonnees();

    }

    public Employe getEmployeConnecte() {
        return employeConnecte;
    }

    public boolean connexionEmploye(String username, String password) {
        if (LoginManager.valider(username, password)) {
            // Mettre a jour employe connecte
            for (Employe e : listeEmployes) {
                if (e.getUsername().equals(username)) {
                    employeConnecte = e;
                }
            }
            chargerDonnees();
            transactionEnCours = null;

            goToMainView();
            return true;
        } else {
            return false;
        }
    }

    void deconnexion() {
        employeConnecte = null;
        sauvegarderDonnees();
        gotoLogin();
    }

    /**
     * Aller à l'écran de connexion
     */
    private void gotoLogin() {
        try {
            LoginController login = (LoginController) changerScene("Login.fxml");
        } catch (Exception ex) {
            Logger.getLogger(Videoclub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Aller à l'interface principale.
     */
    private void goToMainView() {
        try {
            MainViewController mainView = (MainViewController) changerScene("MainView.fxml");
        } catch (Exception ex) {
            Logger.getLogger(Videoclub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Changer de scene JavaFX.
     *
     * @param fxml Fichier .fxml à charger
     * @return le contrôleur associé au fichier fxml
     * @throws Exception
     */
    private Initializable changerScene(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Videoclub.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Videoclub.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }

    private void chargerDonnees() {
        // Charger liste employes
        listeAdherents = DatabaseManager.chargerAdherents();

        // Charger catalogue
        CatalogueProduits.getInstance().setListeArticles(DatabaseManager.chargerArticles());
        CatalogueProduits.getInstance().setListeFilms(DatabaseManager.chargerFilms());

        // Charger log videoclub
        logVideoclub = DatabaseManager.chargerLog();

        // Charger historique locations
        logLocations.setLocationsEnCours(DatabaseManager.chargerLocations());

        // MaJ solde adhérents et locations courantes pour chaque adhérent
        for (LigneLocation ligne : logLocations.getLocationsEnCours()) {
            for (Adherent adherent : listeAdherents) {
                if (adherent.getNom().equals(ligne.getNomAdherent())) {
                    adherent.getLocationsCourantes().add(ligne);

                    if (!logVideoclub.getSoldeUpdated()) {
                        int joursRetard = (int) DAYS.between(ligne.getDateRetour(), LocalDate.now());
                        if (CatalogueProduits.getInstance().getFilmByCode(ligne.getCodeFilm()).isNouveaute()) {
                            // Si nouveauté, retard calculé à la journée
                            double amende = joursRetard * CatalogueProduits.getInstance().CoutJourDeRetard;
                            adherent.setSolde(adherent.getSolde() + amende);
                        } else {
                            // Sinon, retard calculé à la semaine
                            int nombreSemaines = (int) ceil(joursRetard / 7);
                            double amende = nombreSemaines * CatalogueProduits.getInstance().CoutSemaineDeRetard;
                            adherent.setSolde(adherent.getSolde() + amende);
                        }
                    }
                }
            }

            logVideoclub.updateRetards(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm")));
            logVideoclub.setSoldeUpdated(true);
        }

    }

    private void sauvegarderDonnees() {
        // Sauvegarde adhérents dans table ADHERENTS
        DatabaseManager.sauvegarderAdherents(listeAdherents);

        // Sauvegarde logLocations dans LOCATIONS
        DatabaseManager.sauvegarderLocations(logLocations.getLocationsEnCours());

        // Sauvegarde table FILM et ARTICLE, s'il y a eu changement
        if (CatalogueProduits.getInstance().isArticleDirty()) {
            DatabaseManager.sauvegarderArticles(CatalogueProduits.getInstance());
        }
        if (CatalogueProduits.getInstance().isFilmDirty()) {
            DatabaseManager.sauvegarderFilms(CatalogueProduits.getInstance());
        }

        // Sauvegarde du log videoclub
        DatabaseManager.sauvegarderLogVideoclub(logVideoclub);
    }

    public ObservableList<Adherent> getListeAdherents() {
        return listeAdherents;
    }

}
