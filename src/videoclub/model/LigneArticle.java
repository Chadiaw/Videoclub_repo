/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub.model;

/**
 *
 * @author Melanie
 */
public class LigneArticle {
    
    private int numeroArticle;
    private int quantite;
    //private Description description; //non Description se trouve dans Article
    private double sousTotal;
    
    public LigneArticle(int numeroArticle, int quantite){
        this.numeroArticle = numeroArticle;
        this.quantite = quantite;
        
        /*Comment la connection avec catalogue se fait-elle ici?*/
      /*  this.sousTotal = quantite * catalogue.getArticle(numeroArticle).getPrix();*/
    }
    
    public int getQuantite(){
        return this.quantite;
    }
    
    public double getSousTotal(){
        return this.sousTotal;
    }
    
}
