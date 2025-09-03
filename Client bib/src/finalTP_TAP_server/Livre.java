package finalTP_TAP_server;

import java.io.Serializable;

public class Livre implements Serializable {
    private int id;
    private String titre;
    private String auteur;
    private String commentaire;

    public Livre() {}

    public Livre(String titre, String auteur, String commentaire) {
        this.titre = titre;
        this.auteur = auteur;
        this.commentaire = commentaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }


    @Override
    public String toString() {
        return "Livre{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", commentaire='" + commentaire + '\'' +
                '}';
    }
}

