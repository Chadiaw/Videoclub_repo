/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub.model;

import java.util.ArrayList;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author Melanie
 */
public class Film extends Article{
    
    private SimpleStringProperty titre;
    private SimpleStringProperty genre;
    private SimpleBooleanProperty nouveaute;
    private SimpleStringProperty synopsis;
    private SimpleStringProperty type; /*Blue-Ray/DVD */
    private SimpleIntegerProperty annee;
    private SimpleStringProperty anneeString;
    
    public Film (String codeArticle, String descriptif, double prix, boolean achetable, String titre, 
                    String genre, boolean nouveaute, String synopsis, String type, int annee){
        super(codeArticle, descriptif, prix, achetable);
        this.titre = new SimpleStringProperty(titre);
        this.genre = new SimpleStringProperty(genre);
        this.nouveaute = new SimpleBooleanProperty(nouveaute);
        this.synopsis = new SimpleStringProperty(synopsis);
        this.type = new SimpleStringProperty(type);
        this.annee = new SimpleIntegerProperty(annee);
        this.anneeString = new SimpleStringProperty(Integer.toString(annee));
    }
    
    public String getTitre(){
        return this.titre.get();
    }
    
    public SimpleStringProperty getTitreProperty() {
        return this.titre;
    }
    
    public String getGenre(){
        return this.genre.get();
    }
    
    public SimpleStringProperty getGenreProperty() {
        return this.genre;
    }
    
    public boolean isNouveaute(){
        return this.nouveaute.get();
    }
    
    public ObservableValue<Boolean> isNouveauteProperty() {
        return this.nouveaute;
    }
    
    public String getType(){
        return this.type.get();
    }

    public SimpleStringProperty getTypeProperty() {
        return this.type;
    }
    
    public String getSynopsis() {
        return synopsis.get();
    }
    
    public SimpleStringProperty getSynopsisProperty() {
        return this.synopsis;
    }

    public int getAnnee() {
        return annee.get();
    }
    
    public SimpleIntegerProperty getAnneeProperty() {
        return this.annee;
    }

    public void setTitre(String titre) {
        this.titre.set(titre);
    }

    public void setGenre(String genre) {
        this.genre.set(genre);
    }

    public void setNouveaute(boolean nouveaute) {
        this.nouveaute.set(nouveaute);
    }

    public void setSynopsis(String synopsis) {
        this.synopsis.set(synopsis);
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public void setAnnee(int annee) {
        this.annee.set(annee);
        this.anneeString.set(Integer.toString(annee));
    }

    public String getAnneeString() {
        return anneeString.get();
    }

    public void setAnneeString(String anneeString) {
        this.anneeString.set(anneeString);
        this.annee.set(Integer.parseInt(anneeString));
    }
    
    
    
    
    
    
    
    
    
}
    

