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
class Description {
    
    private double prix;
    private final int numeroArticle;
    private String descriptif;
    
    public Description(int numeroArticle, double prix, String descriptif){
        this.prix = prix;
        this.numeroArticle = numeroArticle;
        this.descriptif = descriptif;
    }
    
    public double getPrix(){
        return prix;
    }
    
    public void setPrix(double prix){
        this.prix = prix;
    }
    
    public String getDescriptif(){
        return descriptif;
    }
    
    public void setDescriptif(String descriptif){
        this.descriptif = descriptif;
    }
}
