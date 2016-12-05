/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub.model;

import java.util.Calendar;
/**
 *
 * @author cheik
 */
public class Transaction {
    
    private int numeroTransaction;
    private String date;
    private Vente vente;
    private Location location;
    private double total;
    private double TPS; /*champ relatif*/
    private double TVQ; /*champ relatif*/
    private double sousTotal;

   
    public Transaction(int numeroTransaction){
        this.numeroTransaction = numeroTransaction;
        this.location = new Location();
        this.total = 0;
    }
    
    public int getNumeroTransaction(){
        return numeroTransaction;
    }
    
    public String getDate(){
        return date;
    }
    
    public void setTotal(){
        this.total = calculerTotal();
    }
    public double getTotal(){
        return total;
    }
    
    public double calculerSousTotal(){
        sousTotal = 0;
        if(this.vente != null){
            sousTotal = sousTotal + this.vente.getTotalVente();
        }
        if(this.location != null){
            sousTotal = sousTotal + this.location.getTotalLocation();
        }
        return sousTotal;
    }
    
    public double calculerTPS(){
        return (double)Math.round(this.sousTotal * 0.05 * 0.998*100)/100 ;
    }
    
    public double calculerTVQ(){
        return (double)Math.round(this.sousTotal * 0.998*100)/100 ;
    }
    
    public double calculerTotal() {
        return calculerSousTotal() + calculerTPS() + calculerTVQ();
    }
    
      public void creerVente(){
        this.vente = new Vente();
    }
    
    
    public void creerLocation(){
        this.location = new Location(); 
    }
    
    
    public void creerPaiement(){
        
    }
    
    public void enregistrerTransaction(){
        
    }
}
