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
public class Film extends Article{
    
    String titre;
    String genre;
    boolean nouveaute;
    String synopsis;
    String type; /*Blue-Ray/DVD */
    
    public Film (String numero, String descriptif, double prix, boolean achetable, String titre, 
                    String genre, boolean nouveaute, String synopsis, String type){
        super(numero, descriptif, prix, achetable);
        this.titre = titre;
        this.genre = genre;
        this.nouveaute = nouveaute;
        this.synopsis = synopsis;
        this.type = type;
    }
    
    public String getTitre(){
        return this.titre;
    }
    
    public String getGenre(){
        return this.genre;
    }
    
    public boolean getNouveaute(){
        return this.nouveaute;
    }
    
    public String getType(){
        return this.type;
    }
    
    
    
}
    

