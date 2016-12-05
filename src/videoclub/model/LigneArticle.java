/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub.model;

/**
 *
 * @author cheikh
 */
public class LigneArticle {
    private Article article;
    private int quantite;
   
    public LigneArticle(Article article, int quantite) {
        this.article = article;
        this.quantite = quantite;
    }
    
    public double getSousTotal() {
        return quantite*article.getPrix();
    }
}
