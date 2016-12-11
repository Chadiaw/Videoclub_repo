/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub.model;

/**
 *
 * @author Melanie
 */
public class Exemplaire {
    
    private int codeExemplaire;
    private boolean enLocation;
    
    public Exemplaire(int codeExemplaire){
        this.codeExemplaire = codeExemplaire;
        this.enLocation = false;   
    }
    
    public boolean getEnLocation(){
        return this.enLocation;
    }
    
    public int getCodeExemplaire(){
        return this.codeExemplaire;
    }
           
    
}
