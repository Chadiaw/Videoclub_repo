/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub.model;

import java.util.ArrayList;
import javafx.collections.ObservableList;


/**
 *
 * @author Melanie
 */
public class CatalogueProduits {
    
    private ObservableList<Article> catalogue;
    
    private CatalogueProduits(){
        
    }
    
    public void ajouterArticle(String numero, String descriptif, double prix, boolean achetable){

        Article nouvelArticle = new Article(numero, descriptif, prix, achetable);
        catalogue.add(nouvelArticle);
    }
    
    private static CatalogueProduits instance;
    
    public static CatalogueProduits getInstance(){
        return instance;
    }
    
    
    
}
