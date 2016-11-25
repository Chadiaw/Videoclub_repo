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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author cheik
 */
public class LoginController implements Initializable {
    
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    Button boutonConnexion;
    @FXML
    Label messageConnexion;
    
    private Videoclub application;
    
    
    public void setApp(Videoclub application){
        this.application = application;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        messageConnexion.setText("");        
    }
       
    public void actionConnexion(ActionEvent event) {
        if (application == null){
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
        } else {
            if (!application.connexionEmploye(username.getText(), password.getText())){
                messageConnexion.setText("Nom d'utilisateur/Mot de passe incorrect.");
            }
        }
    }
}
