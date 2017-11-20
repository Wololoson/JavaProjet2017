package be.wilson.ClubVeloUI;

import javax.swing.table.DefaultTableModel;

public class CotisationTableModel extends DefaultTableModel {
	private static final long serialVersionUID = -829519699587943452L;
	
	public CotisationTableModel() {
		addColumn("NOM");
		addColumn("Prénom");
		addColumn("Cotisation");
	}
	
	public boolean isCellEditable(int row, int column){  
        return false;  
    }
}
