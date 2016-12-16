/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author cheikh
 */
public class LogVideoclub {
    
    private ObservableList<LogEntry> entreesLog = FXCollections.observableArrayList();
    
    private boolean soldeUpdated = false;
    
    public ObservableList<LogEntry> getEntreesLog() {
        return entreesLog;
    }

    public void setEntreesLog(ObservableList<LogEntry> entreesLog) {
        this.entreesLog = entreesLog;
    }
    
    public LogVideoclub(){
        this.soldeUpdated = false;
    }
    
    public void updateRetards(String dateString) {
        ajouterEntree("Update", "System", dateString, "Mise à jour des soldes.");
    } 
    
    public void enregistrerTransaction(Transaction transaction, String nomEmploye ) {
        String details = "";
        
        if (transaction.getLocation() != null) {
            details += String.format("Adhérent %1$s : Location (%2$s) ", transaction.getAdherent().getNom(), transaction.getLocation().getTotalFormatted());
            if (transaction.getVente() != null) {
                details += String.format(", Vente (%s)", transaction.getVente().getTotalFormatted());
            }
        }
        else {
            if (transaction.getVente() != null) {
                details += String.format("Vente (%s)", transaction.getVente().getTotalFormatted());
            }
        }
        
        LogEntry entry = new LogEntry("Transaction", nomEmploye, details);
        
        this.entreesLog.add(entry);
    }
    
    public void enregistrerRetour(String nomEmploye, String nomAdherent, Film film) {
        String details = String.format("%1$s, %2$s (%3$s)", nomAdherent, film.getCodeArticle(), film.getTitre());
        
        LogEntry entry = new LogEntry("Retour", nomEmploye, details);
        
        this.entreesLog.add(entry);
    }

    public void enregistrerNouvelAdherent(String nomEmploye,Adherent nouveau) {
        String details = nouveau.getNom();
        
        LogEntry entry = new LogEntry("Inscription", nomEmploye, details);
        
        this.entreesLog.add(entry);
    }
    
    public void ajouterEntree(String type, String nomEmploye, String dateString, String details) {
        
        LocalDateTime date = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm"));
        
        LogEntry entry = new LogEntry(type, nomEmploye, date, details);
        
        this.entreesLog.add(entry);
    }

    public boolean getSoldeUpdated() {
        return this.soldeUpdated;
    }
    
    public void setSoldeUpdated(boolean value) {
        this.soldeUpdated = value;
    }
     
}
