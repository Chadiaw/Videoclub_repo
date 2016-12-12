/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import videoclub.model.CatalogueProduits;
import videoclub.model.Film;
import videoclub.model.LigneLocation;
import videoclub.model.Location;

/**
 * FXML Controller class
 *
 * @author cheik
 */
public class FenetreLocationController implements Initializable {

    @FXML
    private Label ajoutManuelLabel;
    @FXML
    private TextField titreFilmField;
    @FXML
    private TextField codeFilmField;
    @FXML
    private TextField dureeLocationField;
    @FXML
    private Label messageErreur;
    @FXML
    private Button boutonAjouter;
    @FXML
    private Button boutonScan;
    
    @FXML
    private TableView<TableLocationItem> tableLocation = new TableView<TableLocationItem>();
    @FXML 
    private TableColumn codeCol;
    @FXML 
    private TableColumn titreCol;
    @FXML 
    private TableColumn dureeCol;
    @FXML 
    private TableColumn coutCol;
    
    @FXML
    private Label totalLocation;
    @FXML
    private Label labelAdherent;
    @FXML
    private Button boutonAnnuler;
    @FXML
    private Button boutonConfirmer;
    
    private Videoclub application;
    
    private Location location;
    private ObservableList<TableLocationItem> items = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        application = Videoclub.getInstance();
        titreFilmField.setPromptText("Titre film...");
        codeFilmField.setPromptText("Code film...");
        dureeLocationField.setPromptText("jours");
        messageErreur.setText("");
        String labelMsg = String.format("Adhérent : %s", application.getTransactionEnCours().getAdherent().getNom());
        labelAdherent.setText(labelMsg);
        
        location = new Location(); 
        location.setTotal(application.getTransactionEnCours().getAdherent().getSolde());
        
        totalLocation.setText(String.format("Total location : %s", location.getTotalFormatted()));
        
        codeCol.setCellValueFactory(new PropertyValueFactory<TableLocationItem,String>("code"));
        titreCol.setCellValueFactory(new PropertyValueFactory<TableLocationItem, String>("titre"));
        dureeCol.setCellValueFactory(new PropertyValueFactory<TableLocationItem, Integer>("duree"));
        coutCol.setCellValueFactory(new PropertyValueFactory<TableLocationItem, Double>("cout"));
        
        tableLocation.setItems(items);
    }    
    
    @FXML
    private void actionAjouter(ActionEvent event){
        String titreSaisi = titreFilmField.getText();
        String codeSaisi = codeFilmField.getText();
        
        Film filmEntre = CatalogueProduits.getInstance().getFilmByTitre(titreSaisi);
        
        if (filmEntre == null) {
            filmEntre = CatalogueProduits.getInstance().getFilmByCode(codeSaisi);
        }
        
        if( filmEntre == null) {
            messageErreur.setText("Aucun film correspondant.");
            return;
        }
        if(filmEntre.isAchetable()){
            messageErreur.setText("Ce film n'est pas disponible pour la location.");
            return;
        }
        
        String dureeSaisie = dureeLocationField.getText();
        int duree = 0;
        try {
            duree = Integer.parseInt(dureeSaisie);
            if (duree <= 0)
                throw new NumberFormatException();
        }
        catch (NumberFormatException ex) {
            
            messageErreur.setText("Durée invalide.");
           return;
        }
        
        LigneLocation newLigne = new LigneLocation(filmEntre.getCodeArticle(), duree);
        location.ajouterLigneLocation(newLigne);
        totalLocation.setText("Total location : " + location.getTotalFormatted() + "$");
        
        items.add(new TableLocationItem(newLigne));
    }
    
    public void actionAnnuler(ActionEvent event) {
        // Recuperer la fenêtre (stage) parente
        Stage stage = (Stage) boutonAnnuler.getScene().getWindow();
        // Fermer fenêtre
        stage.close();
        
        try {
            application.getViewManager().openView("newTransaction.fxml", "Nouvelle transaction", StageStyle.UTILITY);
        } catch (IOException ex) {
            Logger.getLogger(FenetreVenteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void actionConfirmer(ActionEvent event) {
        // Mettre a jour la transaction courante
        if(location.getLignesLocation().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("La location est vide !");
            
            alert.showAndWait();
            return;
            
        }
        application.getTransactionEnCours().setLocation(location);
       
        // Fermer fenetre
        Stage stage = (Stage) boutonConfirmer.getScene().getWindow();
        stage.close();
        
        try {
            application.getViewManager().openView("newTransaction.fxml", "Nouvelle transaction", StageStyle.UTILITY);
        }
        catch (Exception ex) {
            Logger.getLogger(FenetreLocationController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }

    protected class TableLocationItem {
        
        /*
         Définit le format d'une ligne de la table location
        */
        private String code;
        private String titre;
        private int duree;
        private double cout; 

        public TableLocationItem(LigneLocation newLigne) {
           this.code = newLigne.getCodeArticle();
           this.titre = CatalogueProduits.getInstance().getFilmByCode(code).getTitre();
           this.duree = newLigne.getDuree();
           this.cout = newLigne.getSousTotal();
        }

        public String getCode() {
            return code;
        }

        public String getTitre() {
            return titre;
        }

        public int getDuree() {
            return duree;
        }

        public double getCout() {
            return cout;
        }
        
        
    }
    
}
