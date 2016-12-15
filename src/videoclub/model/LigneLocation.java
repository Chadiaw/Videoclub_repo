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

import static java.lang.Math.ceil;
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
        // Si nouveauté, facturation quotidienne
        if(CatalogueProduits.getInstance().getFilmByCode(codeFilm).isNouveaute()){
            return CatalogueProduits.getInstance().CoutQuotidienNouveaute * this.getDuree();   
        }
        
        else{
            // Sinon, facturation hebdomadaire
            double coutHebdo = CatalogueProduits.getInstance().CoutHebdoRegulier;
            
            // On trouve le nombre de semaines (arrondi supérieur) correspodant à duree, car facturation hebdomadaire
            int nombreSemaines = (int) ceil(this.duree / 7);
            
            return coutHebdo * nombreSemaines; 
            /*
            if(this.duree < 7){
                double sousTotal = CatalogueProduits.getInstance().CoutQuotidienRegulier * this.duree;
                if(sousTotal > coutHebdo){
                    return coutHebdo;
                }else{
                    return sousTotal;
                }
            }else{
                return coutHebdo;
            }
            */
        }
    }
}
