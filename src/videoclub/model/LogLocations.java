/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author cheikh
 */
public class LogLocations {
    
    private ObservableList<LigneLocation> locationsEnCours = FXCollections.observableArrayList();

    public ObservableList<LigneLocation> getLocationsEnCours() {
        return locationsEnCours;
    }
    
    public void setLocationsEnCours(ObservableList<LigneLocation> locationsEnCours) {
        this.locationsEnCours = locationsEnCours;
    }
    
    public void ajouterLocation(LigneLocation ligne) {
        locationsEnCours.add(ligne);
    }
    
    public LigneLocation findLocation(String codeFilm){
        for(LigneLocation loc: locationsEnCours){
            if(loc.getCodeFilm().equals(codeFilm)){
                return loc;
            }
        }
        return null;
    }
    
    public void enleverLocation(LigneLocation location){
        locationsEnCours.remove(location);
    }
    
    
}
