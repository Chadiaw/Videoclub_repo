/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        
        LocalDate today = LocalDate.now();
        String dateFormatted = today.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        dateDuJour.setText(String.format("Date du jour : %s", dateFormatted));
    }    
    
}
