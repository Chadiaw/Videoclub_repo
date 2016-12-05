/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Catalogue de produits du vidéoclub.
 * Contient la liste d'articles et films du vidéoclub.
 * @author cheikh
 */
public class Catalogue {
    
    private Catalogue(){
        
    }
    
    // Instance unique du catalogue.
    private static Catalogue INSTANCE = new Catalogue();
    
    private ObservableList<Article> listeArticles = FXCollections.observableArrayList();

    public void setListeArticles(ObservableList<Article> listeArticles) {
        this.listeArticles = listeArticles;
    }
    
    public static Catalogue getInstance() {
        return INSTANCE;
    }
    
    public ObservableList<Article> getListeArticles() {
        return listeArticles;
    }
    
   public void addArticle(Article article) {
       listeArticles.add(article);
   }
    
    
}
