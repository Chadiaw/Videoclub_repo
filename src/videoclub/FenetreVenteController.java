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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import videoclub.model.CatalogueProduits;
import videoclub.model.Vente;

/**
 * FXML Controller class
 *
 * @author Melanie
 */
public class FenetreVenteController implements Initializable {
    
    @FXML
    private Label entreeManuelleLabel;
    @FXML
    private TextField numeroArticle;
    @FXML 
    private AnchorPane quantiteLabel;
    @FXML
    private TextField quantite;
    @FXML
    private Button boutonAjouter;
    @FXML
    private Button boutonScan;
    @FXML
    private TableView table;
    @FXML
    private Label totalLabel;
    @FXML
    private TextField total;
    @FXML
    private Button boutonConfirmer;
    @FXML
    private Button boutonAnnuler;
    private Videoclub application;
    
    private CatalogueProduits catalogue;
    private Vente vente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        application = Videoclub.getInstance();
        Vente vente = new Vente();
        total.setText(Double.toString(vente.getTotalVente()) + "$");
        
    }  
    
    @FXML
    private void actionAjouter(ActionEvent event){
        String code = numeroArticle.getText();
        String quant = quantite.getText();
        /*ajouter verification de type*/
        int quantite = Integer.parseInt(quant);
        vente.creerLigneArticle(code, quantite);
        total.setText(Double.toString(vente.getTotalVente()) + "$");
    }
        
        
  
        
    
    
    
    

    @FXML
    private void actionAnnulerVente(ActionEvent event) {
        // Recuperer la fenêtre (stage) parente
        Stage stage = (Stage) boutonAnnuler.getScene().getWindow();
        // Fermer fenêtre
        stage.close();
    }

  
    
}
