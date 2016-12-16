/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import videoclub.model.Article;
import videoclub.model.CatalogueProduits;
import videoclub.model.LogEntry;

/**
 * FXML Controller class
 *
 * @author cheikh
 */
public class NouvelArticleController implements Initializable {

    @FXML
    private TextField descriptifField;
    @FXML
    private TextField codeField;
    @FXML
    private TextField prixField;
    @FXML
    private Button boutonEnregistrer;
    @FXML
    private Button boutonAnnuler;
    

    private Videoclub application;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.application = Videoclub.getInstance();
        
    }    

    @FXML
    private void actionEnregistrer(ActionEvent event) {
        if(descriptifField.getText().isEmpty() || codeField.getText().isEmpty() 
                || prixField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Tous les champs n'ont pas été remplis.");
            
            alert.showAndWait();
            return;
        }
        double prixSaisi = 0;
        try {
            prixSaisi = Double.parseDouble(prixField.getText());
        }
        catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Le format du prix est invalide");
            
            alert.showAndWait();
           return;
        }
        
        Article nouvelArticle = new Article(codeField.getText(), descriptifField.getText(), prixSaisi, true);
        CatalogueProduits.getInstance().getListeArticles().add(nouvelArticle);
        LogEntry entry = new LogEntry("Ajout article", application.getEmployeConnecte().getNom(),
                String.format("[%1$s]: %2$s, %3$s ", codeField.getText(), descriptifField.getText(), prixField.getText()));
        
        application.getArticlesAjoutes().add(entry);
        
        Stage stage = (Stage) boutonEnregistrer.getScene().getWindow();
        stage.close();
        
    }

    @FXML
    private void actionAnnuler(ActionEvent event) {
        Stage stage = (Stage) boutonAnnuler.getScene().getWindow();
        stage.close();
        
        
    }
    
}
