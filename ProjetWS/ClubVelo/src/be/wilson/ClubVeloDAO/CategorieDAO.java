package be.wilson.ClubVeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import be.wilson.ClubVeloPOJO.Adresse;
import be.wilson.ClubVeloPOJO.Categorie;
import be.wilson.ClubVeloPOJO.Cyclo;
import be.wilson.ClubVeloPOJO.Responsable;
import be.wilson.ClubVeloPOJO.TypeVTT;
import be.wilson.ClubVeloPOJO.VTT;

public class CategorieDAO extends DAO<Categorie> {
	private long generatedId;
	
	public CategorieDAO(Connection conn){
		super(conn);
		generatedId = 0;
	}
	
	public Categorie find(int id){
		Categorie cat = null;
		TypeVTT typeVTT = null;
		DAO<Responsable> respDAO = adf.getResponsableDAO();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Categorie "
														   + "WHERE idCat = " + id);
			if(result.first()) {
				switch(result.getString("type")){
				case "VTT":
					if(TypeVTT.Descendeur.toString().equals(result.getString("sousType")))
						typeVTT = TypeVTT.Descendeur;
					else if (TypeVTT.Randonneur.toString().equals(result.getString("sousType")))
						typeVTT = TypeVTT.Randonneur;
					else
						typeVTT = TypeVTT.Trialiste;
					cat = new VTT(result.getInt("idCat"), 
								  result.getInt("nbMembres"), 
								  respDAO.find(result.getInt("idPers")), 
								  typeVTT);
					break;
				case "Cyclo":
					cat = new Cyclo(result.getInt("idCat"), 
									result.getInt("nbMembres"), 
									respDAO.find(result.getInt("idPers")));
					break;
				}
			}
			
