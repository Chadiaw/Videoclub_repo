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
    private Adherent adherent;
    private double total;
   
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
            /*
            additionner sousTotal Vente et Location
            multiplier par taxes
            retourner resultat
            */
        return total;
    }
    
    public String getTotalFormatted() {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(this.total);
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
    
    public double calculerTPS(){
        return (double)Math.round(this.totalSansTax * 0.05 * 0.998*100)/100 ;
    }
    
    public double calculerTVQ(){
        return (double)Math.round(this.totalSansTax * 0.998*100)/100 ;
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
*/
}
