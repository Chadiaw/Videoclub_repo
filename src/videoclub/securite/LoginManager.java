/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub.securite;

/**
 *
 * @author cheik
 */
import java.util.HashMap;
import java.util.Map;

public class LoginManager {
    
    // Mappe des noms d'utilisateur a des mots de passe
    private static final Map<String, String> EMPLOYES_CREDENTIALS = new HashMap<String, String>();
    
    /***
     * Valide les identifiants
     * @param username Nom d'utilisateur
     * @param password Mot de passe
     * @return vrai si le couple username-password existe dans la table.
     */
    public static boolean valider(String username, String password){
        String motDePasseValide = EMPLOYES_CREDENTIALS.get(username);
        return motDePasseValide != null && motDePasseValide.equals(password);
    }
    
    public static void addCredentials(String username, String password) {
        EMPLOYES_CREDENTIALS.put(username, password);
    }
}
