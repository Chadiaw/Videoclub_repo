/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author cheik
 */
public class OngletLocationsController implements Initializable {


    @FXML
    private Label listeLocationsLabel;
    @FXML
    private Text dateDuJour;
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
    
    @FXML
    private Button boutonRapportRetards;
    @FXML
    private TableView listeLocations;
    
    private Videoclub application;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.application = Videoclub.getInstance();
        
        Date today = new Date();
        dateDuJour.setText(String.format("Date du jour : %s", dateFormat.format(today)));
    }    
    
}
