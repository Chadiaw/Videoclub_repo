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
    private double sousTotal;
    private Article article;
    
    public LigneArticle(String numeroArticle, int quantite){
        /*this.numeroArticle = numeroArticle;*/
        this.article = CatalogueProduits.getInstance().getArticle(numeroArticle);
        this.quantite = quantite;
        this.sousTotal = quantite * article.getPrix();
        
    }
    
    
    /*
        Cette implémentation marche, mais je pense qu'on devrait quand même 
       avoir un attribut Article dans cette classe. 
        Par exemple, dans le tableau de la fenetre vente, il sera alors facile d'appeler
        ligne.article.code, ligne.article.descriptif etc pour remplir les colonnes.
    */
    public LigneArticle(Article article, int quantite) {
        this.article = article;
        this.quantite = quantite;
        this.sousTotal = quantite * article.getPrix();
    }
    
    public int getQuantite(){
        return this.quantite;
    }
    
    public double getSousTotal(){
        return this.sousTotal;
    }
    
    public Article getArticle() {
        return article;
    }
}
