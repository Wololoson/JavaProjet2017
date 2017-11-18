package be.wilson.ClubVeloPOJO;

import java.util.*;

import be.wilson.ClubVeloDAO.DAO;
import be.wilson.ClubVeloFactory.AbstractDAOFactory;

public class Calendrier {
	private List<Balade> cal;
	private AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	DAO<Balade> balDAO = adf.getBaladeDAO();

	public Calendrier() {
		this.cal = balDAO.findAll();
	}

	public List<Balade> getCal() {
		return cal;
	}

	public void setCal(List<Balade> cal) {
		this.cal = cal;
	}
	
	public void addBalade(Balade b){
		if(!cal.contains(b))
			cal.add(b);
	}
	
	public void removeBalade(Balade b){
		cal.remove(b);
	}
}
