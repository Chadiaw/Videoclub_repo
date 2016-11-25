/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub.model;

/**
 *
 * @author cheik
 */
import java.util.HashMap;
import java.util.Map;

public class Employe {

    private Employe(String username) {
        this.username = username;
        this.nom = username;
    }
    
    public Employe(String username, String nom) {
        this.username = username;
        this.nom = nom;
    }
    
    private final String username;

    public String getUsername() {
        return username;
    }
    //private String email = "";
    //private String numero = "";
    private String nom = "";

    public String getNom() {
        return nom;
    }

    /**
     * @param nom Nom de l'employe 
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}
