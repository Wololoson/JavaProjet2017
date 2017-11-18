package be.wilson.ClubVeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import be.wilson.ClubVeloPOJO.Adresse;
import be.wilson.ClubVeloPOJO.Balade;
import be.wilson.ClubVeloPOJO.Membre;
import be.wilson.ClubVeloPOJO.Responsable;
import be.wilson.ClubVeloPOJO.Voiture;

public class VoitureDAO extends DAO<Voiture> {
	private long generatedId;
	DAO<Membre> mbrDAO = adf.getMembreDAO();
	DAO<Balade> balDAO = adf.getBaladeDAO();
	
	public VoitureDAO(Connection conn){
		super(conn);
		generatedId = 0;
	}
	
	public boolean create(Voiture obj) {
		PreparedStatement stmt = null;
		try{
			stmt = connect.prepareStatement("INSERT INTO Voiture(numImmat, nbPlaces, idPers) "
										  + "VALUES(?, ?, ?)");
			stmt.setString(1, obj.getNumImmat());
			stmt.setInt(2, obj.getNbPlaces());
			stmt.setLong(3, obj.getChauffeur().getId());
			stmt.executeUpdate();
			
			generatedId = stmt.getGeneratedKeys().getInt(1);

			super.close(stmt);
			return true;
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean delete(Voiture obj){
		PreparedStatement stmt = null;
		try{
			stmt = connect.prepareStatement("DELETE FROM Voiture "
					  					  + "WHERE numImmat = ?");
			stmt.setString(1, obj.getNumImmat());
			stmt.executeUpdate();
			
			generatedId = stmt.getGeneratedKeys().getInt(1);

			super.close(stmt);
			return true;
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean update(Voiture obj){
		PreparedStatement stmt = null;
		try{
			stmt = connect.prepareStatement("UPDATE Voiture "
								  		  + "SET numImmat = ?, nbPlaces = ?, idPers = ?");
			stmt.setString(1, obj.getNumImmat());
			stmt.setInt(2, obj.getNbPlaces());
			stmt.setLong(3, obj.getChauffeur().getId());
			stmt.executeUpdate();
			
			generatedId = stmt.getGeneratedKeys().getInt(1);
			super.close(stmt);
			
			return true;
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public Voiture find(String numImmat){
		Voiture voit = new Voiture();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Voiture V "
														   + "INNER JOIN Eclat_Bal_Disp E ON V.numImmat = E.numImmat "
														   + "AND V.idPers = E.idPers "
														   + "WHERE numImmat = " + numImmat);
			
			voit = new Voiture(numImmat, 
							   mbrDAO.find(result.getInt("idPers")), 
							   result.getInt("nbPlaces"), 
							   balDAO.find(result.getInt("idBal")));
			
			super.close(result);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return voit;
	}
	
	public long getGeneratedId(){
		return generatedId;
	}

	@Override
	public Voiture find(int id) {
		return null;
	}

	@Override
	public List<Voiture> findAll() {
		List<Voiture> tres = new ArrayList<Voiture>();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Voiture V "
														   + "INNER JOIN Eclat_Bal_Disp E ON V.numImmat = E.numImmat "
														   + "AND V.idPers = E.idPers "
														   + "INNER JOIN Personne P ON V.idPers = P.idPers "
														   + "INNER JOIN Membre M ON P.idPers = M.idPers");
			while(result.next()){
				tres.add(new Voiture(result.getString("numImmat"), 
									 new Membre(result.getInt("idPers"),
											 result.getString("nom"),
											 result.getString("prenom"),
											 result.getDate("dateNaiss"),
											 new Adresse(result.getInt("idAdr"), 
														 result.getString("rue"),
														 result.getInt("numero"), 
														 result.getString("codePost"),
														 result.getString("ville"),
														 result.getString("pays")),
											 result.getFloat("cotisation"),
											 result.getString("motDePasse")),
									 result.getInt("nbPlaces"), 
									 balDAO.find(result.getInt("idBal"))));
			}
			super.close(result);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return tres;
	}
	
	public List<Voiture> getVoitList(int id){
		List<Voiture> voitList = new ArrayList<>();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Eclat_Bal_Disp "
														   + "WHERE idBal = " + id);
			while(result.next()){
				voitList.add(find(result.getString("numImmat")));
			}
			super.close(result);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return voitList;
	}
}
