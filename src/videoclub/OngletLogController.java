/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import videoclub.model.LogEntry;

/**
 * FXML Controller class
 *
 * @author cheikh
 */
public class OngletLogController implements Initializable {

    @FXML
    private Label logLabel;
    @FXML
    private TableView<LogEntry> tableLog = new TableView<LogEntry>();
    @FXML
    private TableColumn dateCol;
    @FXML
    private TableColumn typeCol;
    @FXML
    private TableColumn employeCol;
    @FXML
    private TableColumn detailsCol;

    private Videoclub application;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.application = Videoclub.getInstance();

        typeCol.setCellValueFactory(new PropertyValueFactory<LogEntry, String>("type"));
        dateCol.setCellValueFactory(new PropertyValueFactory<LogEntry, String>("dateFormatted"));
        employeCol.setCellValueFactory(new PropertyValueFactory<LogEntry, String>("nomEmploye"));
        detailsCol.setCellValueFactory(new PropertyValueFactory<LogEntry, String>("details"));

        tableLog.setItems(application.getLogVideoclub().getEntreesLog());

    }


}
