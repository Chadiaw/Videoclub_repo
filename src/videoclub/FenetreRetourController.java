/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub;

import java.net.URL;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import videoclub.model.Adherent;
import videoclub.model.CatalogueProduits;
import videoclub.model.HistoriqueTransactions;
import videoclub.model.LigneLocation;
import videoclub.model.Location;
import videoclub.model.Transaction;

/**
 * FXML Controller class
 *
 * @author Melanie
 */
public class FenetreRetourController implements Initializable {
    
    @FXML
    private Label entreeManuelleLabel;
    @FXML
    private TextField titreField;
    @FXML
    private TextField codeArticleField;
    @FXML
    private Button boutonScan;
    @FXML
    private Button boutonRetourner;
    @FXML
    private Label messageErreur;
    @FXML 
    private TableColumn titreCol;
    @FXML 
    private TableColumn adherentCol;
    
    @FXML
    private TableView<TableRetourItem> tableRetour = new TableView<TableRetourItem>();
    @FXML
    private Button boutonTerminer;
    private Videoclub application;
    private ObservableList<FenetreRetourController.TableRetourItem> items = FXCollections.observableArrayList();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        application = Videoclub.getInstance();
        messageErreur.setText("");
        // TODO
        titreCol.setCellValueFactory(new PropertyValueFactory<FenetreRetourController.TableRetourItem,String>("titre"));
        adherentCol.setCellValueFactory(new PropertyValueFactory<FenetreRetourController.TableRetourItem, String>("adherent"));
        
        tableRetour.setItems(items);
    }  
    
    @FXML
    private void actionRetourner(ActionEvent event){
        String codeSaisi = codeArticleField.getText();
        
        LigneLocation location = application.getLogLocations().findLocation(codeSaisi);
        
        if(location == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);;
            alert.setHeaderText(null);
            alert.setContentText("Le code saisi ne correspond à aucun article en location");
            alert.showAndWait();
            return;
        }else{
            //Enlever la location de la liste des locations courantes de l'adherent 
            for(Adherent adherent: application.getListeAdherents()){
                if(adherent.getNom().equals(location.getNomAdherent())){
                    adherent.enleverLocation(location);
                }
            }
            //mettre à jour la date de retour de la location
            location.setDateRetour(LocalDate.now());
            
            //Enlever la location du Log des locations courantes
            application.getLogLocations().enleverLocation(location);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Retour confirmé!");
            alert.showAndWait();
            
            items.add(new TableRetourItem(location));
        }
 
    }
    
    protected class TableRetourItem {
        
        /*
         Définit le format d'une ligne de la table location
        */
        //private String code;
        private String titre;
        private String adherent;

        public TableRetourItem(LigneLocation ligneRetour) {
            this.titre = CatalogueProduits.getInstance().getFilmByCode(ligneRetour.getCodeFilm()).getTitre();
            this.adherent = ligneRetour.getNomAdherent();       
        }
        
        public String getTitre() {
            return titre;
        }

        public String getAdherent() {
            return adherent;
        }
        
    }
    
    
}
