/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub.model;

import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author cheikh
 */
public class LogVideoclub {
 
    private int transactionsIndex;

    public int getTransactionsIndex() {
        return transactionsIndex;
    }

    public void setTransactionsIndex(int transactionsIndex) {
        this.transactionsIndex = transactionsIndex;
    }

    
    private ObservableList<LogEntry> entreesLog = FXCollections.observableArrayList();
    
    public ObservableList<LogEntry> getEntreesLog() {
        return entreesLog;
    }

    public void setEntreesLog(ObservableList<LogEntry> entreesLog) {
        this.entreesLog = entreesLog;
    }
    
    public LogVideoclub(){
        
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
    
    public void enregistrerRetour(String nomEmploye, String nomAdhrent, Film film) {
        String details = String.format("%1$s, %2$s (%3$s)", nomAdhrent, film.getCodeArticle(), film.getTitre());
        
        LogEntry entry = new LogEntry("Retour", nomEmploye, details);
        
        this.entreesLog.add(entry);
    }
            
    class LogEntry {
        private String type; 
        private String nomEmploye;
        private LocalDateTime date;
        private String details;
        
        public LogEntry(String type, String employe, String details) {
            this.type = type;
            this.nomEmploye = employe;
            this.details = details;
            this.date = LocalDateTime.now();
        }
        
    }
}
