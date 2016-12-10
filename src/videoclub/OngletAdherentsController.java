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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import videoclub.model.Adherent;

/**
 * FXML Controller class
 *
 * @author cheik
 */
public class OngletAdherentsController implements Initializable {

    @FXML
    private ListView<Adherent> listeAherents;
    @FXML
    private Label labelListeAdherent;
    @FXML
    private Button boutonAjouterAdherent;
    @FXML
    private TextField nomAdherent;
    @FXML
    private TextField telephoneAdherent;
    @FXML
    private TextArea adresseAdherent;
    @FXML
    private Text  soldeAdherent;
    @FXML 
    private TableView historiqueAdherent;
    
    
    private Videoclub application;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.application = Videoclub.getInstance();
        
        listeAherents.setItems(application.getListeAdherents());
        
        // Formatter ListView pour n'afficher que le nom de l'objet 'Adherent'
        listeAherents.setCellFactory(param -> new ListCell<Adherent>() {
            @Override
            protected void updateItem(Adherent item, boolean empty) {
                super.updateItem(item, empty);
                if(empty || item == null || item.getNom() == null) {
                    setText(null);
                }
                else {
                    setText(item.getNom()); 
                }
            }
        });
        
        // Actions lorsqu'un élément de la liste est sélectionné 
        listeAherents.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<Adherent>() {
                @Override
                public void changed(ObservableValue<? extends Adherent> observable, 
                        Adherent oldValue, Adherent newValue) {
                    // Mise à jour des éléments de droite (infos client, historique)
                    nomAdherent.setText(newValue.getNom());
                    telephoneAdherent.setText(newValue.getNumeroTelephone());
                    adresseAdherent.setText(newValue.getAdresse());
                    soldeAdherent.setText(newValue.getSoldeFormatted());
                    // MaJ historique ici
                    // TODO
                    
                }
            }
        );
    }    
    
    public void ajouterAdherent(ActionEvent event) {
        try {
            application.getViewManager().openView("nouvelAdherent.fxml", "Ajouter adhérent", StageStyle.UTILITY);
        } catch (IOException ex) {
            Logger.getLogger(OngletAdherentsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
