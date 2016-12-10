/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub.model;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 *
 * @author cheik
 */
public class Transaction {
    
    
    private int numeroTransaction;
    private LocalDateTime date;
    private Vente vente;
    private Location location;
    private Paiement paiement;
    private Adherent adherent;
    private double total;
    private double TPS;
    private double TVQ;
   
    public Transaction(){
        this.numeroTransaction = HistoriqueTransactions.getTransactionsCount();
        this.total = 0;
        this.adherent = null;
        this.vente = null;
        this.location = null;
        this.date = LocalDateTime.now(); 
        
    }
    
    public int getNumeroTransaction(){
        return numeroTransaction;
    }
    
    public LocalDateTime getDate(){
        return date;
    }
    
    public double getTotal() {
        double sousTotal = 0;
        if(this.vente != null){
            sousTotal += this.vente.getTotalVente();
        }
        if(this.location != null){
            sousTotal += this.location.getTotalLocation();
        }
        
        this.TPS = calculerTPS(sousTotal);
        this.TVQ = calculerTVQ(sousTotal);
        
        this.total = sousTotal + this.TPS + this.TVQ;
        return this.total;
    }


    
    public String getTotalFormatted() {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(getTotal());
    }
    
    public Adherent getAdherent() {
        return this.adherent;
    }
    
    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }
    
    public Vente getVente() {
        return this.vente;
    }
    
    public void setVente(Vente vente) {
        this.vente = vente;
    }
    
    public void setPaiement(Paiement paiement){
        this.paiement = paiement;
    }
    
    public Paiement getPaiement(){
        return this.paiement;
    }
    
    public Location getLocation() {
        return this.location;
    }
    
    public void setLocation(Location location) {
        this.location = location;
    }
/*
    public double calculerSousTotal(){
        totalSansTax = 0;
        if(this.vente != null){
            totalSansTax = totalSansTax + this.vente.getTotalVente();
        }
        if(this.location != null){
            totalSansTax = totalSansTax + this.location.getTotalLocation();
        }
        return totalSansTax;
    }
    */
    public double calculerTPS(double sousTotal){
        return (double)Math.round(sousTotal * 0.05 * 0.998*100)/100 ;
    }
    
    public double calculerTVQ(double sousTotal){
        return (double)Math.round(sousTotal * 0.998*100)/100 ;
    }
    
  /*  public double calculerTotal() {
        return calculerSousTotal() + calculerTPS() + calculerTVQ();
    }*/
    
    
   /* public void enregistrerTransaction(){
        
    }*/

}
