/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import videoclub.model.Adherent;
import videoclub.model.CatalogueProduits;
import videoclub.model.HistoriqueTransactions;
import videoclub.model.LigneLocation;
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
    
    //private TableView<TableRetourItem> tableRetour = new TableView<TableRetourItem>();
    @FXML
    private Button boutonTerminer;
    private Videoclub application;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        application = Videoclub.getInstance();
        messageErreur.setText("");
        // TODO
    }  
    
    private void actionRetourner(ActionEvent event){
        String codeSaisi = codeArticleField.getText();
        Transaction transaction = HistoriqueTransactions.getInstance().findTransaction(codeSaisi);
        
        if(transaction == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);;
            alert.setHeaderText(null);
            alert.setContentText("Le code saisi ne correspond à aucun article en location");
            alert.showAndWait();
            return;
        }else{
            Adherent adherent = transaction.getAdherent();
            LocalDate dateRetour = transaction.getLigneLocation(codeSaisi).getDateRetour();
            boolean nouveaute = CatalogueProduits.getInstance().getFilmByCode(codeSaisi).isNouveaute();
            
            int retard = dateRetour.compareTo(LocalDate.now());
            if(retard > 0){
                //solde = calculer selon la formule choisie
                //adherent.setSolde(adherent.getSolde() + solde);
            }else{
                //adherent.setSolde(solde);
                //enlever la location;
                //inscrire la date de retour (dans l'historique? dans ligneLocation?
            }
        }
     
       
        
    }
    
    
}
