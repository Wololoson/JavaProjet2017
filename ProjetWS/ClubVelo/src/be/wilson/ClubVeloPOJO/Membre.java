package be.wilson.ClubVeloPOJO;

import java.sql.Date;
import java.util.List;

import be.wilson.ClubVeloDAO.CategorieDAO;
import be.wilson.ClubVeloDAO.MembreDAO;
import be.wilson.ClubVeloFactory.AbstractDAOFactory;

public class Membre extends Personne{
	private static final long serialVersionUID = 4199324936270699959L;
	private float cotisation;
	private List<Categorie> listeCat;
	private AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private CategorieDAO catDAO = (CategorieDAO) adf.getCatDAO();
	private MembreDAO mbreDAO = (MembreDAO) adf.getMembreDAO();
	
	public Membre(int id, String nom, String prenom, Date dateNaiss, Adresse adr, float cotisation, String motDePasse) {
		super(id, nom, prenom, dateNaiss, adr, motDePasse);
		this.cotisation = cotisation;
		listeCat = catDAO.getCatList(id);
	}

	public List<Categorie> getListeCat() {
		return listeCat;
	}

	public void setListeCat(List<Categorie> listeCat) {
		this.listeCat = listeCat;
	}

	public Membre(){}

	public float getCotisation() {
		return cotisation;
	}

	public void setCotisation(float cotisation) {
		this.cotisation = cotisation;
	}
	
	public void ajouterCat(int idCat) {
		Categorie c = catDAO.find((int)idCat);
		if(!listeCat.contains(c)) {
			listeCat.add(c);
			mbreDAO.addCat((int)this.id, idCat, 5f);
		}	
	}
}
