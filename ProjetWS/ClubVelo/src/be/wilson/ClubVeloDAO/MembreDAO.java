package be.wilson.ClubVeloDAO;

import java.sql.*;

import be.wilson.ClubVeloPOJO.Membre;

public class MembreDAO extends DAO<Membre>{
	public MembreDAO(Connection conn){
		super(conn);
	}
	
	public boolean create(Membre obj){
		PreparedStatement stmt = null;
		ResultSet idPers = null;
		try{
			stmt = connect.prepareStatement("INSERT INTO Adresse(rue, num, codePost, ville, pays)"
										  + "VALUES(?, ?, ?, ?, ?)");
			stmt.setString(1, obj.getAdr().getRue());
			stmt.setInt(2, obj.getAdr().getNum());
			stmt.setString(3, obj.getAdr().getCodePost());
			stmt.setString(4, obj.getAdr().getVille());
			stmt.setString(5, obj.getAdr().getPays());
			//To do : Insert personne, insert membre
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean delete(Membre obj){
		return false;
	}
	
	public boolean update(Membre obj){
		return false;
	}
	
	public Membre find(int id){
		Membre membre = new Membre();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Personne"
							+ "INNER JOIN Membre WHERE ipPers = " + id);
			if(result.first())
				membre = new Membre(id, result.getString("nom"), result.getString("prenom"), result.getDate("dateNaiss"), result.getFloat("cotisation"));
			}
		catch(SQLException e){
			e.printStackTrace();
		}
		return membre;
	}

}