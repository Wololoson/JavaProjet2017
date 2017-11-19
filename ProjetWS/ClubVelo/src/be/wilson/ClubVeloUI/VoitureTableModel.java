package be.wilson.ClubVeloUI;

import javax.swing.table.DefaultTableModel;

public class VoitureTableModel extends DefaultTableModel {
	private static final long serialVersionUID = -870673386098429835L;

	public VoitureTableModel() {
		addColumn("Chauffeur");
		addColumn("Immatriculation");
		addColumn("Places");
	}
}
