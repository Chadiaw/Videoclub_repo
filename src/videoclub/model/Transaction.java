/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub.model;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
/**
 *
 * @author cheik
 */
public class Transaction {
    
    private LocalDateTime date;
    private Vente vente;
    private Location location;
    private Paiement paiement;
    private Adherent adherent;
    private double total;

    public Transaction(){
        this.adherent = null;
        this.vente = null;
        this.location = null;
        this.paiement = null;
        this.date = LocalDateTime.now(); 
        this.total = 0;
        
    }
    
    public LocalDateTime getDate(){
        return date;
    }
    
    public double getTotal() {
        this.total = 0;
        
        if(this.vente != null){
            total += this.vente.getTotalVente();
        }
        if(this.location != null){
            total += this.location.getTotalLocation();
        }
        return total;

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
    
    public LigneLocation getLigneLocation(String codeArticle){
        return this.getLocation().findLigneLocation(codeArticle);
    }

}
