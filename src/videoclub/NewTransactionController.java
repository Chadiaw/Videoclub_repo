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
        sousTotalLocation.setText("Sous-total : 0.00 $");
        sousTotalVente.setText("Sous-total: 0.00 $");
        adherentLabel.setText("");
        totalTransaction.setText("Total : 0.00$");
    }    
    
    public void actionAjoutLocation(ActionEvent event) {
         // Ouvrir l'interface de Nouvelle location
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("identifierAdherent.fxml"));
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Identifier adhérent");
            stage.setScene(new Scene(root1));  
            stage.show();
        }
        catch (Exception ex) {
            Logger.getLogger(NewTransactionController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public void actionAnnulerTransaction(ActionEvent event) {
        // Recuperer la fenêtre (stage) parente
        Stage stage = (Stage) boutonAnnuler.getScene().getWindow();
        // Fermer fenêtre
        stage.close();
    }
    
}
