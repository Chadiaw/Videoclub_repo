/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 *
 * @author cheik
 */
public class Article {
    
    private SimpleStringProperty codeArticle;
    private SimpleDoubleProperty prix;
    private SimpleStringProperty prixFormatted;
    private SimpleStringProperty descriptif;
    private SimpleBooleanProperty achetable;
    

    public Article(String codeArticle, String descriptif, double prix, boolean achetable){
        this.codeArticle = new SimpleStringProperty(codeArticle);
        this.prix = new SimpleDoubleProperty(prix);
        this.prixFormatted = new SimpleStringProperty(Double.toString(prix));
        this.descriptif = new SimpleStringProperty(descriptif);
        this.achetable = new SimpleBooleanProperty(achetable);
    }
    
    public double getPrix(){
        return this.prix.get();
    }
        
    public String getDescriptif(){
        return this.descriptif.get();
    }
    
    public String getCodeArticle() {
        return codeArticle.get();
    }
    
    public boolean isAchetable(){
        return this.achetable.get();
    }

    public void setCodeArticle(String codeArticle) {
        this.codeArticle.set(codeArticle);
    }

    public void setPrix(double prix) {
        this.prix.set(prix);
        this.prixFormatted.set(Double.toString(prix));
    }

    public void setPrixFormatted(String prixString) {
        this.prixFormatted.set(prixString);
        this.prix.set(Double.parseDouble(prixString));
    }

    public String getPrixFormatted() {
        return this.prixFormatted.get();
    }
    
    public void setDescriptif(String descriptif) {
        this.descriptif.set(descriptif);
    }

    public void setAchetable(boolean achetable) {
        this.achetable.set(achetable);
    }
    
    
}