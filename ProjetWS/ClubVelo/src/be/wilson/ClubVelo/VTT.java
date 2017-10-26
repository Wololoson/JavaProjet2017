package be.wilson.ClubVelo;

public class VTT extends Categorie{
	private static final long serialVersionUID = -2406134306951908985L;
	private TypeVTT type;
	
	public VTT(int id, int nbMembres, TypeVTT type) {
		super(id, nbMembres);
		this.type = type;
	}

	public TypeVTT getType() {
		return type;
	}

	public void setType(TypeVTT type) {
		this.type = type;
	}
}
