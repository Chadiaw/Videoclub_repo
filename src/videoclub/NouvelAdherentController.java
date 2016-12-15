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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import videoclub.model.Adherent;

/**
 * FXML Controller class
 *
 * @author cheikh
 */
public class NouvelAdherentController implements Initializable {

    @FXML
    private TextField nomAdherent;
    @FXML
    private TextField telephoneAdherent;
    @FXML
    private TextArea adresseAdherent;
    @FXML
    private Button boutonEnregistrer;
    @FXML 
    private Button boutonAnnuler;
    @FXML
    private PasswordField codeSecretAdherent;
    
    private Videoclub application;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.application = Videoclub.getInstance();
    }    
    
    public void actionEnregistrer(ActionEvent event) {
        if(codeSecretAdherent.getText().isEmpty() || nomAdherent.getText().isEmpty() 
                || telephoneAdherent.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Tous les champs obligatoires n'ont pas été remplis.");
            
            alert.showAndWait();
            return;
        }
        int codeSaisi = 0;
        try {
            codeSaisi = Integer.parseInt(codeSecretAdherent.getText());
        }
        catch(NumberFormatException ex) {
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Le format du code secret est invalide");
            
            alert.showAndWait();
           return;
        }
        Adherent nouveau = new Adherent(nomAdherent.getText(), telephoneAdherent.getText(), 
                adresseAdherent.getText(), 0, codeSaisi);
        application.getListeAdherents().add(nouveau);
        
        // Entree LogVideoclub
        application.getLogVideoclub().enregistrerNouvelAdherent(application.getEmployeConnecte().getNom(), nouveau);
        application.getHistoriqueSession().ajouterAdherent(nouveau);
        
        Stage stage = (Stage) boutonEnregistrer.getScene().getWindow();
        stage.close();
    }
    
    public void actionAnnuler(ActionEvent event) {
        // Recuperer la fenêtre (stage) parente
        Stage stage = (Stage) boutonAnnuler.getScene().getWindow();
        // Fermer fenêtre
        stage.close();
    }
    
}
