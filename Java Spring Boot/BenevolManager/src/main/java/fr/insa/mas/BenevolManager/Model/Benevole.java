package fr.insa.mas.BenevolManager.Model;


public class Benevole {
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

    public Benevole(int id, String nom, String prenom) {
        super();
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Benevole() {
        super();
    }
    public Benevole(String nom, String prenom) {
        super();
        this.nom = nom;
        this.prenom = prenom;
    }

}