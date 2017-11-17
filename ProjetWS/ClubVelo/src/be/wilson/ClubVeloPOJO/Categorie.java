package be.wilson.ClubVeloPOJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Categorie implements Serializable{
	private static final long serialVersionUID = 8607474364792878892L;
	private int id;
	private int nbMembres;
	private Personne resp;
	protected List<Membre> listeMembres;
	
	public Categorie(int id, int nbMembres, Responsable resp) {
		this.id = id;
		this.nbMembres = nbMembres;
		this.resp = resp;
		listeMembres = new ArrayList<>();
	}
	
	public List<Membre> getListeMembres() {
		return listeMembres;
	}

	public void setListeMembres(List<Membre> listeMembres) {
		this.listeMembres = listeMembres;
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
	
	public abstract void AjouterMembre(Membre m);
	
	public abstract void SupprimerMembre(Membre m);
	
	public abstract boolean equals(Membre m);
	
	public abstract String getSimpleName();
}
