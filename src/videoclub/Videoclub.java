/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub;

import java.io.InputStream;
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
    
    public  Videoclub() {
        super();
        synchronized(Videoclub.class){
        if(instance != null) throw new UnsupportedOperationException(
                getClass()+" is singleton but constructor called more than once");
        instance = this;
    }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(Videoclub.class, (java.lang.String[])null);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;
            stage.setTitle("Vidéoclub");
            //stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
            //stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
            gotoLogin();
            primaryStage.show();
            listeEmployes = DatabaseManager.chargerEmployes();
            if(listeEmployes.isEmpty())
                throw new Exception("Liste d'employes vide");
        } catch (Exception ex) {
            Logger.getLogger(Videoclub.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    
    public Employe getEmployeConnecte() {
        return employeConnecte;
    }
    
    public boolean connexionEmploye(String username, String password){
        if (LoginManager.valider(username, password)) {
            // Mettre a jour employe connecte
            for(Employe e:listeEmployes) {
                if (e.getUsername().equals(username))
                    employeConnecte = e;
            }
            chargerDonnees();
            goToMainView();
            return true;
        } else {
            return false;
        }
    }
    
    void deconnexion(){
        employeConnecte = null;
        
        // Sauvegarder tous les objets dans base de donnees
        //DatabaseManager.saveAll();
        
        // Désallouer mémoire si nécessaire ici
        
        
        
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
        
        // Charger articles dans onglet Inventaire
        
        // Charger catalogue
        CatalogueProduits.getInstance().setListeArticles(DatabaseManager.chargerArticles());
        CatalogueProduits.getInstance().setListeFilms(DatabaseManager.chargerFilms());
    }
    
    public ObservableList<Adherent> getListeAdherents() {
        return listeAdherents;
    }
   
}
