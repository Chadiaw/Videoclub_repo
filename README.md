# Description

Projet réalisé dans le cadre du cours de Modélisation et Conception Orienté Objet. 

Le mandat est la réalisation d'un système de gestion de vidéoclub implémentant les opérations suivantes:
- Traitement de transactions (location de films, vente d'articles, retour de film).
- Inscription de nouveau membre.
- Gestion des membres : informations, mise à jour du solde (amendes, retards).
- Affichage des locations courantes, génération de rapport de retards. 
- Gestion de l'inventaire.

# Utilisation (Java 8 nécessaire)

Les fichiers nécessaires pour l'exécution se trouvent dans le répertoire *dist* : *Videoclub.jar* et les dossiers *data* et *lib*.

## Guide
- Vérifier que Java 8 est installé.
- Télécharger le dépôt git (format .zip) et en extraire le contenu. 
- Exécuter le fichier /dist/Videoclub.jar 

#### Notes
- L'employé Mr X (username: demo, password: demo) peut être utilisé pour tester.
- L'onglet inventaire n'est accessible qu'au gérant (username: admin, password: admin). 
- L'adhérent 'Adhérent Test' (Téléphone: 0000, Code secret: 0000) peut être utilisé pour tester la location.
- Codes d'article existant par défaut : 101, 102, 108. 
- Films par défaut : Avatar, TED, Coming To America, etc.
- Les articles et films disponibles sont modifiables via l'onglet 'Inventaire' (il faut être connecté en tant que gérant).

Afin de modifier la liste des employés et leurs identifiants, la table EMPLOYE de la base de données SQLite (dist/data/videoDB.db) doit être modifiée directement en utilisant un outil approprié.

# Développement
- En utilisant Netbeans (8.2), créer un projet JavaFX -> JavaFX FXML Application, choisir JDK 1.8 pour la plateforme JavaFx. Confirmer puis quitter Netbeans.
- A partir de l'explorateur de fichiers, supprimer le contenu du dossier src. 
- Ouvrir un terminal dans le dossier du projet et exécuter les commandes git suivantes:
```
git init
git remote add origin https://github.com/Chadiaw/Videoclub_repo.git
git fetch
git checkout -t origin/master
```
Il faut pour finir lier le driver SQLite au projet :
- Clic droit sur Librairies à partir de la fenêtre des Projets dans Netbeans, puis cliquer sur ajouter JAR.
- Sélectionner le fichier *src/sqlite-jdbc-3.8.11.2.jar*
- Sauvegarder. Redémarrer Netbeans. 
