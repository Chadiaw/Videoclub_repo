/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub.model;

import java.text.DecimalFormat;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author cheik
 */
public class Adherent {
    
    private String  nom;
    private String numeroTelephone;
    private String adresse;
    private int codeSecret;
    private ObservableList<LigneLocation> locationsCourantes = FXCollections.observableArrayList();
    

    public int getCodeSecret() {
        return codeSecret;
    }

    public void setCodeSecret(int codeSecret) {
        this.codeSecret = codeSecret;
    }
    
    private double solde; 

    public Adherent(String nom, String numeroTelephone, String adresse, double solde, int codeSecret) {
        this.nom = nom;
        this.numeroTelephone = numeroTelephone;
        this.adresse = adresse;
        this.solde = solde;
        this.codeSecret = codeSecret;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getSoldeFormatted() {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(solde);
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }
    
    public double getSolde() {
        return solde;
    }

    public ObservableList<LigneLocation> getLocationsCourantes() {
        return locationsCourantes;
    }

    public void setLocationsCourantes(ObservableList<LigneLocation> locationsCourantes) {
        this.locationsCourantes = locationsCourantes;
    }
    
    public void enleverLocation(LigneLocation location){
        //for(LigneLocation ligneLoc: locationsCourantes){
        //    if(ligneLoc.equals(location)){      
        //    }
        locationsCourantes.remove(location);
    }
    
    
    
    
}
