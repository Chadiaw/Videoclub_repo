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
    
    private String numeroArticle;
    private double prix;
    private String descriptif;
    private boolean achetable;
    
    public Article(String numeroArticle, String descriptif, double prix, boolean achetable){
        this.numeroArticle = numeroArticle;
        this.prix= prix;
        this.descriptif=descriptif;
        this.achetable= achetable;
        
    }
    
    public double getPrix(){
        return this.prix;
    }
    
    public String getDescriptif(){
        return this.descriptif;
    }
    
    public boolean getAchetable(){
        return this.achetable;
    }
    
}