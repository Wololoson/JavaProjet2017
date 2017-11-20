package be.wilson.ClubVeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import be.wilson.ClubVeloPOJO.Adresse;

public class AdresseDAO extends DAO<Adresse> {
	private long generatedId;
	
	public AdresseDAO(Connection conn){
		super(conn);
		generatedId = 0;
	}
	
	public boolean create(Adresse obj) {
		PreparedStatement stmt = null;
		try{
			stmt = connect.prepareStatement("INSERT INTO Adresse(rue, numero, codePost, ville, pays) "
										  + "VALUES(?, ?, ?, ?, ?)");
			stmt.setString(1, obj.getRue());
			stmt.setInt(2, obj.getNum());
			stmt.setString(3, obj.getCodePost());
			stmt.setString(4, obj.getVille());
			stmt.setString(5, obj.getPays());
			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
			while(rs.next())
				generatedId = rs.getInt(1);
			
			obj.setId((int)generatedId);

			super.close(stmt);
			return true;
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean delete(Adresse obj){
		PreparedStatement stmt = null;
		try{
			stmt = connect.prepareStatement("DELETE FROM Adresse "
					  					  + "WHERE idAdr = ?");
			stmt.setLong(1, obj.getId());
			stmt.executeUpdate();

			super.close(stmt);
			return true;
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean update(Adresse obj){
		PreparedStatement stmt = null;
		try{
			stmt = connect.prepareStatement("UPDATE Adresse "
								  		  + "SET rue = ?, numero = ?, codePost = ?, ville = ?, pays = ?");
			stmt.setString(1, obj.getRue());
			stmt.setInt(2, obj.getNum());
			stmt.setString(3, obj.getCodePost());
			stmt.setString(4, obj.getVille());
			stmt.setString(5, obj.getPays());
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
	
	public Adresse find(int id){
		Adresse adr = new Adresse();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Adresse "
														   + "WHERE idAdr = " + id);
			if(result.first()){
				adr = new Adresse(id, 
								  result.getString("rue"), 
								  result.getInt("numero"), 
								  result.getString("codePost"), 
								  result.getString("ville"), 
								  result.getString("pays"));
			}
			
			super.close(result);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return adr;
	}
	
	public long getGeneratedId(){
		return generatedId;
	}

	@Override
	public List<Adresse> findAll() {
		List<Adresse> tres = new ArrayList<Adresse>();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Voiture");
			while(result.next()){
				tres.add( new Adresse(result.getInt("idAdr"), 
									  result.getString("rue"), 
									  result.getInt("numero"), 
									  result.getString("codePost"), 
									  result.getString("ville"), 
									  result.getString("pays")));
			}
			super.close(result);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return tres;
	}
}
