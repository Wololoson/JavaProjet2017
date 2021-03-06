package be.wilson.ClubVeloPOJO;

public class VTT extends Categorie{
	private static final long serialVersionUID = -2406134306951908985L;
	private TypeVTT type;
	
	public VTT(int id, int nbMembres, Responsable resp, TypeVTT type) {
		super(id, nbMembres, resp);
		this.type = type;
	}
	
	public VTT(){}

	public TypeVTT getType() {
		return type;
	}

	public void setType(TypeVTT type) {
		this.type = type;
	}
	
	public boolean equals(Membre m){
		if(this.getId() == m.getId())
			return true;
		else
			return false;
	}
	
	public String getSimpleName() {
		return String.format("VTT");
	}

	@Override
	public String toString() {
		return String.format(type.toString());
	}
}
