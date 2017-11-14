package be.wilson.ClubVeloUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import be.wilson.ClubVeloConnection.DBConnection;
import be.wilson.ClubVeloPOJO.*;
import be.wilson.ClubVeloDAO.*;

public class ConnectionWindow {
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		//Connexion � la DB
		Connection conn = DBConnection.getInstance();
		
		//Chargement de la liste des membres/responsables/tr�sorier
		List<String> membres = new ArrayList<String>();
		DAO<Membre> membreDAO = new MembreDAO(conn);
		DAO<Responsable> responsableDAO = new ResponsableDAO(conn);
		DAO<Tresorier> tresorierDAO = new TresorierDAO(conn);
		
		for(Membre m : membreDAO.findAll()) {
			membres.add(m.getNom() + " " + m.getPrenom());
		}
		
		for(Responsable r : responsableDAO.findAll()) {
			membres.add(r.getNom() + " " + r.getPrenom());
		}
		
		for(Tresorier t : tresorierDAO.findAll()) {
			membres.add(t.getNom() + " " + t.getPrenom());
		}
		
		//Cr�ation du JPanel principal
		Panneau mainPan = new Panneau();
		mainPan.setLayout(new GridLayout(4, 1));
		
		//Cr�ation du JPanel de titre
		Panneau titlePan = new Panneau();
		JLabel title = new JLabel("Bienvenue !");
		title.setFont(new Font("Arial", Font.PLAIN, 30));
		title.setForeground(Color.RED);
		titlePan.add(title);
		mainPan.add(titlePan);
		
		//Cr�ation du JPanel de l'option Nom
		Panneau namePan = new Panneau();
		JLabel name = new JLabel("Nom : ");
		namePan.add(name);
		JComboBox<String> nameCombo = new JComboBox<>();
		
		for(int i = 0; i < membres.size(); i++)			//Remplissage de la ComboBox
			nameCombo.addItem(membres.get(i));
		
		nameCombo.setPreferredSize(new Dimension(100, 20));
		namePan.add(nameCombo);
		mainPan.add(namePan);
		
		//Cr�ation du JPanel du bouton de connexion
		Panneau connButPan = new Panneau();
		JButton connBut = new JButton("Se connecter");
		connBut.setPreferredSize(new Dimension(150, 25));
		connButPan.add(connBut);
		mainPan.add(connButPan);
		
		//Cr�ation de la fen�tre de connexion
		new Fenetre(500, 300, "Connexion", mainPan);
	}
}
