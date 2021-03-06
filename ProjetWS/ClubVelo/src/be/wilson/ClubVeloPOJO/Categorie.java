package be.wilson.ClubVeloPOJO;

import java.io.Serializable;

public abstract class Categorie implements Serializable{
	private static final long serialVersionUID = 8607474364792878892L;
	private int id;
	private int nbMembres;
	private Personne resp;
	
	public Categorie(int id, int nbMembres, Responsable resp) {
		this.id = id;
		this.nbMembres = nbMembres;
		this.resp = resp;
	}

	public Personne getResp() {
		return resp;
	}

	public void setResp(Personne resp) {
		this.resp = resp;
	}
	
	public Categorie(){ }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNbMembres() {
		return nbMembres;
	}

	public void setNbMembres(int nbMembres) {
		this.nbMembres = nbMembres;
	}
	
	public abstract String toString();
	
	public abstract boolean equals(Membre m);
	
	public abstract String getSimpleName();
}
