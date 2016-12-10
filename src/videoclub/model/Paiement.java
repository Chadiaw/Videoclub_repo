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
    
    private double total;
    private double monnaie;
    
    public Paiement(double total){
        this. total = total;
    }
    
    public void setArgentTendu(double argent){
        this.monnaie = argent - total;
    }
    
    public String getTotalFormatted() {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(this.total);
    }
    
    public String getMonnaieFormatted() {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(this.monnaie);
    }
    
}
