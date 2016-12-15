/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Melanie
 */


public class Location {
    
    private double totalLocation;
    private ObservableList<LigneLocation> lignesLocation;
    
    public Location(){
        this.totalLocation = 0;
        this.lignesLocation = FXCollections.observableArrayList();
    }
    
    public void ajouterLigneLocation(LigneLocation ligne) {
        lignesLocation.add(ligne);
        this.totalLocation += ligne.getSousTotal();
    }
    
    public void ajouterLigneLocation(Adherent adherent, String codeFilm, int duree){
        LigneLocation ligne = new LigneLocation(adherent.getNom(), codeFilm, duree);
        lignesLocation.add(ligne);
        this.totalLocation += ligne.getSousTotal();
    }
    
    public double getTotalLocation(){
        return this.totalLocation;
    }

    public String getTotalFormatted() {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(getTotalLocation());
    }

    public void setTotal(double total) {
        this.totalLocation = total;
    }
    
    public ObservableList<LigneLocation> getLignesLocation() {
        return lignesLocation;
    }
    
    public LigneLocation findLigneLocation(String codeArticle){
        for(LigneLocation ligneLoc: lignesLocation){
            if(ligneLoc.getCodeArticle().equals(codeArticle)){
                return ligneLoc;
            }
        }
        return null;
    }
    
    
    
}
