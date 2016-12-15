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

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import videoclub.securite.LoginManager;

public class DatabaseManager {
    
    // Chemin du fichier de base de donnees SQLite
    final static String DATABASE_PATH = "data/videoDB.db"; 
    
    /**
     * Retourne la liste des employes stockés dans la base de donnees.
     * @return liste des employes
     */
    public static ArrayList<Employe> chargerEmployes() {
        
        ArrayList<Employe> listeEmployes = new ArrayList<>();
        
        Connection c = null;
        Statement stmt = null;
        try {
          Class.forName("org.sqlite.JDBC");
          String url = String.format("jdbc:sqlite:%s", DATABASE_PATH);
          c = DriverManager.getConnection(url);
          c.setAutoCommit(false);
          
          stmt = c.createStatement();
          ResultSet rs = stmt.executeQuery( "SELECT * FROM EMPLOYE;" );
          while ( rs.next() ) {
             Employe employe = new Employe(rs.getString("username"), rs.getString("name"));
              LoginManager.addCredentials(employe.getUsername(), rs.getString("password"));
              listeEmployes.add(employe);
          }
          rs.close();
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        return listeEmployes;
    } 
    
    /**
     * Retourne la liste d'adherents stockée dans la base de données
     * @return liste des adherents et leurs informations
     */
    public static ObservableList<Adherent> chargerAdherents() {
        ObservableList<Adherent> listeAdherents = FXCollections.observableArrayList();
        
        Connection c = null;
        Statement stmt = null;
        try {
          Class.forName("org.sqlite.JDBC");
          String url = String.format("jdbc:sqlite:%s", DATABASE_PATH);
          c = DriverManager.getConnection(url);
          c.setAutoCommit(false);
          
          stmt = c.createStatement();
          ResultSet rs = stmt.executeQuery( "SELECT * FROM ADHERENT;" );
          while ( rs.next() ) {
             Adherent adherent = new Adherent(rs.getString("name"), rs.getString("telephone"),
                                              rs.getString("address"), rs.getDouble("solde"),
                                              rs.getInt("code"));
             listeAdherents.add(adherent);
          }
          rs.close();
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        return listeAdherents;
    }
    
    public static ObservableList<Article> chargerArticles() {
        ObservableList<Article> listeArticles = FXCollections.observableArrayList();
        
        Connection c = null;
        Statement stmt = null;
        try {
          Class.forName("org.sqlite.JDBC");
          String url = String.format("jdbc:sqlite:%s", DATABASE_PATH);
          c = DriverManager.getConnection(url);
          c.setAutoCommit(false);
          
          stmt = c.createStatement();
          ResultSet rs = stmt.executeQuery( "SELECT * FROM ARTICLE;" );
          while ( rs.next() ) {
             Article article = new Article(rs.getString("code"), rs.getString("descriptif"),
                                              rs.getDouble("prix"), (rs.getInt("achetable")==1));
             listeArticles.add(article);
          }
          rs.close();
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        return listeArticles;
    }
    
    public static ObservableList<Film> chargerFilms() {
        ObservableList<Film> listeFilms = FXCollections.observableArrayList();
        
        Connection c = null;
        Statement stmt = null;
        try {
          Class.forName("org.sqlite.JDBC");
          String url = String.format("jdbc:sqlite:%s", DATABASE_PATH);
          c = DriverManager.getConnection(url);
          c.setAutoCommit(false);
          
          stmt = c.createStatement();
          ResultSet rs = stmt.executeQuery( "SELECT * FROM FILM;" );
          while ( rs.next() ) {
             Film film = new Film(rs.getString("code"), rs.getString("descriptif"),
                                              rs.getDouble("prix"), (rs.getInt("achetable")==1), rs.getString("titre"),
                                              rs.getString("genre"), (rs.getInt("nouveaute")==1), rs.getString("synopsis"),
                                              rs.getString("type"), rs.getInt("annee"));
             listeFilms.add(film);
          }
          rs.close();
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        return listeFilms;
    }

    public static ObservableList<LigneLocation> chargerLocations() {
         ObservableList<LigneLocation> listeLocations = FXCollections.observableArrayList();
        
        Connection c = null;
        Statement stmt = null;
        try {
          Class.forName("org.sqlite.JDBC");
          String url = String.format("jdbc:sqlite:%s", DATABASE_PATH);
          c = DriverManager.getConnection(url);
          c.setAutoCommit(false);
          
          stmt = c.createStatement();
          ResultSet rs = stmt.executeQuery( "SELECT * FROM LOCATIONS;" );
          while ( rs.next() ) {
             
             LigneLocation ligne = new LigneLocation(rs.getString("code"), rs.getString("adherent"), 
                     rs.getString("dateLouee"), rs.getString("dateRetour"));
             
             listeLocations.add(ligne);
          }
          rs.close();
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        return listeLocations;
    }
    
    
}
