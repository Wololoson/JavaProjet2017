package be.wilson.ClubVeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import be.wilson.ClubVeloPOJO.Balade;

public class BaladeDAO extends DAO<Balade> {
	private long generatedId;
	
	public BaladeDAO(Connection conn){
		super(conn);
		generatedId = 0;
	}
	
	public boolean create(Balade obj) {
		PreparedStatement stmt = null;
		try{
			stmt = connect.prepareStatement("INSERT INTO Balade(libelleBal, dateBal, fraisDepla, idAdr, idCat)"
										  + "VALUES(?, ?, ?, ?, ?)");
			stmt.setString(1, obj.getLibelle());
			stmt.setDate(2, obj.getDate());
			stmt.setFloat(3, obj.getFraisDepla());
			stmt.setLong(4, obj.getAdr().getId());
			stmt.setLong(4, obj.getCat().getId());
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
	
	public boolean delete(Balade obj){
		PreparedStatement stmt = null;
		try{
			stmt = connect.prepareStatement("DELETE FROM Balade"
					  					  + "WHERE idBal = ?");
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
	
	public boolean update(Balade obj){
		PreparedStatement stmt = null;
		try{
			stmt = connect.prepareStatement("UPDATE Balade"
								  		  + "SET libelleBal = ?, dateBal = ?, fraisDepla = ?, idAdr = ?, idCat = ?");
			stmt.setString(1, obj.getLibelle());
			stmt.setDate(2, obj.getDate());
			stmt.setFloat(3, obj.getFraisDepla());
			stmt.setLong(4, obj.getAdr().getId());
			stmt.setLong(4, obj.getCat().getId());
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
	
	public Balade find(int id){
		Balade bal = new Balade();
		AdresseDAO adrDAO = new AdresseDAO(connect);
		CategorieDAO catDAO = new CategorieDAO(connect);
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Balade"
														   + "WHERE idBal = " + id);
			
			bal = new Balade(id, result.getString("libelleCat"), adrDAO.find(result.getInt("idAdr")), result.getDate("dateBal"), result.getFloat("fraisDepla"), catDAO.find(result.getInt("idCat")));
			
			super.close(result);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return bal;
	}
	
	public long getGeneratedId(){
		return generatedId;
	}
}
