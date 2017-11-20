package be.wilson.ClubVeloUI;

import javax.swing.table.DefaultTableModel;

public class CategorieTableModel extends DefaultTableModel {
	private static final long serialVersionUID = -829519699587943452L;
	
	public CategorieTableModel() {
		addColumn("NOM");
		addColumn("Prénom");
		addColumn("Date de naissance");
	}
	
	public boolean isCellEditable(int row, int column){  
        return false;  
    }
}
