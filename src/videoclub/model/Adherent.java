/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub.model;

import java.text.DecimalFormat;

/**
 *
 * @author cheik
 */
public class Adherent {
    
    private String  nom;
    private String numeroTelephone;
    private String adresse;
    private String codeSecret;

    public String getCodeSecret() {
        return codeSecret;
    }

    public void setCodeSecret(String codeSecret) {
        this.codeSecret = codeSecret;
    }
    
    private Location[] historiqueLocations; // ou locations courantes.
    
    private double solde; 

    public Adherent(String nom, String numeroTelephone, String adresse, double solde) {
        this.nom = nom;
        this.numeroTelephone = numeroTelephone;
        this.adresse = adresse;
        this.solde = solde;
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
    
    
    
}
