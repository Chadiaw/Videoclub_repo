/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub.model;

/**
 *
 * @author cheik
 */
public class Article {
    
    private int numeroArticle;
    private double prix;
    private String descriptif;
    private boolean achetable;
    
    public Article(int numeroArticle, double prix, String descriptif, boolean achetable){
        this.numeroArticle = numeroArticle;
        this.prix= prix;
        this.descriptif=descriptif;
        this.achetable= achetable;
        
    }
    
}