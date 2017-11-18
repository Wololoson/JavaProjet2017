package be.wilson.ClubVeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import be.wilson.ClubVeloPOJO.Adresse;
import be.wilson.ClubVeloPOJO.Responsable;

public class ResponsableDAO extends DAO<Responsable> {
	AdresseDAO adrDAO = (AdresseDAO) adf.getAdresseDAO();
	private long generatedId;
	
	public ResponsableDAO(Connection conn){
		super(conn);
		generatedId = 0;
	}
	
	public boolean create(Responsable obj){
		PreparedStatement stmt = null;
		long idPers = 0;
		long idAdr = 0;
		try{
			adrDAO.create(obj.getAdr());
			idAdr = adrDAO.getGeneratedId();
			
			stmt = connect.prepareStatement("INSERT INTO Personne(nom, prenom, dateNaiss, idAdr, motDePasse) "
										  + "VALUES(?, ?, ?, ?, ?)");
			stmt.setString(1, obj.getNom());
			stmt.setString(2, obj.getPrenom());
			stmt.setDate(3, obj.getDateNaiss());
			stmt.setLong(4, idAdr);
			stmt.setString(5, obj.getMotDePasse());
			stmt.executeUpdate();
			
			idPers = stmt.getGeneratedKeys().getLong(1);
			stmt = connect.prepareStatement("INSERT INTO Responsable(idPers, dateExp) "
					  					  + "VALUES(?, ?)");
			stmt.setLong(1, idPers);
			stmt.setDate(2, obj.getDateExp());
			stmt.executeUpdate();
			
			obj.setId(idPers);
			super.close(stmt);
			return true;
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean delete(Responsable obj){
		PreparedStatement stmt = null;
		try{
			stmt = connect.prepareStatement("DELETE FROM Personne "
					  					  + "WHERE idPers = ?");
			stmt.setLong(1, obj.getId());
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
	
	public boolean update(Responsable obj){
		PreparedStatement stmt = null;
		long idPers = 0;
		long idAdr = 0;
		try{
			adrDAO.update(obj.getAdr());
			idAdr = adrDAO.getGeneratedId();
			
			stmt = connect.prepareStatement("UPDATE Personne "
										  + "SET nom = ?, prenom = ?, dateNaiss = ?, idAdr = ?, motDePasse = ?");
			stmt.setString(1, obj.getNom());
			stmt.setString(2, obj.getPrenom());
			stmt.setDate(3, obj.getDateNaiss());
			stmt.setLong(4, idAdr);
			stmt.setString(5, obj.getMotDePasse());
			stmt.executeUpdate();
			
			idPers = stmt.getGeneratedKeys().getLong(1);
			stmt = connect.prepareStatement("UPDATE Responsable "
					  					  + "SET idPers = ?, dateExp = ?");
			stmt.setLong(1, idPers);
			stmt.setDate(2, obj.getDateExp());
			stmt.executeUpdate();
			super.close(stmt);
			
			return true;
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public Responsable find(int id){
		Responsable resp = new Responsable();
		try{
			ResultSet resultPers = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Personne P "
														   + "INNER JOIN Responsable R ON P.idPers = R.idPers WHERE idPers = " + id);
			if(resultPers.first()){
				resp = new Responsable(id, 
									   resultPers.getString("nom"), 
									   resultPers.getString("prenom"),
									   resultPers.getDate("dateNaiss"), 
									   adrDAO.find(resultPers.getInt("idAdr")), 
									   resultPers.getDate("dateExp"), 
									   resultPers.getString("motDePasse"));
			}
			super.close(resultPers);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return resp;
	}
	
	public List<Responsable> findAll(){
		List<Responsable> tres = new ArrayList<Responsable>();
		try{
			ResultSet resultPers = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Personne P "
														   + "INNER JOIN Responsable R ON P.idPers = R.idPers "
														   + "INNER JOIN Adresse A ON P.idAdr = A.idAdr "
														   + "ORDER BY nom ASC");
			while(resultPers.next()){
				tres.add(new Responsable(resultPers.getInt("idPers"), 
										 resultPers.getString("nom"), 
										 resultPers.getString("prenom"), 
										 resultPers.getDate("dateNaiss"), 
										 new Adresse(resultPers.getInt("idAdr"), 
													resultPers.getString("rue"),
													resultPers.getInt("numero"), 
													resultPers.getString("codePost"),
													resultPers.getString("ville"),
													resultPers.getString("pays")), 
										 resultPers.getDate("dateExp"), 
										 resultPers.getString("motDePasse")));
			}
			super.close(resultPers);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return tres;
	}
	
	public long getGeneratedId(){
		return generatedId;
	}

}
