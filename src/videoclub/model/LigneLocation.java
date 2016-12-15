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
import static java.time.temporal.ChronoUnit.DAYS;

/**
 *
 * @author Melanie
 */
public class LigneLocation {
    
    private int duree; /*le nombre de jours de la location */
    private String codeFilm;
    private LocalDate dateRetour;
    private String nomAdherent; 
    private LocalDate dateLouee; 

    public String getCodeFilm() {
        return codeFilm;
    }

    public String getNomAdherent() {
        return nomAdherent;
    }

    public LocalDate getDateLouee() {
        return dateLouee;
    }
    
    

    
    public LigneLocation(String adherent, String codeFilm, int duree){
        this.duree = duree;
        this.codeFilm = codeFilm;
        this.nomAdherent = adherent;
        
        LocalDate today = LocalDate.now();
        this.dateLouee = today;
        this.dateRetour = today.plusDays(duree);

    }
    
    public LigneLocation(String codeFilm, String adherent, String dateLouee, String dateRetour) {
        this.codeFilm = codeFilm;
        this.nomAdherent = adherent;
        this.dateLouee = LocalDate.parse(dateLouee);
        this.dateRetour = LocalDate.parse(dateRetour);
        this.duree = (int) DAYS.between(this.dateLouee, this.dateRetour);
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
