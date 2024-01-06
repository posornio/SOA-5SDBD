package fr.insa.mas.ValideurManager.Model;


public class Valideur {
    private int id;
    private String nom;
    private String prenom;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Valideur(int id, String nom, String prenom) {
        super();
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Valideur() {
        super();
    }
    public Valideur(String nom, String prenom) {
        super();
        this.nom = nom;
        this.prenom = prenom;
    }

}