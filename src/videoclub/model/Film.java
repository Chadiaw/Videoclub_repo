/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub.model;

import java.util.ArrayList;

/**
 *
 * @author Melanie
 */
public class Film extends Article{
    
    private String titre;
    private String genre;
    private boolean nouveaute;
    private String synopsis;
    private String type; /*Blue-Ray/DVD */
    private int annee;
    private ArrayList<Exemplaire> listeExemplaire;
    
    public Film (String codeArticle, String descriptif, double prix, boolean achetable, String titre, 
                    String genre, boolean nouveaute, String synopsis, String type, int annee){
        super(codeArticle, descriptif, prix, achetable);
        this.titre = titre;
        this.genre = genre;
        this.nouveaute = nouveaute;
        this.synopsis = synopsis;
        this.type = type;
        this.annee = annee;
    }
    
    public String getTitre(){
        return this.titre;
    }
    
    public String getGenre(){
        return this.genre;
    }
    
    public boolean isNouveaute(){
        return this.nouveaute;
    }
    
    public String getType(){
        return this.type;
    }
    
    public void ajouterExemplaire(Exemplaire exemplaire){
        this.listeExemplaire.add(exemplaire);
    }
    
    public Exemplaire findExemplaire(int codeExemplaire){
        for (int i = 0; i < listeExemplaire.size(); i++){
            if(listeExemplaire.get(i).getCodeExemplaire() == codeExemplaire){
                return listeExemplaire.get(i);
            }
        }
        return null;
    }
    
}
    

