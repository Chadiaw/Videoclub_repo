/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;


/**
 *
 * @author Melanie
 */
public class CatalogueProduits {
    
    private ObservableList<Article> listeProduits;
    
    // Mappe les numéros (code article) à des objets Article
    private ObservableMap<String, Article> mapCodeProduits = FXCollections.observableHashMap();
    
    private CatalogueProduits(){
        
    }
    
    /*
    public void ajouterArticle(String numero, String descriptif, double prix, boolean achetable){

        Article nouvelArticle = new Article(numero, descriptif, prix, achetable);
        listeProduits.add(nouvelArticle);
        mapCodeProduits.put(numero, nouvelArticle);
    }
    */
    // Rendre la méthode indépendante de l'implémentation de Article
    public void ajouterArticle(Article article) {
        listeProduits.add(article);
        mapCodeProduits.put(article.getNumeroArticle(), article);
    }
    
    // Si on ne veut pas ajouter les articles un par un..
    public void setListeProduits(ObservableList<Article> listeProduits) {
        this.listeProduits = listeProduits;
        for (Article article:listeProduits)
        {
            mapCodeProduits.put(article.getNumeroArticle(), article);
        }
    }
    
    
    
    private static CatalogueProduits INSTANCE = new CatalogueProduits();
    
    public static CatalogueProduits getInstance(){
        return INSTANCE;
    }
    
    public Article getArticle(String numero){     
        return mapCodeProduits.get(numero);
        
    }
    
}
