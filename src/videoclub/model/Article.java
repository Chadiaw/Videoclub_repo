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
    
    private String codeArticle;

    
    private double prix;
    private String descriptif;
    private boolean achetable;
    

    public Article(String codeArticle, String descriptif, double prix, boolean achetable){
        this.codeArticle = codeArticle;
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
    
    public String getCodeArticle() {
        return codeArticle;
    }
    
    public boolean isAchetable(){
        return this.achetable;
    }
    
}