/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import videoclub.model.Transaction;

/**
 * FXML Controller class
 *
 * @author cheik
 */
public class NewTransactionController implements Initializable {

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
    @FXML
    private TableView tableLocation;
    @FXML
    private TableView tableVente;
    
    private Videoclub application;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.application = Videoclub.getInstance();
        if (application.getTransactionEnCours() == null) {
            application.setTransactionEnCours(new Transaction());
        }
        sousTotalLocation.setText("Sous-total : 0.00 $");
        sousTotalVente.setText("Sous-total: 0.00 $");
        
        if(application.getTransactionEnCours().getLocation() == null) {
            boutonLocation.setVisible(true);
            tableLocation.setVisible(false);
        }
        else {
            boutonLocation.setVisible(false);
            tableLocation.setVisible(true);
        }
        
        if(application.getTransactionEnCours().getVente() == null) {
            boutonVente.setVisible(true);
            tableVente.setVisible(false);
        }
        else {
            boutonVente.setVisible(false);
            tableVente.setVisible(true);
        }
        
        if(application.getTransactionEnCours().getAdherent() == null) {
            adherentLabel.setText("");
        }
        else {
            adherentLabel.setText("Adhérent : " + application.getTransactionEnCours().getAdherent().getNom());
        }
        
        totalTransaction.setText("Total : 0.00$");
        
    }    
    
    public void actionAjoutLocation(ActionEvent event) {
         // Ouvrir l'interface de Nouvelle location
        try {
            // Fermer vue actuelle
            Stage stage = (Stage) boutonLocation.getScene().getWindow();
            stage.close();
            
            // Ouvrir vue 'Identifier adhérent'
            application.getViewManager().openView("identifierAdherent.fxml", "Identifier adhérent", StageStyle.UNDECORATED );
         
        }
        catch (Exception ex) {
            Logger.getLogger(NewTransactionController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
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
    
    public void actionAnnulerTransaction(ActionEvent event) {
        
        application.setTransactionEnCours(null);
        // Recuperer la fenêtre (stage) parente
        Stage stage = (Stage) boutonAnnuler.getScene().getWindow();
        // Fermer fenêtre
        stage.close();
        
        
    }

    
}
