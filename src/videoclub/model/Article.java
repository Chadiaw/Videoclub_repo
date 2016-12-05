/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub.model;

/**
 * Description d'un article du vidéoclub.
 */
public class Article {

    private int numeroArticle;
    private double prix;
    private String descriptif;
    private boolean achetable;

    public Article(int numeroArticle, double prix, String descriptif, boolean achetable) {
        this.numeroArticle = numeroArticle;
        this.prix = prix;
        this.descriptif = descriptif;
        this.achetable = achetable;

    }

    public int getNumeroArticle() {
        return numeroArticle;
    }

    public void setNumeroArticle(int numeroArticle) {
        this.numeroArticle = numeroArticle;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

    public boolean isAchetable() {
        return achetable;
    }

    public void setAchetable(boolean achetable) {
        this.achetable = achetable;
    }

}
