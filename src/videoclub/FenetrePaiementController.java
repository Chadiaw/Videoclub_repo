/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import videoclub.model.CatalogueProduits;
import videoclub.model.Paiement;

/**
 * FXML Controller class
 *
 * @author Melanie
 */
public class FenetrePaiementController implements Initializable {

    @FXML
    private Label sousTotalLabel;
    @FXML
    private Label TVQLabel;
    @FXML
    private Label TPSLabel;
    @FXML
    private Label Total;
    @FXML
    private Label totalLabel;
    @FXML
    private Label ArgentTenduLabel;
    @FXML
    private Button boutonConfirmer;
    @FXML
    private TextField ArgentTenduField;
    @FXML
    private Label monnaieLabel;
    @FXML
    private Button boutonTerminer;
    @FXML
    private Button boutonAnnuler;
    private Paiement paiement;
    private Videoclub application;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.application = Videoclub.getInstance();
        this.paiement = new Paiement(application.getTransactionEnCours().getTotal());
        
        sousTotalLabel.setText("Sous total: " + paiement.getMontantFormatted(paiement.getSousTotal()) + "$");
        TVQLabel.setText("TVQ: " + paiement.getMontantFormatted(paiement.getTVQ()) + "$");
        TPSLabel.setText("TPS: " + paiement.getMontantFormatted(paiement.getTPS()) + "$");
        totalLabel.setText("Total: " + paiement.getMontantFormatted(paiement.calculerTotal()) + "$");
    }

    @FXML
    private void actionConfirmer(ActionEvent event) {
        if(Double.parseDouble(ArgentTenduField.getText())< paiement.getTotal()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);;
            alert.setHeaderText(null);
            alert.setContentText("Le montant tendu doit être égal ou supérieur au total.");
            alert.showAndWait();
            return;
        }else{
            paiement.setArgentTendu(Double.parseDouble(ArgentTenduField.getText()));
            monnaieLabel.setText("Monnaie rendue: " + paiement.getMontantFormatted(paiement.getMonnaie()) + "$");
            paiement.setComplete(true);
        }
    }

    @FXML
    private void actionTerminer(ActionEvent event) {
        //si le paiement n'a pas ete complete
        if(!paiement.getComplete()){
          Alert alert = new Alert(Alert.AlertType.ERROR);;
            alert.setHeaderText(null);
            alert.setContentText("La facture n'a pas été réglée.");
            alert.showAndWait();
            return;  
        }else{
        //enregistrer paiement
        application.getTransactionEnCours().setPaiement(paiement);
        //fermer fenetre Paiement
        Stage stage = (Stage) boutonTerminer.getScene().getWindow();
        stage.close();
        //fermer fenetreTransaction, enregistrer transaction
        //TODO
        }
    }

    @FXML
    private void actionAnnulerPaiement(ActionEvent event) {
        // Recuperer la fenêtre (stage) parente
        Stage stage = (Stage) boutonAnnuler.getScene().getWindow();
        // Fermer fenêtre
        stage.close();
    }
    
}
