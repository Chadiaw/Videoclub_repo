/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import videoclub.model.Adherent;
import videoclub.model.CatalogueProduits;
import videoclub.model.LigneLocation;

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
    private TableView<TablePretsItem> locationsCourantesAdherent = new TableView<TablePretsItem>();
    @FXML
    private TableColumn statutCol;
    @FXML
    private TableColumn titreCol;
    @FXML
    private TableColumn dateLocCol;
    @FXML
    private TableColumn dateRetCol;
    private ObservableList<TablePretsItem> tablePretsItems = FXCollections.observableArrayList();
    
    
    
    
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
                    tablePretsItems.clear();
                    for (LigneLocation ligne : newValue.getLocationsCourantes()) {
                        tablePretsItems.add(new TablePretsItem((ligne)));
                    }
                    
                }
            }
        );
        
        // Initialisation table des locations courantes
        statutCol.setCellValueFactory(new PropertyValueFactory<TablePretsItem,String>("statut"));
        titreCol.setCellValueFactory(new PropertyValueFactory<TablePretsItem, String>("titre"));
        dateLocCol.setCellValueFactory(new PropertyValueFactory<TablePretsItem, String>("dateLoc"));
        dateRetCol.setCellValueFactory(new PropertyValueFactory<TablePretsItem, String>("dateRet"));
        
        locationsCourantesAdherent.setItems(tablePretsItems);
        
    }    
    
    public void ajouterAdherent(ActionEvent event) {
        try {
            application.getViewManager().openView("nouvelAdherent.fxml", "Ajouter adhérent", StageStyle.UTILITY);
        } catch (IOException ex) {
            Logger.getLogger(OngletAdherentsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected class TablePretsItem {
        
        private SimpleStringProperty statut;
        private SimpleStringProperty titre;
        private SimpleStringProperty dateLoc;
        private SimpleStringProperty dateRet; 

        public String getStatut() {
            return statut.get();
        }

        public String getTitre() {
            return titre.get();
        }

        public String getDateLoc() {
            return dateLoc.get();
        }

        public String getDateRet() {
            return dateRet.get();
        }
        
        public TablePretsItem(LigneLocation ligne) {
            String titreFilm = CatalogueProduits.getInstance().getFilmByCode(ligne.getCodeFilm()).getTitre();
            this.titre = new SimpleStringProperty(titreFilm);
            
            this.dateLoc = new SimpleStringProperty(ligne.getDateLouee().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            this.dateRet = new SimpleStringProperty(ligne.getDateRetour().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            
            if (ligne.getDateRetour().isBefore(LocalDate.now())) {
                this.statut = new SimpleStringProperty("Retard");
            }
            else {
                this.statut = new SimpleStringProperty("A jour");
            }
            
        }
    }
    
}
