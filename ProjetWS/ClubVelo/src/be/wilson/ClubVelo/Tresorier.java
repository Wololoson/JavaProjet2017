package be.wilson.ClubVelo;

import java.util.GregorianCalendar;

public class Tresorier extends Personne {
	private static final long serialVersionUID = 9048267652525965325L;
	private String code;
	
	public Tresorier(int id, String nom, String prenom, GregorianCalendar dateNaiss, String code) {
		super(id, nom, prenom, dateNaiss);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
