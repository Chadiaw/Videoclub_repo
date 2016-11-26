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

/**
 * FXML Controller class
 *
 * @author cheik
 */
public class OngletAccueilController implements Initializable {

    @FXML
    private Label historiqueLabel;
    @FXML
    private TableView tableHistorique;
    @FXML
    private Button boutonTransaction;
    @FXML
    private Button boutonRetournerFilm;
    @FXML 
    private Button boutonInscrireAdherent;
    
    
    private Videoclub application;
    
    
    public void setApp(Videoclub application){
        this.application = application;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void actionTransaction(ActionEvent event) {
        // Ouvrir l'interface de Nouvelle transaction
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("newTransaction.fxml"));
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Nouvelle transaction");
            stage.setScene(new Scene(root1));  
            stage.show();
        }
        catch (Exception ex) {
            Logger.getLogger(OngletAccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public void actionInscrireAdherent(ActionEvent event) {
        // Ouvrir l'interface de Nouvel adhérent
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("nouvelAdherent.fxml"));
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Ajouter adhérent");
            stage.setScene(new Scene(root1));  
            stage.show();
        }
        catch (Exception ex) {
            Logger.getLogger(OngletAccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    
}
