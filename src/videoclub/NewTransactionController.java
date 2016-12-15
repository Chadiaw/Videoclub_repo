/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import videoclub.model.CatalogueProduits;
import videoclub.model.HistoriqueTransactions;
import videoclub.model.LigneArticle;
import videoclub.model.LigneLocation;
import videoclub.model.Transaction;

/**
 * FXML Controller class
 *
 * @author cheik
 */
public class NewTransactionController implements Initializable {
    
    /*
        Éléments de l'interface
    */
    @FXML
    private Button boutonPaiement;
    @FXML
    private Button boutonAnnuler;
    @FXML
    private Button boutonLocation;
    @FXML
    private Button boutonVente; 
    @FXML
    private Label totalTransaction;
    @FXML
    private Label sousTotalLocation; 
    @FXML
    private Label sousTotalVente;
    @FXML
    private Label locationLabel;
    @FXML
    private Label venteLabel;
    @FXML
    private Label adherentLabel;
    
    /*
        Éléments de la table de location
    */
    @FXML
    private TableView<TableLocationItem> tableLocation = new TableView<TableLocationItem>();
    @FXML
    private TableColumn filmCol;
    @FXML
    private TableColumn dateRetourCol;
    private ObservableList<TableLocationItem> itemsTableLocation = FXCollections.observableArrayList();
    
    /*
     Éléments de la table de vente
    */
    @FXML
    private TableView<TableVenteItem> tableVente = new TableView<TableVenteItem>();
    @FXML
    private TableColumn articleCol;
    @FXML
    private TableColumn quantiteCol;
    private ObservableList<TableVenteItem> itemsTableVente = FXCollections.observableArrayList();
    
    // Instance de l'application
    private Videoclub application;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.application = Videoclub.getInstance();

        // Populate TableLocation
        filmCol.setCellValueFactory(new PropertyValueFactory<TableLocationItem, String>("film"));
        dateRetourCol.setCellValueFactory(new PropertyValueFactory<TableLocationItem, String>("dateRetour"));
        tableLocation.setItems(itemsTableLocation);
        
        // Populate TableVente
        articleCol.setCellValueFactory(new PropertyValueFactory<TableVenteItem,String>("article"));
        quantiteCol.setCellValueFactory(new PropertyValueFactory<TableVenteItem, Integer>("quantite"));
        tableVente.setItems(itemsTableVente);
        
