package be.wilson.ClubVeloDAO;

import java.sql.*;

import be.wilson.ClubVeloPOJO.Membre;

public class MembreDAO extends DAO<Membre>{
	AdresseDAO adrDAO = new AdresseDAO(connect);
	private long generatedId;
	
	public MembreDAO(Connection conn){
		super(conn);
		generatedId = 0;
	}
	
	public boolean create(Membre obj){
		PreparedStatement stmt = null;
		long idPers = 0;
		long idAdr = 0;
		try{
			adrDAO.create(obj.getAdr());
			idAdr = adrDAO.getGeneratedId();
			
			stmt = connect.prepareStatement("INSERT INTO Personne(nom, prenom, dateNaiss, idAdr)"
										  + "VALUES(?, ?, ?, ?)");
			stmt.setString(1, obj.getNom());
			stmt.setString(2, obj.getPrenom());
			stmt.setDate(3, obj.getDateNaiss());
			stmt.setLong(4, idAdr);
			stmt.executeUpdate();
			
			idPers = stmt.getGeneratedKeys().getLong(1);
			stmt = connect.prepareStatement("INSERT INTO Membre(idPers, cotisation)"
					  					  + "VALUES(?, ?)");
			stmt.setLong(1, idPers);
			stmt.setFloat(2, obj.getCotisation());
			stmt.executeUpdate();
			
			obj.setId(idPers);
			return true;
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean delete(Membre obj){
		PreparedStatement stmt = null;
		try{
			stmt = connect.prepareStatement("DELETE FROM Personne"
					  					  + "WHERE idPers = ?");
			stmt.setLong(1, obj.getId());
			stmt.executeUpdate();
			
			generatedId = stmt.getGeneratedKeys().getInt(1);

			return true;
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean update(Membre obj){
		PreparedStatement stmt = null;
		long idPers = 0;
		long idAdr = 0;
		try{
			adrDAO.update(obj.getAdr());
			idAdr = adrDAO.getGeneratedId();
			
			stmt = connect.prepareStatement("UPDATE Personne"
										  + "SET nom = ?, prenom = ?, dateNaiss = ?, idAdr = ?");
			stmt.setString(1, obj.getNom());
			stmt.setString(2, obj.getPrenom());
			stmt.setDate(3, obj.getDateNaiss());
			stmt.setLong(4, idAdr);
			stmt.executeUpdate();
			
			idPers = stmt.getGeneratedKeys().getLong(1);
			stmt = connect.prepareStatement("UPDATE Membre"
					  					  + "SET idPers = ?, cotisation = ?");
			stmt.setLong(1, idPers);
			stmt.setFloat(2, obj.getCotisation());
			stmt.executeUpdate();
			
			return true;
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public Membre find(int id){
		Membre membre = new Membre();
		try{
			ResultSet resultPers = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Personne"
														   + "INNER JOIN Membre WHERE ipPers = " + id);
			if(resultPers.first()){
				membre = new Membre(id, resultPers.getString("nom"), resultPers.getString("prenom"), resultPers.getDate("dateNaiss"), adrDAO.find(resultPers.getInt("idAdr")), resultPers.getFloat("cotisation"));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return membre;
	}
	
	public long getGeneratedId(){
		return generatedId;
	}
}
