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
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import videoclub.model.CatalogueProduits;
import videoclub.model.LigneLocation;

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
    private TableView<TableLocationItem> listeLocations = new TableView<TableLocationItem>();
    @FXML
    private TableColumn codeCol;
    @FXML
    private TableColumn titreCol;
    @FXML
    private TableColumn adherentCol;
    @FXML
    private TableColumn dateLocCol;
    @FXML
    private TableColumn dateRetCol;
    
    private ObservableList<TableLocationItem> tableItems = FXCollections.observableArrayList();
    
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
        
        // Initialisation table des locations 
        codeCol.setCellValueFactory(new PropertyValueFactory<TableLocationItem,String>("code"));
        titreCol.setCellValueFactory(new PropertyValueFactory<TableLocationItem, String>("titre"));
        adherentCol.setCellValueFactory(new PropertyValueFactory<TableLocationItem, String>("adherent"));
        dateLocCol.setCellValueFactory(new PropertyValueFactory<TableLocationItem, String>("dateLoc"));
        dateRetCol.setCellValueFactory(new PropertyValueFactory<TableLocationItem, String>("dateRet"));
        
        tableItems.clear();
        for(LigneLocation ligne : application.getLogLocations().getLocationsEnCours()) {
            tableItems.add(new TableLocationItem(ligne));
        }
        listeLocations.setItems(tableItems);
        
    }    

    protected class TableLocationItem {
        
        private SimpleStringProperty code;
        private SimpleStringProperty titre;
        private SimpleStringProperty adherent;
        private SimpleStringProperty dateLoc;
        private SimpleStringProperty dateRet;

        public String getCode() {
            return code.get();
        }

        public String getTitre() {
            return titre.get();
        }

        public String getAdherent() {
            return adherent.get();
        }

        public String getDateLoc() {
            return dateLoc.get();
        }

        public String getDateRet() {
            return dateRet.get();
        }
        
        public TableLocationItem(LigneLocation ligne) {
            this.code = new SimpleStringProperty(ligne.getCodeFilm());
            String titreFilm = CatalogueProduits.getInstance().getFilmByCode(ligne.getCodeFilm()).getTitre();
            this.titre = new SimpleStringProperty(titreFilm);
            this.adherent = new SimpleStringProperty(ligne.getNomAdherent());
            this.dateLoc = new SimpleStringProperty(ligne.getDateLouee().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            this.dateRet = new SimpleStringProperty(ligne.getDateRetour().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            
        }
    }
    
}
