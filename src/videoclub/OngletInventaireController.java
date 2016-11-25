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
import videoclub.model.Employe;

/**
 * FXML Controller class
 *
 * @author cheik
 */
public class OngletInventaireController implements Initializable {

/*
    @FXML
	private Label messageBienvenue;
    @FXML 
    private Button boutonDeconnexion;

  */  
    private Videoclub application;
    
    
    public void setApp(Videoclub application){
        this.application = application;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //String message = String.format("Bonjour, %s", 
        //        application.getEmployeConnecte().getNom());
        //messageBienvenue.setText(message);
    }    
    
}