			super.close(result);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return cat;
	}
	
	public List<Categorie> getCatList(int id){
		List<Categorie> catList = new ArrayList<>();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT C.idCat, nbMembres, type, sousType, C.idPers, P.idPers, motDePasse, nom, prenom, dateNaiss, P.idAdr, R.idPers, dateExp, A.idAdr, rue, numero, codePost, ville, pays, L.idPers AS id, L.idCat, supplement FROM Categorie C "
														   + "INNER JOIN Personne P ON C.idPers = P.idPers "
														   + "INNER JOIN Responsable R ON P.idPers = R.idPers "
														   + "INNER JOIN Adresse A ON P.idAdr = A.idAdr "
														   + "INNER JOIN Ligne_Membre_Cat L ON C.idCat = L.idCat "
														   + "WHERE L.idPers = " + id);
			
			while(result.next()){
				TypeVTT typeVTT = null;
				switch(result.getString("type")){
					case "VTT":
						if(TypeVTT.Descendeur.toString().equals(result.getString("sousType")))
							typeVTT = TypeVTT.Descendeur;
						else if (TypeVTT.Randonneur.toString().equals(result.getString("sousType")))
							typeVTT = TypeVTT.Randonneur;
						else
							typeVTT = TypeVTT.Trialiste;
						catList.add(new VTT(result.getInt("idCat"), 
									  result.getInt("nbMembres"), 
									  new Responsable(result.getInt("idPers"), 
													  result.getString("nom"), 
													  result.getString("prenom"), 
													  result.getDate("dateNaiss"), 
													  new Adresse(result.getInt("idAdr"), 
															  	  result.getString("rue"),
																  result.getInt("numero"), 
																  result.getString("codePost"),
																  result.getString("ville"),
																  result.getString("pays")), 
													  result.getDate("dateExp"), 
													  result.getString("motDePasse")), 
									  typeVTT));
						break;
					case "Cyclo":
						catList.add(new Cyclo(result.getInt("idCat"), 
										result.getInt("nbMembres"), 
										new Responsable(result.getInt("idPers"), 
												  result.getString("nom"), 
												  result.getString("prenom"), 
												  result.getDate("dateNaiss"), 
												  new Adresse(result.getInt("idAdr"), 
														  	  result.getString("rue"),
															  result.getInt("numero"), 
															  result.getString("codePost"),
															  result.getString("ville"),
															  result.getString("pays")), 
												  result.getDate("dateExp"), 
												  result.getString("motDePasse"))));
						break;
				}
			}
			super.close(result);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return catList;
	}
	
	public long getGeneratedId(){
		return generatedId;
	}

	@Override
	public boolean create(Categorie obj) {
		PreparedStatement stmt = null;
		try{
			stmt = connect.prepareStatement("INSERT INTO Categorie(nbMembres, type, sousType, idPers)"
										  + "VALUES(?, ?, ?, ?)");
			stmt.setInt(1, obj.getNbMembres());
			if(obj instanceof VTT) {
				stmt.setString(2, "VTT");
				stmt.setString(3, ((VTT)obj).getType().name());
			}
			else {
				stmt.setString(2, "Cyclo");
				stmt.setString(3, null);
			}
			stmt.setLong(4, obj.getResp().getId());
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

	@Override
	public boolean delete(Categorie obj) {
		PreparedStatement stmt = null;
		try{
			stmt = connect.prepareStatement("DELETE FROM Categorie"
					  					  + "WHERE idCat = ?");
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

	@Override
	public boolean update(Categorie obj) {
		PreparedStatement stmt = null;
		try{
			stmt = connect.prepareStatement("UPDATE Categorie"
								  		  + "SET nbMembres = ?, type = ?, sousType = ?, idPers = ?");
			stmt.setInt(1, obj.getNbMembres());
			if(obj instanceof VTT) {
				stmt.setString(2, "VTT");
				stmt.setString(3, ((VTT)obj).getType().name());
			}
			else {
				stmt.setString(2, "Cyclo");
				stmt.setString(3, null);
			}
			stmt.setLong(4, obj.getResp().getId());
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

	@Override
	public List<Categorie> findAll() {
		List<Categorie> catList = new ArrayList<Categorie>();
		TypeVTT typeVTT = null;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Categorie C "
														   + "INNER JOIN Personne P ON C.idPers = P.idPers "
														   + "INNER JOIN Responsable R ON P.idPers = R.idPers "
														   + "INNER JOIN Adresse A ON P.idAdr = A.idAdr");
			
			while(result.next()){
				switch(result.getString("type")){
					case "VTT":
						if(TypeVTT.Descendeur.toString().equals(result.getString("sousType")))
							typeVTT = TypeVTT.Descendeur;
						else if (TypeVTT.Randonneur.toString().equals(result.getString("sousType")))
							typeVTT = TypeVTT.Randonneur;
						else
							typeVTT = TypeVTT.Trialiste;
						catList.add(new VTT(result.getInt("idCat"), 
											result.getInt("nbMembres"), 
											new Responsable(result.getInt("idPers"),
															result.getString("nom"),
															result.getString("prenom"),
															result.getDate("dateNaiss"),
															new Adresse(result.getInt("idAdr"), 
																		result.getString("rue"),
																		result.getInt("numero"), 
																		result.getString("codePost"),
																		result.getString("ville"),
																		result.getString("pays")),
															result.getDate("dateExp"),
															result.getString("motDePasse")), 
											typeVTT));
						break;
					case "Cyclo":
						catList.add(new Cyclo(result.getInt("idCat"), 
											  result.getInt("nbMembres"), 
											  new Responsable(result.getInt("idPers"),
														result.getString("nom"),
														result.getString("prenom"),
														result.getDate("dateNaiss"),
														new Adresse(result.getInt("idAdr"), 
																	result.getString("rue"),
																	result.getInt("numero"), 
																	result.getString("codePost"),
																	result.getString("ville"),
																	result.getString("pays")),
														result.getDate("dateExp"),
														result.getString("motDePasse"))));
						break;
				}
			}
			super.close(result);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return catList;
	}
	
	public Categorie findForResp(int idPers) {
		Categorie cat = null;
		TypeVTT typeVTT = null;
		DAO<Responsable> respDAO = adf.getResponsableDAO();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Categorie "
														   + "WHERE idPers = " + idPers);
			if(result.first()) {
				switch(result.getString("type")){
				case "VTT":
					if(TypeVTT.Descendeur.toString().equals(result.getString("sousType")))
						typeVTT = TypeVTT.Descendeur;
					else if (TypeVTT.Randonneur.toString().equals(result.getString("sousType")))
						typeVTT = TypeVTT.Randonneur;
					else
						typeVTT = TypeVTT.Trialiste;
					cat = new VTT(result.getInt("idCat"), 
								  result.getInt("nbMembres"), 
								  respDAO.find(result.getInt("idPers")), 
								  typeVTT);
					break;
				case "Cyclo":
					cat = new Cyclo(result.getInt("idCat"), 
									result.getInt("nbMembres"), 
									respDAO.find(result.getInt("idPers")));
					break;
				}
			}
			
			super.close(result);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return cat;
	}
}
