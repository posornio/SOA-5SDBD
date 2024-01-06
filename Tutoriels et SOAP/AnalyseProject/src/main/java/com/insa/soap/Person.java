package com.insa.soap;
import java.util.Date;

public class Person {
	String nom;
	String prenom;
	int status;
	Date dateNaissance;
	int personId;
	
	public Person(String nom, String prenom, int status, Date dateN, int personId){
		this.nom= nom;
		this.prenom=prenom;
		this.status=status;
		this.dateNaissance= dateN;
		this.personId= personId ;
	}
	
	public Person() {
	}
	
	public void setPerson(String nom, String prenom, int status, int personId){
		this.nom= nom;
		this.prenom=prenom;
		this.status=status;
		this.personId= personId ;
	}
	
	public Person getPerson() {
		return this ;
	}
	
	public void setName(String nom) {
		this.nom=nom ;
	}
	
	public String getName() {
		return this.nom ;
	}
	
	public void setPrenom(String prenom) {
		this.prenom= prenom ;
	}
	
	public String getPrenom() {
		return this.prenom ;
	}
	
	
	
	public void setStatus(int status) {
		this.status =status ;
	}
	
	public int getStatus() {
		return this.status ;
	}
	
	public void setDate(Date dateN) {
		this.dateNaissance = dateN ;
	}
	
	public Date getDate() {
		return this.dateNaissance ;
	}
	
	
	public void setPersonID(int personID) {
		this.personId=personID ;
	}
	
	public int getPersonID() {
		return this.personId ;
	}
	
	
}




