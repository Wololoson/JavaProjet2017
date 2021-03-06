package be.wilson.ClubVeloPOJO;

import java.sql.Date;

public class Tresorier extends Personne {
	private static final long serialVersionUID = 9048267652525965325L;
	private String code;
	
	public Tresorier(int id, String nom, String prenom, Date dateNaiss, Adresse adr, String code, String motDePasse) {
		super(id, nom, prenom, dateNaiss, adr, motDePasse);
		this.code = code;
	}
	
	public Tresorier(){}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
