/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub.model;

import java.util.ArrayList;

/**
 *
 * @author Melanie
 */


class Location {
    
    private double totalLocation;
    private ArrayList<LigneLocation> LigneLoc;
    
    public Location(){
        this.totalLocation = 0;
        this.LigneLoc = new ArrayList<>();
    }
    
    public void creerLigneLocation(int numeroArticle, int numeroExemplaire, int duree){
        LigneLocation ligne = new LigneLocation(numeroArticle, numeroExemplaire, duree);
        LigneLoc.add(ligne);
    }
    
    public double getTotalLocation(){
        double total = 0;
        for(int i = 0; i< LigneLoc.size()-1; i++){
            total = total + LigneLoc.get(i).getSousTotal();
        }
        return total;
    }
    
}
