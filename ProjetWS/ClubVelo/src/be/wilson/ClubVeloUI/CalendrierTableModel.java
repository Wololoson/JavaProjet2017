package be.wilson.ClubVeloUI;

import javax.swing.table.DefaultTableModel;

public class CalendrierTableModel extends DefaultTableModel {
	private static final long serialVersionUID = -829519699587943452L;
	
	public CalendrierTableModel() {
		addColumn("Libell�");
		addColumn("Date");
		addColumn("Forfait");
	}
}
