/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import videoclub.model.Article;
import videoclub.model.CatalogueProduits;
import videoclub.model.LigneArticle;
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
    private TextField codeArticle;
    @FXML 
    private AnchorPane quantiteLabel;
    @FXML
    private TextField quantite;
    @FXML
    private Button boutonAjouter;
    @FXML
    private Button boutonScan;
    @FXML
    private TableView<TableVenteItem> tableVente = new TableView<TableVenteItem>();
    @FXML
    private TableColumn codeCol;
    @FXML
    private TableColumn descriptifCol;
    @FXML
    private TableColumn quantiteCol;
    @FXML
    private TableColumn prixCol;
   
    @FXML
    private Label totalLabel;
    @FXML
    private Button boutonConfirmer;
    @FXML
    private Button boutonAnnuler;
    
    private Videoclub application;
    private Vente vente;
    private ObservableList<TableVenteItem> items = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        application = Videoclub.getInstance();
        vente = new Vente();    
        totalLabel.setText("Total achats : 0 $");
        
        // Remplir table avec des objets TableVenteItem
        codeCol.setCellValueFactory(new PropertyValueFactory<TableVenteItem,String>("code"));
        descriptifCol.setCellValueFactory(new PropertyValueFactory<TableVenteItem, String>("descriptif"));
        quantiteCol.setCellValueFactory(new PropertyValueFactory<TableVenteItem, Integer>("quantite"));
        prixCol.setCellValueFactory(new PropertyValueFactory<TableVenteItem, Double>("prix"));
        
        tableVente.setItems(items);
        
    }
        
    
    @FXML
    private void actionAjouter(ActionEvent event){
        String codeSaisi = codeArticle.getText();
        
        Article articleEntre = CatalogueProduits.getInstance().getArticle(codeSaisi);
        
        if(articleEntre == null ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);;
            alert.setHeaderText(null);
            alert.setContentText("Le code saisi ne correspond à aucun article");
            alert.showAndWait();
            return;
        }
        if(!articleEntre.isAchetable()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("L'article n'est pas disponible en vente.");
            alert.showAndWait();
            return;
        }
        
        // Vérifier si l'article n'est pas déja inclus dans la vente      
        Iterator<LigneArticle> iterVente = vente.getLignesArticles().iterator();
        while (iterVente.hasNext()) {
            LigneArticle ligne = iterVente.next();
            if (ligne.getCodeArticle().equals(articleEntre.getCodeArticle())) {
                vente.setTotalVente(vente.getTotalVente()- ligne.getSousTotal());
                iterVente.remove();
                // Trouver la ligne dans l'affichage
                Iterator<TableVenteItem> iterLignesAffichees = items.iterator();
                while (iterLignesAffichees.hasNext()) {
                    TableVenteItem item = iterLignesAffichees.next();
                    if(item.getCode().equals(ligne.getCodeArticle())) {
                        iterLignesAffichees.remove();
                    }
                }
                
            }
        }
        
        
        String quantiteSaisie = quantite.getText();
        int quantiteEntree = 0;
        try {
            quantiteEntree = Integer.parseInt(quantiteSaisie);
        }
        catch (NumberFormatException ex) {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("La quantité saisie est invalide.");
            
            alert.showAndWait();
           return;
        }
        vente.ajouterLigneArticle(codeSaisi, quantiteEntree);
        totalLabel.setText("Total achats : " + vente.getTotalFormatted() + "$");
        
        items.add(new TableVenteItem(CatalogueProduits.getInstance().getArticle(codeSaisi),quantiteEntree));
    }
       
    @FXML
    private void actionAnnulerVente(ActionEvent event) {
        // Recuperer la fenêtre (stage) parente
        Stage stage = (Stage) boutonAnnuler.getScene().getWindow();
        // Fermer fenêtre
        stage.close();
        
        try {
            application.getViewManager().openView("newTransaction.fxml", "Nouvelle transaction", StageStyle.UTILITY);
        } catch (IOException ex) {
            Logger.getLogger(FenetreVenteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void actionConfirmer(ActionEvent event) {
        // Mettre a jour la transaction courante
        if(vente.getLignesArticles().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("La vente est vide !");
            
            alert.showAndWait();
            return;
            
        }
        application.getTransactionEnCours().setVente(vente);
       
        // Fermer fenetre
        Stage stage = (Stage) boutonConfirmer.getScene().getWindow();
        stage.close();
        
        try {
            application.getViewManager().openView("newTransaction.fxml", "Nouvelle transaction", StageStyle.UTILITY);
        }
        catch (Exception ex) {
            Logger.getLogger(FenetreVenteController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }

  
    protected class TableVenteItem {
        /**
         * Definit le format d'une ligne de la table vente. 
         */
        private String code;
        private String descriptif;
        private int quantite;
        private double prix; 
        
        public TableVenteItem(Article article, int quantite) {
            this.code = article.getCodeArticle();
            this.descriptif = article.getDescriptif();
            this.quantite = quantite;
            this.prix = article.getPrix();
        }
        
        public String getCode() {
            return code;
        }

        public String getDescriptif() {
            return descriptif;
        }

        public int getQuantite() {
            return quantite;
        }

        public double getPrix() {
            return prix;
        }
        
        
    }
}
