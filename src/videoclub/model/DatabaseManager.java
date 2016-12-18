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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import videoclub.securite.LoginManager;
import videoclub.model.LogEntry;

public class DatabaseManager {

    // Chemin du fichier de base de donnees SQLite
    final static String DATABASE_PATH = "data/videoDB.db";

    /**
     * Retourne la liste des employes stockés dans la base de donnees.
     *
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM EMPLOYE;");
            while (rs.next()) {
                Employe employe = new Employe(rs.getString("username"), rs.getString("name"));
                LoginManager.addCredentials(employe.getUsername(), rs.getString("password"));
                listeEmployes.add(employe);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return listeEmployes;
    }

    /**
     * Retourne la liste d'adherents stockée dans la base de données
     *
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM ADHERENT;");
            while (rs.next()) {
                Adherent adherent = new Adherent(rs.getString("name"), rs.getString("telephone"),
                        rs.getString("address"), rs.getDouble("solde"),
                        rs.getInt("code"));
                listeAdherents.add(adherent);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM ARTICLE;");
            while (rs.next()) {
                Article article = new Article(rs.getString("code"), rs.getString("descriptif"),
                        rs.getDouble("prix"), (rs.getInt("achetable") == 1));
                listeArticles.add(article);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM FILM;");
            while (rs.next()) {
                Film film = new Film(rs.getString("code"), rs.getString("descriptif"),
                        rs.getDouble("prix"), (rs.getInt("achetable") == 1), rs.getString("titre"),
                        rs.getString("genre"), (rs.getInt("nouveaute") == 1), rs.getString("synopsis"),
                        rs.getString("type"), rs.getInt("annee"));
                listeFilms.add(film);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM LOCATIONS;");
            while (rs.next()) {

                LigneLocation ligne = new LigneLocation(rs.getString("code"), rs.getString("adherent"),
                        rs.getString("dateLouee"), rs.getString("dateRetour"));

                listeLocations.add(ligne);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return listeLocations;
    }

    public static LogVideoclub chargerLog() {
        LogVideoclub logVideoclub = new LogVideoclub();

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = String.format("jdbc:sqlite:%s", DATABASE_PATH);
            c = DriverManager.getConnection(url);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM LOG;");
            while (rs.next()) {
                if (rs.getString("employe").equals("System")) {
                    LocalDateTime lastUpdate = LocalDateTime.parse(rs.getString("date"), DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm"));
                    
                    logVideoclub.setLastUpdate(lastUpdate.toLocalDate());
                }
                logVideoclub.ajouterEntree(rs.getString("type"), rs.getString("employe"), rs.getString("date"), rs.getString("details"));
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return logVideoclub;
    }

    public static void sauvegarderAdherents(ObservableList<Adherent> listeAdherents) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = String.format("jdbc:sqlite:%s", DATABASE_PATH);
            c = DriverManager.getConnection(url);
            c.setAutoCommit(false);

            // Supprimer entrées de la table
            stmt = c.createStatement();
            String sql = "DELETE from ADHERENT;";
            stmt.executeUpdate(sql);
            c.commit();

            // Ajouter entrées
            for (Adherent adherent : listeAdherents) {
                stmt = c.createStatement();
                sql = String.format("INSERT INTO ADHERENT (NAME,TELEPHONE,ADDRESS,SOLDE,CODE) "
                        + "VALUES ('%1$s', '%2$s', '%3$s', %4$.2f, %5$d);",
                        adherent.getNom(), adherent.getNumeroTelephone(), adherent.getAdresse(), adherent.getSolde(), adherent.getCodeSecret());
                stmt.executeUpdate(sql);
                c.commit();

            }

            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public static void sauvegarderLocations(ObservableList<LigneLocation> locationsEnCours) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = String.format("jdbc:sqlite:%s", DATABASE_PATH);
            c = DriverManager.getConnection(url);
            c.setAutoCommit(false);

            // Supprimer entrées de la table
            stmt = c.createStatement();
            String sql = "DELETE from LOCATIONS;";
            stmt.executeUpdate(sql);
            c.commit();

            // Ajouter entrées
            for (LigneLocation ligne : locationsEnCours) {
                stmt = c.createStatement();
                sql = String.format("INSERT INTO LOCATIONS (CODE,ADHERENT,DATELOUEE,DATERETOUR) "
                        + "VALUES ('%1$s', '%2$s', '%3$s', '%4$s');",
                        ligne.getCodeFilm(), ligne.getNomAdherent(),
                        ligne.getDateLouee().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        ligne.getDateRetour().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                stmt.executeUpdate(sql);
                c.commit();

            }

            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public static void sauvegarderArticles(CatalogueProduits catalogue) {
        ObservableList<Article> listeArticles = catalogue.getListeArticles();

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = String.format("jdbc:sqlite:%s", DATABASE_PATH);
            c = DriverManager.getConnection(url);
            c.setAutoCommit(false);

            // Supprimer entrées de la table ARTICLE
            stmt = c.createStatement();
            String sql = "DELETE from ARTICLE;";
            stmt.executeUpdate(sql);
            c.commit();

            // Ajouter les articles mis à jour
            for (Article article : listeArticles) {
                stmt = c.createStatement();
                sql = String.format("INSERT INTO ARTICLE (CODE,DESCRIPTIF,PRIX,ACHETABLE) "
                        + "VALUES (%1$d, '%2$s', %3$.2f, %4$d);",
                        Integer.parseInt(article.getCodeArticle()),
                        article.getDescriptif(),
                        article.getPrix(),
                        article.isAchetable() ? 1 : 0
                );
                stmt.executeUpdate(sql);
                c.commit();

            }

            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public static void sauvegarderFilms(CatalogueProduits catalogue) {
        ObservableList<Film> listeFilms = catalogue.getListeFilms();
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = String.format("jdbc:sqlite:%s", DATABASE_PATH);
            c = DriverManager.getConnection(url);
            c.setAutoCommit(false);

            // Supprimer entrées de la table film
            stmt = c.createStatement();
            String sql = "DELETE from FILM;";
            stmt.executeUpdate(sql);
            c.commit();

            // Ajouter les films mis à jour
            for (Film film : listeFilms) {
                stmt = c.createStatement();
                sql = String.format("INSERT INTO FILM (CODE,DESCRIPTIF,PRIX,ACHETABLE,TITRE,GENRE,NOUVEAUTE,SYNOPSIS,TYPE,ANNEE) "
                        + "VALUES (%1$d, '%2$s', %3$.2f, %4$d, '%5$s', '%6$s', %7$d, '%8$s', '%9$s', %10$d);",
                        Integer.parseInt(film.getCodeArticle()),
                        film.getDescriptif(),
                        film.getPrix(),
                        film.isAchetable() ? 1 : 0,
                        film.getTitre(),
                        film.getGenre(),
                        film.isNouveaute() ? 1 : 0,
                        film.getSynopsis(),
                        film.getType(),
                        film.getAnnee()
                );
                stmt.executeUpdate(sql);
                c.commit();

            }
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public static void sauvegarderLogVideoclub(LogVideoclub logVideoclub) {
        ObservableList<LogEntry> entreesLog = logVideoclub.getEntreesLog();

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = String.format("jdbc:sqlite:%s", DATABASE_PATH);
            c = DriverManager.getConnection(url);
            c.setAutoCommit(false);

            // Supprimer entrées de la table
            stmt = c.createStatement();
            String sql = "DELETE from LOG;";
            stmt.executeUpdate(sql);
            c.commit();

            // Ajouter nouvelles (et vieilles) entrées
            for (LogEntry entry : entreesLog) {
                stmt = c.createStatement();
                sql = String.format("INSERT INTO LOG (Date,Type,Employe,Details) "
                        + "VALUES ('%1$s', '%2$s', '%3$s', '%4$s');",
                        entry.getDateFormatted(),
                        entry.getType(),
                        entry.getNomEmploye(),
                        entry.getDetails()
                );
                stmt.executeUpdate(sql);
                c.commit();

            }

            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

}
