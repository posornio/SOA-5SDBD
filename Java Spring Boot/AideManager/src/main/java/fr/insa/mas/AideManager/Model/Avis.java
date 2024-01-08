package fr.insa.mas.AideManager.Model;

public class Avis {
    private int id;
    //note sur 5
    private int note;
    private String commentaire;

    private int aide_reference;


    public int getNote() {
        return note;
    }
    public void setNote(int note) {
        this.note = note;
    }
    public String getCommentaire() {
        return commentaire;
    }
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    public int getAide_reference() {
        return aide_reference;
    }
    public void setAide_reference(int aide_reference) {
        this.aide_reference = aide_reference;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Avis(int id, int note, String commentaire, int aide_reference) {
        this.id = id;
        this.note = note;
        this.commentaire = commentaire;
        this.aide_reference = aide_reference;
    }

    public Avis(int note, String commentaire, int aide_reference) {
        this.note = note;
        this.commentaire = commentaire;
        this.aide_reference = aide_reference;
    }
    public Avis() {
    }
}
