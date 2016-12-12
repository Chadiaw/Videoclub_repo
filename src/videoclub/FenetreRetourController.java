/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import videoclub.model.CatalogueProduits;

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
    private TextField exemplaireField;
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
        // TODO
    }  
    
    private void actionRetourner(ActionEvent event){
        String codeSaisi = codeArticleField.getText();
        
        if(CatalogueProduits.getInstance().getArticle(codeSaisi) == null ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);;
            alert.setHeaderText(null);
            alert.setContentText("Le code saisi ne correspond à aucun article");
            alert.showAndWait();
            return;
        }
        

        
        //vérifier si l'exemplaire en question existe
        String exemplaireSaisi = exemplaireField.getText();
        int numeroExemplaire = 0;
        try {
            numeroExemplaire = Integer.parseInt(exemplaireSaisi);
        }
        catch (NumberFormatException ex) {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("L'exemplaire saisi est invalide");
            
            alert.showAndWait();
           return;
        }
        
        //Vérifier que l'exemplaire en question est loué
        if(!CatalogueProduits.getInstance().getFilm(codeSaisi).findExemplaire(numeroExemplaire).getEnLocation()){
            this.messageErreur.setText("Ce film n'est pas en location actuellement.");
        }else{
        
            //aller chercher le locataire en question - ajouter l'info de 
           
            
        }
    }
    
    
}
