package be.wilson.ClubVeloPOJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import be.wilson.ClubVeloDAO.MembreDAO;
import be.wilson.ClubVeloFactory.AbstractDAOFactory;

public class Voiture implements Serializable {
	private static final long serialVersionUID = 8173001894596959567L;
	private int id;
	private Membre chauffeur;
	private List<Membre> passagers;
	private String numImmat;
	private int nbPlaces;
	private Balade bal;
	private AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	MembreDAO mbreDAO = (MembreDAO) adf.getMembreDAO();
	
	public Voiture(){}
	
	public Voiture(int id, String numImmat, Membre chauffeur,  int nbPlaces, Balade bal) {
		this.id = id;
		this.chauffeur = chauffeur;
		this.numImmat = numImmat;
		this.nbPlaces = nbPlaces;
		this.bal = bal;
		passagers = new ArrayList<>();
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Balade getBal() {
		return bal;
	}

	public void setBal(Balade bal) {
		this.bal = bal;
	}

	public int getNbPlaces() {
		return nbPlaces;
	}

	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}

	public Membre getChauffeur() {
		return chauffeur;
	}
	public void setChauffeur(Membre chauffeur) {
		this.chauffeur = chauffeur;
	}
	public List<Membre> getPassagers() {
		return passagers;
	}
	public void setPassagers(List<Membre> passagers) {
		this.passagers = passagers;
	}
	public String getNumImmat() {
		return numImmat;
	}
	public void setNumImmat(String numImmat) {
		this.numImmat = numImmat;
	}
	
	public void addMembre(Membre m){
		if(!passagers.contains(m))
			passagers.add(m);
	}
	
	public void removeMembre(Membre m){
		passagers.remove(m);
	}
	
	public boolean equals(Voiture v){
		if(this.getNumImmat() == v.getNumImmat())
			return true;
		else
			return false;
	}
}
