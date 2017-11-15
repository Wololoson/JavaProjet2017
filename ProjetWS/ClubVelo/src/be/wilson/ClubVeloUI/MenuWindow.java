package be.wilson.ClubVeloUI;

import java.awt.EventQueue;

import javax.swing.JFrame;

import be.wilson.ClubVeloPOJO.Personne;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;

public class MenuWindow {

	private JFrame frmMenu;
	private Personne connected;

	public void menuDisplay() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Création
	 */
	public MenuWindow(Personne p) {
		connected = p;
		initialize();
	}

	/**
	 * Initialisation
	 */
	private void initialize() {
		frmMenu = new JFrame();
		frmMenu.setTitle(connected.getNom().toUpperCase() + " " + connected.getPrenom());
		frmMenu.getContentPane().setBackground(Color.DARK_GRAY);
		frmMenu.setBounds(100, 100, 800, 600);
		frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GroupLayout groupLayout = new GroupLayout(frmMenu.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 778, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 544, Short.MAX_VALUE)
		);
		frmMenu.getContentPane().setLayout(groupLayout);
	}

}
