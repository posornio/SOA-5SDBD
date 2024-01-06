package fr.insa.soa.Rest;

import java.util.ArrayList;

public class Etudiant {
	private int id;
	private String nom;
	private String prenom;
	private Binome binome;
	private Stage stage;
	private ArrayList<Link> links = new ArrayList<Link>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Binome getBinome() {
		return binome;
	}

	public void setBinome(Binome binome) {
		this.binome = binome;
	}

	public void addLink(String uri, String rel, String methode) {
		Link link = new Link();
		link.setUri(uri);
		link.setRel(rel);
		link.setMethode(methode);
		links.add(link);
	}

	public ArrayList<Link> getLinks() {
		return links;
	}
	
	
}
