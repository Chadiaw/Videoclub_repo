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
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.StageStyle;
import videoclub.model.Article;
import videoclub.model.CatalogueProduits;
import videoclub.model.Employe;
import videoclub.model.Film;

/**
 * FXML Controller class
 *
 * @author cheik
 */
public class OngletInventaireController implements Initializable {

    @FXML
    private Label venteLabel;
    @FXML
    private Label locationLabel;
    @FXML
    private Button boutonArticle;
    @FXML
    private Button boutonConfirmerArticle;
    @FXML
    private Button boutonAjouterFilm;
    @FXML
    private Button boutonConfirmerFilm;
    @FXML
    private TableView<Article> tableAchetables = new TableView<Article>();
    @FXML
    private TableColumn codeArticleCol;
    @FXML
    private TableColumn descriptifCol;
    @FXML
    private TableColumn prixCol;
    @FXML
    private TableColumn quantiteCol;

    @FXML
    private TableView<Film> tableLouables = new TableView<Film>();
    @FXML
    private TableColumn codeFilmCol;
    @FXML
    private TableColumn titreCol;
    @FXML
    private TableColumn typeCol;
    @FXML
    private TableColumn genreCol;
    @FXML
    private TableColumn anneeCol;
    @FXML
    private TableColumn<Film, Boolean> nouveauteCol;

    private Videoclub application;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.application = Videoclub.getInstance();

        // Initialisation table achetables : toutes les colonnes excepté code sont rendues éditables avec un TextField
        
        codeArticleCol.setCellValueFactory(new PropertyValueFactory<Article, String>("codeArticle"));
        
        descriptifCol.setCellValueFactory(new PropertyValueFactory<Article, String>("descriptif"));
        descriptifCol.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptifCol.setOnEditCommit(new EventHandler<CellEditEvent<Article, String>>() {
            @Override
            public void handle(CellEditEvent<Article, String> t) {
                ((Article) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setDescriptif(t.getNewValue());
            }
        });
        prixCol.setCellValueFactory(new PropertyValueFactory<Article, String>("prixFormatted"));
        prixCol.setCellFactory(TextFieldTableCell.forTableColumn());
        prixCol.setOnEditCommit(new EventHandler<CellEditEvent<Article, String>>() {
            @Override
            public void handle(CellEditEvent<Article, String> t) {
                ((Article) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setPrixFormatted(t.getNewValue());
            }
        });
        
        tableAchetables.setItems(CatalogueProduits.getInstance().getListeArticles());

        
        // Initialisation table louables : les colonnes titre, type, genre, annee, et nouveauté sont rendues éditables (TextField)
        codeFilmCol.setCellValueFactory(new PropertyValueFactory<Film, String>("codeArticle"));
        
        titreCol.setCellValueFactory(new PropertyValueFactory<Film, String>("titre"));
        titreCol.setCellFactory(TextFieldTableCell.forTableColumn());
        titreCol.setOnEditCommit(new EventHandler<CellEditEvent<Film, String>>() {
            @Override
            public void handle(CellEditEvent<Film, String> t) {
                ((Film) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setTitre(t.getNewValue());
            }
        });

        typeCol.setCellValueFactory(new PropertyValueFactory<Film, String>("type"));
        typeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        typeCol.setOnEditCommit(new EventHandler<CellEditEvent<Film, String>>() {
            @Override
            public void handle(CellEditEvent<Film, String> t) {
                ((Film) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setType(t.getNewValue());
            }
        });
        
        genreCol.setCellValueFactory(new PropertyValueFactory<Film, String>("genre"));
        genreCol.setCellFactory(TextFieldTableCell.forTableColumn());
        genreCol.setOnEditCommit(new EventHandler<CellEditEvent<Film, String>>() {
            @Override
            public void handle(CellEditEvent<Film, String> t) {
                ((Film) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setGenre(t.getNewValue());
            }
        });
        
        anneeCol.setCellValueFactory(new PropertyValueFactory<Film, String>("anneeString"));
        anneeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        anneeCol.setOnEditCommit(new EventHandler<CellEditEvent<Film, String>>() {
            @Override
            public void handle(CellEditEvent<Film, String> t) {
                ((Film) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setAnneeString(t.getNewValue());
            }
        });
        
        nouveauteCol.setCellValueFactory(f -> f.getValue().isNouveauteProperty());
        nouveauteCol.setCellFactory(tc -> new CheckBoxTableCell<>());
        tableLouables.setItems(CatalogueProduits.getInstance().getListeFilms());

    }

    @FXML
    public void actionConfirmerArticle(ActionEvent event) {
        CatalogueProduits.getInstance().setArticleDirty(true);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Changements enregistrés.");
        alert.showAndWait();
        
        application.getLogVideoclub().getEntreesLog().addAll(application.getArticlesAjoutes());
    }

    @FXML
    public void actionConfirmerFilm(ActionEvent event) {
        CatalogueProduits.getInstance().setFilmDirty(true);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Changements enregistrés.");
        alert.showAndWait();
    }
    
    @FXML
    public void actionNouvelArticle(ActionEvent event) {
        try {
            application.getViewManager().openView("nouvelArticle.fxml", "Nouvel article", StageStyle.UTILITY);
        } catch (IOException ex) {
            Logger.getLogger(OngletInventaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