        // MaJ les éléments de l'interface en fonction de l'état de l'application
        updateAll();
        
    }    
    
    /**
     *  Ajout d'une location à la transaction. Ouverture de l'interface 'Identifier adhérent'.
     */
    @FXML
    public void actionAjoutLocation(ActionEvent event) {
         // Ouvrir l'interface de Nouvelle location
        try {
            // Fermer vue actuelle
            Stage stage = (Stage) boutonLocation.getScene().getWindow();
            stage.close();
            
            // Ouvrir vue 'Identifier adhérent'
            application.getViewManager().openView("identifierAdherent.fxml", "Identifier adhérent", StageStyle.UTILITY );
         
        }
        catch (Exception ex) {
            Logger.getLogger(NewTransactionController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    /**
     *  Ajout d'une vente à la transaction. Ouvre l'interface 'Nouvelle vente'.
     */
    @FXML
    public void actionAjoutVente(ActionEvent event){
        //Ouvrir l'interface Nouvelle vente
        try{
            // Fermer vue actuelle
            Stage stage = (Stage) boutonVente.getScene().getWindow();
            stage.close();
            
            // Ouvvrir vue 'Vente'
            application.getViewManager().openView("fenetreVente.fxml", "Vente", StageStyle.UNDECORATED );
    
        }
        catch(Exception ex){
            Logger.getLogger(NewTransactionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *  Annuler transaction. La transaction en cours est réinitialisée (null).
     */
    @FXML
    public void actionAnnulerTransaction(ActionEvent event) {
        
        application.setTransactionEnCours(null);
        // Recuperer la fenêtre (stage) parente
        Stage stage = (Stage) boutonAnnuler.getScene().getWindow();
        // Fermer fenêtre
        stage.close();
        
        try {
            application.getViewManager().openView("MainView.fxml", "Vidéoclub", StageStyle.DECORATED);
        } catch (IOException ex) {
            Logger.getLogger(NewTransactionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
     public void actionPaiement(ActionEvent event) {
        // Ouvrir l'interface de Paiement
        try {
            Stage stage = (Stage) boutonPaiement.getScene().getWindow();
            stage.close();
            
            application.getViewManager().openView("fenetrePaiement.fxml", "Paiement", StageStyle.UTILITY);        
        }
        catch (Exception ex) {
            Logger.getLogger(NewTransactionController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    /**
     * Mise à jour des éléments de l'interface selon l'état de l'application
     */
    private void updateAll() {
       if (application.getTransactionEnCours() == null) {
           // S'il n'y a pas de transaction en cours, en créer une lorsque cette interface est ouverte.
            application.setTransactionEnCours(new Transaction(HistoriqueTransactions.getInstance().getTransactionsIndex()));
        }
               
        if(application.getTransactionEnCours().getLocation() == null) {
            // Cacher table et afficher bouton 'Ajout location'
            boutonLocation.setVisible(true);
            tableLocation.setVisible(false);
            sousTotalLocation.setText("Sous-total : 0.00 $");
        }
        else {
            // Cacher bouton et afficher table (location déjà définie)
            boutonLocation.setVisible(false);
            tableLocation.setVisible(true);
            String locationTotal = application.getTransactionEnCours().getLocation().getTotalFormatted();                    
            sousTotalLocation.setText(String.format("Sous-total: %s $", locationTotal));
            
            // Remplir les éléments de la table à partir de la location définie.
            itemsTableLocation.clear();
            for (LigneLocation ligneLocation : application.getTransactionEnCours().getLocation().getLignesLocation()) {
                itemsTableLocation.add(new TableLocationItem((ligneLocation)));
            }
            
        }
        
        if(application.getTransactionEnCours().getVente() == null) {
            // Cacher table et afficher bouton 'Ajouter vente'
            boutonVente.setVisible(true);
            tableVente.setVisible(false);
            sousTotalVente.setText("Sous-total: 0.00 $");
        }
        else {
            // Afficher table et cacher bouton (vente déjà définie)
            boutonVente.setVisible(false);
            tableVente.setVisible(true);
            String venteTotal = application.getTransactionEnCours().getVente().getTotalFormatted();
            sousTotalVente.setText(String.format("Sous-total : %s $", venteTotal));
            
            // Remplir les éléments de la table à partir de la vente définie.
            itemsTableVente.clear();
            for (LigneArticle ligneArticle: application.getTransactionEnCours().getVente().getLignesArticles()) {
                itemsTableVente.add(new TableVenteItem(ligneArticle));
            }
        }
        
        // MaJ du champ adhéremt.
        if(application.getTransactionEnCours().getAdherent() == null) {
            adherentLabel.setText("");
        }
        else {
            adherentLabel.setText("Adhérent : " + application.getTransactionEnCours().getAdherent().getNom());
        }
        
        // MaJ champ 'Total' de la transaction
        totalTransaction.setText(String.format("Total : %s $" , application.getTransactionEnCours().getTotalFormatted()));
    }

    /**
     * Objet définissant le contenu de la TableVente. 
     */
    protected class TableVenteItem {

        private String article;
        private int quantite;
        
        public String getArticle() {
            return this.article;
        }
        
        public int getQuantite() {
            return this.quantite;
        }
        
        public TableVenteItem(LigneArticle ligneArticle) {
            String codeArticle = ligneArticle.getCodeArticle();
            String descriptif = CatalogueProduits.getInstance().getArticle(codeArticle).getDescriptif();
            
            // La première colonne contient le code article ainsi que le descriptif de l'article.
            this.article = String.format("#%1$s : %2$s ", codeArticle, descriptif);
            
            // Deuxième colonne : quantité vendue pour l'article en question 
            this.quantite = ligneArticle.getQuantite();
            
        }
    }

    /**
     * Objet définissant le contenu de la TableLocations
     */
    protected class TableLocationItem {
        
        private String film;
        private String dateRetour;

        public String getFilm() {
            return film;
        }

        public String getDateRetour() {
            return dateRetour;
        }
        
        public TableLocationItem(LigneLocation ligneLocation) {
            String codeFilm = ligneLocation.getCodeArticle();
            String titreFilm = CatalogueProduits.getInstance().getFilmByCode(codeFilm).getTitre();
            
            
            // 1ere colonne : " CodeFilm#Exemplaire : TireFilm"
            this.film = String.format("%1$s | %2$s", codeFilm, titreFilm);
            
            // 2e colonne : Date de retour sous le format yy/MM/dd (plus facile à trier)
            this.dateRetour = ligneLocation.getDateRetour().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        }
    }

    
}
