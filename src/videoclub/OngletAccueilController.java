/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import videoclub.HistoriqueSession.EntreeHistoriqueSession;

/**
 * FXML Controller class
 *
 * @author cheik
 */
public class OngletAccueilController implements Initializable {

    @FXML
    private Label historiqueLabel;
    @FXML
    private TableView<EntreeHistoriqueSession> tableHistorique = new TableView<EntreeHistoriqueSession>();
    @FXML
    private TableColumn idCol;
    @FXML
    private TableColumn typeCol;
    @FXML
    private TableColumn dateCol;
    @FXML
    private TableColumn detailsCol;
    @FXML
    private Button boutonTransaction;
    
    private Videoclub application;
    @FXML
    private Button boutonRetournerFilm;
    @FXML
    private Button boutonInscrireAdherent;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.application = Videoclub.getInstance();
        
        idCol.setCellValueFactory(new PropertyValueFactory<EntreeHistoriqueSession, Integer>("id"));
        typeCol.setCellValueFactory(new PropertyValueFactory<EntreeHistoriqueSession, String>("type"));
        dateCol.setCellValueFactory(new PropertyValueFactory<EntreeHistoriqueSession, String>("date"));
        detailsCol.setCellValueFactory(new PropertyValueFactory<EntreeHistoriqueSession, String>("details"));
        tableHistorique.setItems(application.getHistoriqueSession().getListeEntrees());
        
    }    
    
    @FXML
    public void actionTransaction(ActionEvent event) {
        
        Stage stage = (Stage) boutonTransaction.getScene().getWindow();
        stage.close();
        
        // Ouvrir l'interface de Nouvelle transaction
        try {
            application.getViewManager().openView("newTransaction.fxml", "Nouvelle transaction", StageStyle.UTILITY );
        }
        catch (Exception ex) {
            Logger.getLogger(OngletAccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    @FXML
    public void actionInscrireAdherent(ActionEvent event) {
        try {
            application.getViewManager().openView("nouvelAdherent.fxml", "Ajouter adhérent", StageStyle.UTILITY);
        } catch (IOException ex) {
            Logger.getLogger(OngletAccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void actionRetournerFilm(ActionEvent event) {
        try {
            application.getViewManager().openView("fenetreRetour.fxml", "Retourner film", StageStyle.UTILITY);
        } catch (IOException ex) {
            Logger.getLogger(OngletAccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
