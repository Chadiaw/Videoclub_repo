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


/**
 * FXML Controller class
 *
 * @author cheik
 */
public class MainViewController implements Initializable {

    @FXML
    private Label messageBienvenue;
    @FXML 
    private Button boutonDeconnexion;

    
    private Videoclub application;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.application = Videoclub.getInstance();
        messageBienvenue.setText(application.getEmployeConnecte().getNom());
    }
    
    public void actionDeconnexion(ActionEvent event) {
        if (application == null){
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
        }
        application.deconnexion();
    }
}
