package be.wilson.ClubVeloDAO;

import java.sql.*;
import java.util.List;

import be.wilson.ClubVeloFactory.AbstractDAOFactory;

public abstract class DAO<T> {
	protected Connection connect = null;
	protected AbstractDAOFactory adf;
	
	public DAO(Connection conn){
		this.connect = conn;
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	}
	
	public abstract boolean create(T obj);
	
	public abstract boolean delete(T obj);
	
	public abstract boolean update(T obj);
	
	public abstract T find(int id);
	
	public abstract List<T> findAll();
	
	public void close(PreparedStatement stmt){
		try {
			if(stmt != null)
				stmt.close();
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void close(ResultSet res){
		try {
			if(res != null)
				res.close();
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}

