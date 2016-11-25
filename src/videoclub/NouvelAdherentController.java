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
    
    private Videoclub application;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        application = Videoclub.getInstance();
    }    
    
    public void actionEnregistrer(ActionEvent event) {
        Adherent nouveau = new Adherent(nomAdherent.getText(), telephoneAdherent.getText(), 
                adresseAdherent.getText(), 0);
        application.ajouterAdherent(nouveau);
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
