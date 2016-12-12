/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;


/**
 *
 * @author Melanie
 */
public class CatalogueProduits {
    
    private ObservableList<Article> listeArticles;
    private ObservableList<Film> listeFilms;
    
    // Mappe les codes article à des objets Article
    private ObservableMap<String, Article> mapCodeArticles = FXCollections.observableHashMap();
    
    // Mappe les codes article à des objets Film
    private ObservableMap<String, Film> mapCodesFilm = FXCollections.observableHashMap();
    private ObservableMap<String, Film> mapTitresFilm = FXCollections.observableHashMap();
    
    private CatalogueProduits(){
        
    }
    
    private static CatalogueProduits INSTANCE = new CatalogueProduits();
    
    public static CatalogueProduits getInstance(){
        return INSTANCE;
    }
    
    // Rendre la méthode indépendante de l'implémentation de Article
    public void ajouterArticle(Article article) {
        listeArticles.add(article);
        mapCodeArticles.put(article.getCodeArticle(), article);
    }
    
    public void ajouterFilm(Film film) {
        listeFilms.add(film);
        mapCodesFilm.put(film.getCodeArticle(), film);
        mapTitresFilm.put(film.getTitre(), film);
    }
    
    // Si on ne veut pas ajouter les articles un par un..
    public void setListeArticles(ObservableList<Article> listeArticles) {
        this.listeArticles = listeArticles;
        for (Article article:listeArticles)
        {
            mapCodeArticles.put(article.getCodeArticle(), article);
        }
    }
    
    public void setListeFilms(ObservableList<Film> listeFilms) {
        this.listeFilms = listeFilms;
        for (Film film:listeFilms)
        {
            mapCodesFilm.put(film.getCodeArticle(), film);
            mapTitresFilm.put(film.getTitre(), film);
        }
    }
    
    public Article getArticle(String codeArticle){     
        return mapCodeArticles.get(codeArticle);
    }
    
    public Film getFilmByCode(String codeFilm) {
        return mapCodesFilm.get(codeFilm);
    }
    
    public Film getFilmByTitre(String titreFilm) {
        return mapTitresFilm.get(titreFilm);
    }
    
}
