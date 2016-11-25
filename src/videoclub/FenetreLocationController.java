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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cheik
 */
public class FenetreLocationController implements Initializable {

    @FXML
    private Label ajoutManuelLabel;
    @FXML
    private TextField titreFilmField;
    @FXML
    private TextField codeFilmField;
    @FXML
    private TextField dureeLocationField;
    @FXML
    private Label messageErreur;
    @FXML
    private Button boutonAjouter;
    @FXML
    private Button boutonScan;
    
    @FXML
    private TableView tableLocation;
    @FXML
    private Label totalLocation;
    @FXML
    private ComboBox adherentBox;
    @FXML
    private Button boutonAnnuler;
    @FXML
    private Button boutonConfirmer;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        titreFilmField.setPromptText("Titre film...");
        codeFilmField.setPromptText("Code film...");
        dureeLocationField.setPromptText("jours");
        messageErreur.setText("");
    }    
    
    public void actionAnnuler(ActionEvent event) {
        // Recuperer la fenêtre (stage) parente
        Stage stage = (Stage) boutonAnnuler.getScene().getWindow();
        // Fermer fenêtre
        stage.close();
    }
    
}
