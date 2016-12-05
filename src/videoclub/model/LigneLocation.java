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

/**
 *
 * @author Melanie
 */
public class LigneLocation {
    
    private int duree; /*le nombre de jours de la location */
    private int numExemplaire; /*le numero d'exemplaire de l'article */
    private int numArticle;
    private double sousTotal;
    /*date de retour a ajouter*/
    
    public LigneLocation(int numeroArticle, int numeroExemplaire, int duree){
        this.duree = duree;
        this.numExemplaire = numeroExemplaire;
        /*Ajouter connexion a l'article avec le numero Article.. il faudra renvoyer
        * la description a location, ou passer directement comme location aura le numero d'article
        */
        this.sousTotal = calculerSousTotal(numeroArticle, duree);
    }
    
    public double getSousTotal(){
        return this.sousTotal;
    }
    
    private double calculerSousTotal(int numeroArticle, int duree){
        double total =0 ; /*a changer le 0 une fois le reste decommente */
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
        return total;
    }
}
