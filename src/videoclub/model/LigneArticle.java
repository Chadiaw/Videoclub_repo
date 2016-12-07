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
    
    private String numeroArticle;/* cet attribut ne devrait pas apparaitre dans Ligne Article*/
    private int quantite;
    //private Description description; //non Description se trouve dans Article
    private double sousTotal;
    private CatalogueProduits catalogue;
    
    public LigneArticle(String numeroArticle, int quantite){
        /*this.numeroArticle = numeroArticle;*/
        this.quantite = quantite;
        this.catalogue = CatalogueProduits.getInstance();
        this.sousTotal = quantite * catalogue.getArticle(numeroArticle).getPrix();
        
   /*est-ce que la connexion au catalogue devrait se faire a partir de cette classe*/
    }
    
    public int getQuantite(){
        return this.quantite;
    }
    
    public double getSousTotal(){
        return this.sousTotal;
    }
    
}
