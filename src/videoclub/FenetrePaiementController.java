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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import videoclub.model.Paiement;

/**
 * FXML Controller class
 *
 * @author Melanie
 */
public class FenetrePaiementController implements Initializable {

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
        totalLabel.setText("Total: " + paiement.getTotalFormatted() + "$");
    }    

    @FXML
    private void actionConfirmer(ActionEvent event) {
        paiement.setArgentTendu(Double.parseDouble(ArgentTenduField.getText()));
        monnaieLabel.setText("Monnaie rendue: " + paiement.getMonnaieFormatted() + "$");
    }

    @FXML
    private void actionTerminer(ActionEvent event) {
        //enregistrer paiement
        application.getTransactionEnCours().setPaiement(paiement);
        //fermer fenetre Paiement
        Stage stage = (Stage) boutonTerminer.getScene().getWindow();
        stage.close();
        //fermer fenetreTransaction, enregistrer transaction
        //TODO
    }

    @FXML
    private void actionAnnulerPaiement(ActionEvent event) {
        // Recuperer la fenêtre (stage) parente
        Stage stage = (Stage) boutonAnnuler.getScene().getWindow();
        // Fermer fenêtre
        stage.close();
    }
    
}
