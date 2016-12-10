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
    private int numeroExemplaire; /*le numero d'exemplaire de l'article */
    private String codeArticle;
    private LocalDate dateRetour;
    
    public LigneLocation(String codeArticle, int numeroExemplaire, int duree){
        this.duree = duree;
        this.numeroExemplaire = numeroExemplaire;
        this.codeArticle = codeArticle;
        
        LocalDate today = LocalDate.now();
        this.dateRetour = today.plusDays(duree);
    }
    
    public int getNumeroExemplaire() {
        return this.numeroExemplaire;
    }
    
    public String getCodeArticle() {
        return this.codeArticle;
    }
    
    public LocalDate getDateRetour() {
        return this.dateRetour;
    }
    
    public int getDuree() {
        return this.duree;
    }
    
    public double getSousTotal(){
        // dummy
        return 6.99;
                
        /*
        String numeroArticle = this.codeArticle;
        int duree = this.duree;
        */
        //double total =0 ; /*a changer le 0 une fois le reste decommente */
       /* boolean nouveaute = catalogue.getItem(numeroArticle).getDescription().getNouveaute();*/
      /*  if(nouveaute){
            total = 6 * duree;
        }else{
            int nbSemaines = (int)duree/7;
            int nbJours = duree%7;
            
            /*Si le client a pris le film pour un nombre de semaines + 5 jours*/
            /*on lui accorde la semaine complete comme c'est le meme prix*/
      /*      if(nbJours >= 5){
                this.duree = duree + (7-nbJours);
                nbSemaines ++;
                nbJours = 0;
            }
            total = 5*nbSemaines + nbJours;
        }*/
        //return total;
    }
}
