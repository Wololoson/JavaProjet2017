package be.wilson.ClubVeloDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import be.wilson.ClubVeloFactory.AbstractDAOFactory;
import be.wilson.ClubVeloPOJO.Adresse;
import be.wilson.ClubVeloPOJO.Balade;
import be.wilson.ClubVeloPOJO.Categorie;

public class BaladeDAO extends DAO<Balade> {
	private long generatedId;
	private AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	
	public BaladeDAO(Connection conn){
		super(conn);
		generatedId = 0;
	}
	
	public boolean create(Balade obj) {
		PreparedStatement stmt = null;
		try{
			DAO<Adresse> adrDAO = adf.getAdresseDAO();
			adrDAO.create(obj.getAdr());
			
			stmt = connect.prepareStatement("INSERT INTO Balade(libelleBal, dateBal, fraisDepla, idAdr, idCat) "
										  + "VALUES(?, ?, ?, ?, ?)");
			stmt.setString(1, obj.getLibelle());
			stmt.setDate(2, obj.getDate());
			stmt.setFloat(3, obj.getFraisDepla());
			stmt.setLong(4, obj.getAdr().getId());
			stmt.setLong(5, obj.getCat().getId());
			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
			while(rs.next())
				generatedId = rs.getInt(1);
			
			obj.setId((int)generatedId);

			super.close(stmt);
			return true;
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean delete(Balade obj){
		PreparedStatement stmt = null;
		try{
			DAO<Adresse> adrDAO = adf.getAdresseDAO();
			adrDAO.delete(obj.getAdr());
			
			stmt = connect.prepareStatement("DELETE FROM Balade "
					  					  + "WHERE idBal = ?");
			stmt.setLong(1, obj.getId());
			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
			while(rs.next())
				generatedId = rs.getInt(1);

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
			stmt = connect.prepareStatement("UPDATE Balade "
								  		  + "SET libelleBal = ?, dateBal = ?, fraisDepla = ?, idAdr = ?, idCat = ? "
								  		  + "WHERE idBal = " + obj.getId());
			stmt.setString(1, obj.getLibelle());
			stmt.setDate(2, new Date(obj.getDate().getTime()));
			stmt.setFloat(3, obj.getFraisDepla());
			stmt.setLong(4, obj.getAdr().getId());
			stmt.setLong(5, obj.getCat().getId());
			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
			
			while(rs.next())
				generatedId = rs.getInt(1);
			
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
		DAO<Adresse> adrDAO = adf.getAdresseDAO();
		DAO<Categorie> catDAO = adf.getCatDAO();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Balade "
														   + "WHERE idBal = " + id);
			if(result.first())
				bal = new Balade(id, result.getString("libelleBal"), adrDAO.find(result.getInt("idAdr")), result.getDate("dateBal"), result.getFloat("fraisDepla"), catDAO.find(result.getInt("idCat")));
			
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
	
	public List<Balade> findAll(int idCat) {
		List<Balade> tres = new ArrayList<Balade>();
		DAO<Categorie> catDAO = adf.getCatDAO();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Balade B "
														   + "INNER JOIN Adresse A ON B.idAdr = A.idAdr "
														   + "INNER JOIN Categorie C ON B.idCat = C.idCat "
														   + "WHERE B.idCat = " + idCat);
			
			while(result.next()){
				tres.add(new Balade(result.getInt("idBal"), 
									result.getString("libelleBal"), 
									new Adresse(result.getInt("idAdr"), 
												result.getString("rue"),
												result.getInt("numero"), 
												result.getString("codePost"),
												result.getString("ville"),
												result.getString("pays")), 
									result.getDate("dateBal"), 
									result.getFloat("fraisDepla"), 
									catDAO.find(result.getInt("idCat"))));
			}
			super.close(result);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return tres;
	}

	public List<Balade> findAll(List<Integer> idCat) {
		List<Balade> tres = new ArrayList<Balade>();
		DAO<Categorie> catDAO = adf.getCatDAO();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Balade B "
														   + "INNER JOIN Adresse A ON B.idAdr = A.idAdr "
														   + "INNER JOIN Categorie C ON B.idCat = C.idCat");
			while(result.next()){
				for(int i : idCat) {
					if(result.getInt("idCat") == i) {
						tres.add(new Balade(result.getInt("idBal"), 
											result.getString("libelleBal"), 
											new Adresse(result.getInt("idAdr"), 
														result.getString("rue"),
														result.getInt("numero"), 
														result.getString("codePost"),
														result.getString("ville"),
														result.getString("pays")), 
											result.getDate("dateBal"), 
											result.getFloat("fraisDepla"), 
											catDAO.find(result.getInt("idCat"))));
					}
				}
			}
			super.close(result);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return tres;
	}
	
	public List<Balade> getBalList(String numImmat){
		List<Balade> balList = new ArrayList<>();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Eclat_Bal_Disp "
														   + "WHERE numImmat = " + numImmat);
			while(result.next()){
				balList.add(find(result.getInt("idBal")));
			}
			super.close(result);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return balList;
	}

	@Override
	public List<Balade> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
