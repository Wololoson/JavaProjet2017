package be.wilson.ClubVeloUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import java.awt.Dimension;
import javax.swing.JPasswordField;
import java.awt.Component;

import be.wilson.ClubVeloConnection.DBConnection;
import be.wilson.ClubVeloDAO.DAO;
import be.wilson.ClubVeloDAO.MembreDAO;
import be.wilson.ClubVeloDAO.ResponsableDAO;
import be.wilson.ClubVeloDAO.TresorierDAO;
import be.wilson.ClubVeloPOJO.Membre;
import be.wilson.ClubVeloPOJO.Personne;
import be.wilson.ClubVeloPOJO.Responsable;
import be.wilson.ClubVeloPOJO.Tresorier;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.JInternalFrame;

public class ConnexionWindow{

	private JFrame frmConnexion;
	private JPasswordField pwdFld;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnexionWindow window = new ConnexionWindow();
					window.frmConnexion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Création
	 */
	public ConnexionWindow() {
		initialize();
	}

	/**
	 * Initialisation
	 */
	private void initialize() {
		//Connexion à la DB
		Connection conn = DBConnection.getInstance();
		
		//Chargement de la liste des membres/responsables/trésorier
		List<Personne> membres = new ArrayList<Personne>();
		DAO<Membre> membreDAO = new MembreDAO(conn);
		DAO<Responsable> responsableDAO = new ResponsableDAO(conn);
		DAO<Tresorier> tresorierDAO = new TresorierDAO(conn);
		
		for(Membre m : membreDAO.findAll())
			membres.add(m);
		
		for(Responsable r : responsableDAO.findAll())
			membres.add(r);
		
		for(Tresorier t : tresorierDAO.findAll())
			membres.add(t);
		
		//Création de la Frame principal
		frmConnexion = new JFrame();
		frmConnexion.getContentPane().setForeground(Color.WHITE);
		frmConnexion.setTitle("Connexion");
		frmConnexion.getContentPane().setBackground(Color.DARK_GRAY);
		
		//Création du Panel de titre
		JPanel titlePan = new JPanel();
		titlePan.setBackground(Color.DARK_GRAY);
		
		//Création du Panel des Nom
		JPanel namePan = new JPanel();
		FlowLayout flowLayout = (FlowLayout) namePan.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		flowLayout.setVgap(15);
		namePan.setBackground(Color.DARK_GRAY);
		
		//Création du Panel de la ComboBox des noms
		JPanel nameCBPan = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) nameCBPan.getLayout();
		flowLayout_3.setVgap(15);
		nameCBPan.setBackground(Color.DARK_GRAY);
		
		//Création du Panel de l'input du mot de passe
		JPanel pwdFldPan = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) pwdFldPan.getLayout();
		flowLayout_4.setVgap(15);
		pwdFldPan.setBackground(Color.DARK_GRAY);
		
		//Création du Panel du mot de passe
		JPanel pwdPan = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pwdPan.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		flowLayout_1.setVgap(15);
		pwdPan.setBackground(Color.DARK_GRAY);
		
		//Création du Panel du bouton de connexion
		JPanel connButPan = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) connButPan.getLayout();
		flowLayout_2.setVgap(15);
		connButPan.setBackground(Color.DARK_GRAY);
		
		JPanel errorMessagePan = new JPanel();
		errorMessagePan.setBackground(Color.DARK_GRAY);
		errorMessagePan.setForeground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(frmConnexion.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(errorMessagePan, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(connButPan, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(namePan, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(nameCBPan, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
								.addComponent(titlePan, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE))
							.addGap(15))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(pwdPan, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pwdFldPan, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(titlePan, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(nameCBPan, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(namePan, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(pwdFldPan, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(pwdPan, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(9)
					.addComponent(errorMessagePan, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(connButPan, GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
					.addGap(8))
		);
		
		JLabel errorMessage = new JLabel("Mot de passe incorrect");
		errorMessage.setVisible(false);
		errorMessage.setFont(new Font("Arial Black", Font.BOLD, 16));
		errorMessage.setForeground(Color.RED);
		errorMessagePan.add(errorMessage);
		
		//Création de l'input pour le mot de passe
		pwdFld = new JPasswordField();
		pwdFld.setBorder(new LineBorder(new Color(0, 0, 0)));
		pwdFld.setBackground(Color.GRAY);
		pwdFld.setForeground(Color.WHITE);
		pwdFld.setPreferredSize(new Dimension(180, 20));
		pwdFld.setSize(new Dimension(170, 20));
		pwdFldPan.add(pwdFld);
		
		//Création de la ComboBox des nom
		JComboBox<Personne> nameCB = new JComboBox<>(new DefaultComboBoxModel<Personne>(membres.toArray(new Personne[0])));
		nameCB.setBorder(new LineBorder(Color.BLACK));
		nameCB.setSelectedIndex(-1);
		nameCB.setPreferredSize(new Dimension(180, 20));
		nameCB.setMinimumSize(new Dimension(170, 20));
		nameCB.setForeground(Color.WHITE);
		nameCB.setBackground(Color.GRAY);
		nameCBPan.add(nameCB);
		
		//Création du Label du Password
		JLabel pwd = new JLabel("Mot de passe :");
		pwd.setHorizontalAlignment(SwingConstants.LEFT);
		pwd.setForeground(Color.WHITE);
		pwdPan.add(pwd);
		
		//Création du Label du Nom
		JLabel name = new JLabel("Nom :");
		name.setHorizontalAlignment(SwingConstants.LEFT);
		name.setForeground(Color.WHITE);
		namePan.add(name);
		
		//Création du Bouton de connexion
		JButton connBut = new JButton("Se connecter");
		connBut.setBorder(new LineBorder(new Color(0, 0, 0)));
		connBut.setForeground(Color.WHITE);
		
		connBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Personne selected = (Personne)nameCB.getSelectedItem();
				if(selected.getMotDePasse().equals(String.valueOf(pwdFld.getPassword()))) {
					errorMessage.setVisible(false);
					MenuWindow menu = new MenuWindow(selected);
					frmConnexion.setVisible(false); 
					frmConnexion.dispose();
					menu.menuDisplay();
				}
				else {
					errorMessage.setVisible(true);
				}
			}
		});
		
		connBut.setAlignmentX(Component.CENTER_ALIGNMENT);
		connBut.setPreferredSize(new Dimension(200, 30));
		connBut.setBackground(Color.DARK_GRAY);
		connButPan.add(connBut);
		
		//Création du Label du titre
		JLabel title = new JLabel("Bienvenue");
		title.setFont(new Font("Arial Black", Font.PLAIN, 20));
		title.setForeground(Color.WHITE);
		title.setBackground(Color.DARK_GRAY);
		titlePan.add(title);
		
		frmConnexion.getContentPane().setLayout(groupLayout);
		frmConnexion.setBounds(100, 100, 450, 340);
		frmConnexion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
