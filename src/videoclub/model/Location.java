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
    }
    
    public void ajouterLigneLocation(String codeFilm, int duree){
        LigneLocation ligne = new LigneLocation(codeFilm, duree);
        lignesLocation.add(ligne);
    }
    
    public double getTotalLocation(){
        double total = 0;
        for (LigneLocation ligne : lignesLocation){
            total += ligne.getSousTotal();
        }
        return total;
    }

    public String getTotalFormatted() {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(getTotalLocation());
    }

    public ObservableList<LigneLocation> getLignesLocation() {
        return lignesLocation;
    }
    
    
    
}
