package be.wilson.ClubVeloPOJO;

import java.io.Serializable;
import java.sql.Date;

public abstract class Personne  implements Serializable {
	private static final long serialVersionUID = -3480279736443429062L;
	protected long id;
	protected String nom;
	protected String prenom;
	protected Date dateNaiss;
	protected Adresse adr;
	protected String motDePasse;

	public Personne(long id, String nom, String prenom, Date dateNaiss, Adresse adr, String motDePasse) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaiss = dateNaiss;
		this.adr = adr;
		this.motDePasse = motDePasse;
	}
	
	public Personne(){}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public Date getDateNaiss() {
		return dateNaiss;
	}

	public void setDateNaiss(Date dateNaiss) {
		this.dateNaiss = dateNaiss;
	}
	
	public Adresse getAdr(){
		return adr;
	}
	
	public void setAdr(Adresse adr){
		this.adr = adr;
	}
	
	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	
	public String toString() {
		return String.format(nom.toUpperCase() + " " + prenom);
	}
}
