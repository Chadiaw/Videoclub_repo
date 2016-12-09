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
    
    private int quantite;
    private String codeArticle;
    
    public LigneArticle(String codeArticle, int quantite){
        this.codeArticle = codeArticle;
        this.quantite = quantite;
        
        
    }
    
    public int getQuantite(){
        return this.quantite;
    }
    
    public double getSousTotal(){
        return quantite * CatalogueProduits.getInstance().getArticle(this.codeArticle).getPrix();
    }
    
    public String getCodeArticle() {
        return this.codeArticle;
    }
    
}
