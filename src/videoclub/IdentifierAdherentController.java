/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import videoclub.model.Adherent;

/**
 * FXML Controller class
 *
 * @author cheikh
 */
public class IdentifierAdherentController implements Initializable {

    @FXML
    private TextField numeroAdherent;
    @FXML
    private TextField codeSecretAdherent;
    @FXML
    private Button boutonIdentifier;
    @FXML
    private Button boutonAnnuler;
    @FXML
    private Label messageErreur;

    private Videoclub application;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        application = Videoclub.getInstance();
        messageErreur.setText("");

    }

    public void actionAnnuler(ActionEvent event) {
        Stage stage = (Stage) boutonAnnuler.getScene().getWindow();
        // Fermer fenêtre
        stage.close();

        try {
            // Ouvrir vue 'Transaction
            application.getViewManager().openView("newTransaction.fxml", "Nouvelle transaction", StageStyle.UTILITY);
        } catch (IOException ex) {
            Logger.getLogger(IdentifierAdherentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actionIdentifier(ActionEvent event) {
        // Vérifier que le client est dans la liste 
        String numeroSaisi = numeroAdherent.getText();
        int codeSecretSaisi = 0;

        try {
            codeSecretSaisi = Integer.parseInt(codeSecretAdherent.getText());
        } catch (NumberFormatException ex) {
            messageErreur.setText("Format code invalide.");
            return;
        }
        boolean estDansListe = false;
        Adherent adherentIdentifie = null;

        for (Adherent adherent : application.getListeAdherents()) {
            if ((adherent.getNumeroTelephone().equals(numeroSaisi)
                    || adherent.getNumeroTelephone().replaceAll("\\D", "").equals(numeroSaisi))
                    && adherent.getCodeSecret() == (codeSecretSaisi)) {

                adherentIdentifie = adherent;
                estDansListe = true;
                break;
            }
        }

        // Client est dans la liste, ouvrir fenetre location
        if (estDansListe) {
            try {
                // Si solde > 0 afficher alerte et refuser location.
                if (adherentIdentifie.getSolde() > 0) {
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Payer amende");
                    alert.setHeaderText("Solde impayé");
                    alert.setContentText("Le solde de l'adhérent sera ajouté au total de la location. Continuer ?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        // ... Utilisateur a confirmé.
                        application.getTransactionEnCours().setAdherent(adherentIdentifie);
                        Stage stage = (Stage) boutonAnnuler.getScene().getWindow();
                        stage.close();

                        // Ouvrir vue 'Location'
                        application.getViewManager().openView("fenetreLocation.fxml", "Nouvelle location", StageStyle.UNDECORATED);
                    } else {
                        // ... Utilisateur a quitté la fenêtre ou cliqué sur 'Cancel'
                        messageErreur.setText("Solde impayé.");
                        return;
                    }
                }

            } catch (Exception ex) {
                Logger.getLogger(IdentifierAdherentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            messageErreur.setText("Adhérent non trouvé.");
        }
    }

}
