package be.wilson.ClubVeloPOJO;

public class Cyclo extends Categorie {
	private static final long serialVersionUID = 5882898790269390079L;

	public Cyclo(int id, int nbMembres, Responsable resp) {
		super(id, nbMembres, resp);
	}
	
	public Cyclo(){}
	
	public boolean equals(Membre m){
		if(this.getId() == m.getId())
			return true;
		else
			return false;
	}
	
	public String getSimpleName() {
		return String.format("Cyclo");
	}
	
	@Override
	public String toString() {
		return getSimpleName();
	}
}
