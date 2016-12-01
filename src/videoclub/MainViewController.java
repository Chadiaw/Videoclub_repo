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
    
    
    public void setApp(Videoclub application){
        this.application = application;
        
        messageBienvenue.setText(application.getEmployeConnecte().getNom());
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
               
    }
    
    public void actionDeconnexion(ActionEvent event) {
        if (application == null){
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
        }
        application.deconnexion();
    }
}