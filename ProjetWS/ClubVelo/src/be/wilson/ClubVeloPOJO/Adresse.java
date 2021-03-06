package be.wilson.ClubVeloPOJO;

import java.io.*;

public class Adresse implements Serializable {
	private static final long serialVersionUID = 6895178666450590071L;
	private long id;
	private String rue;
	private int num;
	private String ville;
	private String codePost;
	private String pays;
	
	public Adresse(long id, String rue, int num, String ville, String codePost, String pays){
		this.id = id;
		this.rue = rue;
		this.num = num;
		this.ville = ville;
		this.codePost = codePost;
		this.pays = pays;
	}
	
	public Adresse(){}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodePost() {
		return codePost;
	}

	public void setCodePost(String codePost) {
		this.codePost = codePost;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}
	
	public void modifierAdresse(Adresse a) {
		this.id = a.id;
		this.rue = a.rue;
		this.num = a.num;
		this.ville = a.ville;
		this.codePost = a.codePost;
		this.pays = a.pays;
	}
}
