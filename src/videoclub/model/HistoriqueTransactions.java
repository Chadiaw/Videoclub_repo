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
    
    private int transactionIndex = 1;
    
    private ObservableList<Transaction> transactions = FXCollections.observableArrayList();
    
    private HistoriqueTransactions() {
        
    }
    
    private static HistoriqueTransactions INSTANCE = new HistoriqueTransactions();
    
    public static  HistoriqueTransactions getInstance() {
        return INSTANCE;
    }

    public int getTransactionsIndex() {
        return this.transactionIndex;
    }
    
    public void setTransactionsIndex(int nombreTransactions) {
        this.transactionIndex = nombreTransactions;
    }

    public ObservableList<Transaction> getTransactions() {
        return transactions;
    }
    
    public void enregistrer(Transaction transaction) {
        this.transactions.add(transaction);
        transactionIndex++;
    }
    
    public void setTransactions(ObservableList<Transaction> listeTransactions) {
        this.transactions = listeTransactions;
    }
    
    public Transaction findTransaction(String codeArticle){
        for(Transaction trans : transactions){
            if(trans.getLigneLocation(codeArticle) != null){
                return trans;
            }
        }
        return null;
    }
    
}
