/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import videoclub.model.Transaction;

/**
 *
 * @author cheikh
 */
public class HistoriqueSession {

    private int idCourant;
    private ObservableList<EntreeHistoriqueSession> listeEntrees = FXCollections.observableArrayList();
    
    public HistoriqueSession() {
        this.idCourant = 1;
    }
    
    public ObservableList<EntreeHistoriqueSession> getListeEntrees() {
        return this.listeEntrees;
    }
    
    public void ajouterTransaction(Transaction transaction) {
        String details = "";
        if(transaction.getLocation() != null) {
            details += String.format("Location '%1$s' (%2$s $); ", transaction.getAdherent().getNom(), transaction.getLocation().getTotalFormatted());
        }
        
        if (transaction.getVente() != null) {
            details += String.format("Vente (%s $).", transaction.getVente().getTotalFormatted());
        }
        
        EntreeHistoriqueSession entree = new EntreeHistoriqueSession(idCourant, "Transaction", details);
        
        this.listeEntrees.add(entree);
        
        idCourant++;
    }
    
    protected class EntreeHistoriqueSession {
    
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty type;
    private final SimpleStringProperty date; 
    private final SimpleStringProperty details;
    
    public EntreeHistoriqueSession(int id, String type, String details) {
        this.id = new SimpleIntegerProperty(id);
        this.type = new SimpleStringProperty(type);
        this.details = new SimpleStringProperty(details);
        
        String dateEntree = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yy, HH:mm"));
        this.date = new SimpleStringProperty(dateEntree);
    }

        public int getId() {
            return id.get();
        }

        public String getType() {
            return type.get();
        }

        public String getDate() {
            return date.get();
        }

        public String getDetails() {
            return details.get();
        }
    
    
}

}
