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
public class HistoriqueTransactions {
    
    private static int transactionsCount = 0;
    
    private ObservableList<Transaction> transactions = FXCollections.observableArrayList();
    
    private HistoriqueTransactions() {
        
    }
    
    private static HistoriqueTransactions INSTANCE = new HistoriqueTransactions();
    
    public static  HistoriqueTransactions getInstance() {
        return INSTANCE;
    }

    public static int getTransactionsCount() {
        return transactionsCount;
    }

    public ObservableList<Transaction> getTransactions() {
        return transactions;
    }
    
    public void enregistrer(Transaction transaction) {
        this.transactions.add(transaction);
        transactionsCount++;
    }
    
    
}
