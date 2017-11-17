package be.wilson.ClubVeloUI;

import javax.swing.table.DefaultTableModel;

public class BaladeTableModel extends DefaultTableModel {
	private static final long serialVersionUID = -829519699587943452L;
	
	public BaladeTableModel() {
		addColumn("Libellé");
		addColumn("Date");
		addColumn("Catégorie");
	}
}
