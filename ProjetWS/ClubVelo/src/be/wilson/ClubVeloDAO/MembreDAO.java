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

public class MembreDAO extends DAO<Membre>{
	AdresseDAO adrDAO = (AdresseDAO) adf.getAdresseDAO();
	private long generatedId;
	
	public MembreDAO(Connection conn){
		super(conn);
		generatedId = 0;
	}
	
	public boolean create(Membre obj){
		PreparedStatement stmt = null;
		try{
			adrDAO.create(obj.getAdr());
			
			stmt = connect.prepareStatement("INSERT INTO Personne(nom, prenom, dateNaiss, idAdr, motDePasse) "
										  + "VALUES(?, ?, ?, ?, ?)");
			stmt.setString(1, obj.getNom());
			stmt.setString(2, obj.getPrenom());
			stmt.setDate(3, obj.getDateNaiss());
			stmt.setLong(4, obj.getAdr().getId());
			stmt.setString(5, obj.getMotDePasse());
			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
			while(rs.next()) {
				obj.setId(rs.getInt(1));
				generatedId = rs.getInt(1);
			}
			
			stmt = connect.prepareStatement("INSERT INTO Membre(idPers, cotisation) "
					  					  + "VALUES(?, ?)");
			stmt.setLong(1, obj.getId());
			stmt.setFloat(2, obj.getCotisation());
			stmt.executeUpdate();
			
			super.close(stmt);
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
			stmt = connect.prepareStatement("DELETE FROM Personne "
					  					  + "WHERE idPers = ?");
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
	
	public boolean update(Membre obj){
		PreparedStatement stmt = null;
		try{
			adrDAO.update(obj.getAdr());
			
			stmt = connect.prepareStatement("UPDATE Personne "
										  + "SET nom = ?, prenom = ?, dateNaiss = ?, idAdr = ?, motDePasse = ? "
										  + "WHERE idPers = " + obj.getId());
			stmt.setString(1, obj.getNom());
			stmt.setString(2, obj.getPrenom());
			stmt.setDate(3, obj.getDateNaiss());
			stmt.setLong(4, obj.getAdr().getId());
			stmt.setString(5, obj.getMotDePasse());
			stmt.executeUpdate();
			
			stmt = connect.prepareStatement("UPDATE Membre "
					  					  + "SET cotisation = ? "
					  					  + "WHERE idPers = " + obj.getId());
			stmt.setFloat(1, obj.getCotisation());
			stmt.executeUpdate();
			
			super.close(stmt);
			
			return true;
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean updateForfait(Membre m, Balade b, float f){
		PreparedStatement stmt = null;
		try{
			stmt = connect.prepareStatement("UPDATE Eclat_Bal_Disp "
										  + "SET forfait = ? "
										  + "WHERE idPers = " + m.getId()
										  + " AND idBal = " + b.getId());
			stmt.setFloat(1, f);
			stmt.executeUpdate();
			
			super.close(stmt);
			
			return true;
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public float getForfait(Membre m, Balade b){
		float f = 0f;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT forfait FROM Eclat_Bal_Disp "
														   + "WHERE idPers = " + m.getId()
														   + " AND idBal = " + b.getId());
			
			if(result.first())
				f = result.getFloat("forfait");
			
			super.close(result);
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return f;
	}
	
	public List<Float> getAllForfait(Membre m){
		List<Float> f = new ArrayList<>();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT forfait FROM Eclat_Bal_Disp "
														   + "WHERE idPers = " + m.getId());
			
			while(result.next())
				f.add(result.getFloat("forfait"));
			
			super.close(result);
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return f;
	}
	
	public Membre find(int id){
		Membre membre = new Membre();
		try{
			ResultSet resultPers = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Personne P "
														   + "INNER JOIN Membre M ON P.idPers = M.idPers WHERE idPers = " + id);
			if(resultPers.first()){
				membre = new Membre(id, 
									resultPers.getString("nom"), 
									resultPers.getString("prenom"), 
									resultPers.getDate("dateNaiss"), 
									adrDAO.find(resultPers.getInt("idAdr")), 
									resultPers.getFloat("cotisation"), 
									resultPers.getString("motDePasse"));
			}
			super.close(resultPers);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return membre;
	}
	
	public List<Membre> findAll(){
		List<Membre> tres = new ArrayList<Membre>();
		try{
			ResultSet resultPers = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Personne P "
														   + "INNER JOIN Membre M ON P.idPers = M.idPers "
														   + "INNER JOIN Adresse A ON P.idAdr = A.idAdr "
														   + "ORDER BY nom ASC");
			while(resultPers.next()){
				tres.add(new Membre(resultPers.getInt("idPers"), 
									resultPers.getString("nom"), 
									resultPers.getString("prenom"), 
									resultPers.getDate("dateNaiss"), 
									new Adresse(resultPers.getInt("idAdr"), 
												resultPers.getString("rue"),
												resultPers.getInt("numero"), 
												resultPers.getString("codePost"),
												resultPers.getString("ville"),
												resultPers.getString("pays")), 
									resultPers.getFloat("cotisation"), 
									resultPers.getString("motDePasse")));
			}
			super.close(resultPers);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return tres;
	}
	
	public float getSuppl(long id) {
		float suppl = 0;
		try{
			ResultSet resultPers = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Ligne_Membre_Cat "
														   + "WHERE idPers = " + id);
			while(resultPers.next()){
				suppl += resultPers.getFloat("supplement");
			}
			super.close(resultPers);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return suppl;
	}
	
	public List<Membre> getMembreList(int id){
		List<Membre> mbreList = new ArrayList<>();
		List<Membre> all = findAll();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Ligne_Membre_Cat L "
														   + "WHERE idCat = " + id);
			while(result.next()){
				for(Membre m : all)
					if(m.getId() == result.getInt("idPers"))
						mbreList.add(m);
			}
			super.close(result);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return mbreList;
	}
	
	public List<Membre> getPassagers(int idVoit, int idBal){
		List<Membre> mbreList = new ArrayList<>();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Eclat_Bal_Disp "
														   + "WHERE idBal = " + idBal + " AND idVoit = " + idVoit);
			while(result.next()){
				mbreList.add(find(result.getInt("idPers")));
			}
			super.close(result);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return mbreList;
	}
	
	public boolean addCat(int idCat, int idPers, float supp) {
		PreparedStatement stmt = null;
		try{
			stmt = connect.prepareStatement("INSERT INTO Ligne_Membre_Cat(idPers, idCat, supplement) "
										  + "VALUES(?, ?, ?)");
			stmt.setInt(1, idPers);
			stmt.setInt(2, idCat);
			stmt.setFloat(3, supp);
			stmt.executeUpdate();
			
			super.close(stmt);
			return true;
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public long getGeneratedId(){
		return generatedId;
	}
}
