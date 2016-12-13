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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import videoclub.model.CatalogueProduits;
import videoclub.model.HistoriqueTransactions;
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

        double montantTendu = 0;

        try {
            montantTendu = Double.parseDouble(ArgentTenduField.getText());
            if (montantTendu <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);;
            alert.setHeaderText(null);
            alert.setContentText("Montant tendu incorrect.");
            alert.showAndWait();
            return;
        }

        if (montantTendu < paiement.getTotal()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);;
            alert.setHeaderText(null);
            alert.setContentText("Le montant tendu doit être supérieur ou égal au total.");
            alert.showAndWait();
            return;
        } else {
            paiement.setArgentTendu(montantTendu);
            monnaieLabel.setText("Monnaie rendue: " + paiement.getMontantFormatted(paiement.getMonnaie()) + "$");
            paiement.setComplete(true);
        }
    }

    @FXML
    private void actionTerminer(ActionEvent event) {
        //si le paiement n'a pas ete complete
        if (!paiement.getComplete()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);;
            alert.setHeaderText(null);
            alert.setContentText("La facture n'a pas été réglée.");
            alert.showAndWait();
            return;
        } else {
            //enregistrer paiement
            application.getTransactionEnCours().setPaiement(paiement);

            application.getHistoriqueSession().ajouterTransaction(application.getTransactionEnCours());
            
            HistoriqueTransactions.getInstance().enregistrer(application.getTransactionEnCours());
            
            application.setTransactionEnCours(null);
            
            //fermer fenetre Paiement
            Stage stage = (Stage) boutonTerminer.getScene().getWindow();
            stage.close();
            
            ;
        }
    }

    @FXML
    private void actionAnnulerPaiement(ActionEvent event) {

        application.getTransactionEnCours().setPaiement(null);

        // Recuperer la fenêtre (stage) parente
        Stage stage = (Stage) boutonAnnuler.getScene().getWindow();
        // Fermer fenêtre
        stage.close();

        try {
            application.getViewManager().openView("newTransaction.fxml", "Nouvelle transaction", StageStyle.UTILITY);
        } catch (IOException ex) {
            Logger.getLogger(FenetrePaiementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
