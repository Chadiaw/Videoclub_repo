/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*ici je suppose que les films se loue pour :
* Nouveaute : 6$ par jour 
* Non-nouveaute: 1$ par jour ou 5$ la semaine 
*/
package videoclub.model;

import java.time.LocalDate;

/**
 *
 * @author Melanie
 */
public class LigneLocation {
    
    private int duree; /*le nombre de jours de la location */
    private String codeFilm;
    private LocalDate dateRetour;
    
    public LigneLocation(String codeFilm, int duree){
        this.duree = duree;
        this.codeFilm = codeFilm;
        
        LocalDate today = LocalDate.now();
        this.dateRetour = today.plusDays(duree);
    }
    
    
    public String getCodeArticle() {
        return this.codeFilm;
    }
    
    public LocalDate getDateRetour() {
        return this.dateRetour;
    }
    
    public void setDuree(int duree){
        this.duree = duree;
    }
    
    public int getDuree() {
        return this.duree;
    }
    
    public double getSousTotal(){
      /*  double total;*/
        return 6.99;
     /*   if(CatalogueProduits.getInstance().getFilm(codeFilm).isNouveaute()){
            total = 6.0 * duree;
        }else{
            int nbSemaines = (int)duree/7;
            int nbJours = duree%7;
      */      
            /*Si le client a pris le film pour un nombre de semaines + 5 jours*/
            /*on lui accorde la semaine complete comme c'est le meme prix*/
       /*     if(nbJours >= 5){
                setDuree(this.duree + (7-nbJours));
                nbSemaines ++;
                nbJours = 0;
            }
            total = 5 *nbSemaines + nbJours;
            
        }
*/
        /*return total;*/
    }
    
}
