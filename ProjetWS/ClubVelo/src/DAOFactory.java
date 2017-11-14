import java.sql.Connection;
import be.wilson.ClubVeloConnection.DBConnection;
import be.wilson.ClubVeloDAO.*;
import be.wilson.ClubVeloPOJO.*;

public class DAOFactory extends AbstractDAOFactory {
	protected static final Connection connec = DBConnection.getInstance();
	
	public DAO<Membre> getMembreDAO() {
		return new MembreDAO(connec);
	}

	public DAO<Responsable> getResponsableDAO() {
		return new ResponsableDAO(connec);
	}

	public DAO<Tresorier> getTresorierDAO() {
		return new TresorierDAO(connec);
	}

	public DAO<Voiture> getVoitureDAO() {
		return new VoitureDAO(connec);
	}

	public DAO<Adresse> getAdresseDAO() {
		return new AdresseDAO(connec);
	}

	public DAO<Categorie> getVTTDAO() {
		return new VTTDAO(connec);
	}

	public DAO<Categorie> getCycloDAO() {
		return new CycloDAO(connec);
	}

	public DAO<Balade> getBaladeDAO() {
		return new BaladeDAO(connec);
	}

}
