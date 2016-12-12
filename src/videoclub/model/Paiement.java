/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub.model;

import java.text.DecimalFormat;

/**
 *
 * @author Melanie
 */
public class Paiement {
    
    private double sousTotal;
    private double monnaie;
    private double TPS;
    private double TVQ;
    private double total;
    private boolean complete;
    
    public Paiement(double sousTotal){
        this.sousTotal = sousTotal;
        this.TPS = calculerTPS();
        this.TVQ = calculerTVQ();
        this.total = calculerTotal(); 
        this.complete = false;
    }
    
    public void setArgentTendu(double argent){
        this.monnaie = argent - total;
    }
    
    public double getSousTotal(){
        return sousTotal;
    }
    
    public double getTVQ(){
        return TVQ;
    }
    public double getTPS(){
        return TPS;
    }
    
    public double getTotal(){
        return this.total;
    }
    
    public String getMontantFormatted(double montant){
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(montant);
    }
    
    public double calculerTPS(){
        return sousTotal * 0.05 ;
    }
    
    public double calculerTVQ(){
        return sousTotal * 0.0975;
    }
    
    public double calculerTotal(){
        return sousTotal + TPS + TVQ;
    }
    
    public double getMonnaie(){
        return this.monnaie;
    }
    
    public void setComplete(boolean complete){
        this.complete = complete;
    }
    
    public boolean getComplete(){
        return this.complete;
    }
    
}
