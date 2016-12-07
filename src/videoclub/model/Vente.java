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
public class Vente {
    
    private double totalVente;
    private ArrayList<LigneArticle> LigneArt;
    
    public Vente(){
        this.totalVente = 0;
        this.LigneArt = new ArrayList<>();
    }
    
    public void creerLigneArticle(String numeroArticle, int quantite){
        LigneArticle ligne = new LigneArticle(numeroArticle, quantite);
        LigneArt.add(ligne);
    }
    
    public double getTotalVente(){
        double total = 0;
        for(int i = 0; i< LigneArt.size()-1; i++){
            total = total + LigneArt.get(i).getSousTotal();
        }
        return total;
    }
}
