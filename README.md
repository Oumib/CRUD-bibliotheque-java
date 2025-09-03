# Projet Bibliothèque Java Full Stack

## Description
Application Desktop Java pour gérer une bibliothèque : livres, auteurs et utilisateurs.  
Fonctionnalités : CRUD complet, inscription et connexion des utilisateurs, recherche, filtres et statistiques simples.  
Projet conçu pour démontrer les compétences Full Stack avec Java, MySQL et interface utilisateur JavaFX/Swing.  

## Fonctionnalités
- Gestion complète des livres et auteurs (Fonctionnalités CRUD complètes).  
- Inscription et connexion sécurisée des utilisateurs.  
- Recherche et filtres sur les livres et auteurs.  
- Statistiques simples pour le suivi des livres.  

## Technologies utilisées
- Java SE (Swing/JavaFX)  
- MySQL Server  
- JDBC pour la connexion à la base de données  
- RMI
- JavaFX/Swing

## Structure du projet

## Instructions pour exécuter le projet
1. Assurez-vous que MySQL Server est installé et en fonctionnement.  
2. Créez la base de données et les tables nécessaires.  
3. Compilez le projet :
#```bash
javac -d bin src/**/*.java 
Lancez le serveur :

java -cp bin finalTP_TAP_server.Serveur


Lancez le client :

java -cp bin client.Client
