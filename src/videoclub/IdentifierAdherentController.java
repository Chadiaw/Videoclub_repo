/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
    
    private Videoclub application;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        application = Videoclub.getInstance();
        
    }    
    
    public void actionAnnuler(ActionEvent event) {
        Stage stage = (Stage) boutonAnnuler.getScene().getWindow();
        // Fermer fenêtre
        stage.close();
    }
    
    public void actionIdentifier(ActionEvent event) {
        // Vérifier que le client est dans la liste 
        String numeroSaisi = numeroAdherent.getText();
        String codeSecretSaisi = codeSecretAdherent.getText();
        
        
        for (Adherent adherent:application.getListeAdherents()) {
            if(adherent.getNumeroTelephone().equals(numeroSaisi)
                    && adherent.getCodeSecret().equals(codeSecretSaisi)) {
                
            }
        }
        
        // Client est dans la liste, ouvrir fenetre location
    }
    
}
