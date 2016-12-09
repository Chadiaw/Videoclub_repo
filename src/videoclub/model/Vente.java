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
public class Vente {
    
    private double totalVente;
    private ObservableList<LigneArticle> lignesArticle;
    
    public Vente(){
        this.totalVente = 0;
        this.lignesArticle = FXCollections.observableArrayList();
    }
    
    public void ajouterLigneArticle(String codeArticle, int quantite){
        LigneArticle ligne = new LigneArticle(codeArticle, quantite);
        lignesArticle.add(ligne);
        totalVente += ligne.getSousTotal();
    }
   
    public double getTotalVente(){
        return totalVente;
    }
    
    public String getTotalFormatted() {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(totalVente);
    }

    public ObservableList<LigneArticle> getLignesArticles() {
        return lignesArticle;
    }
}
