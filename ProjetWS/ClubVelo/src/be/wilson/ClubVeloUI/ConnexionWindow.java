package be.wilson.ClubVeloUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import be.wilson.ClubVeloDAO.DAO;
import be.wilson.ClubVeloFactory.AbstractDAOFactory;
import be.wilson.ClubVeloPOJO.Membre;
import be.wilson.ClubVeloPOJO.Personne;
import be.wilson.ClubVeloPOJO.Responsable;
import be.wilson.ClubVeloPOJO.Tresorier;

public class ConnexionWindow{
	//SECTION : Création des variables globales
	
	private JFrame frmConnexion;
	private JPasswordField pwdFld;
	private AbstractDAOFactory adf;
	
	//FIN DE SECTION : Création des variables globales

	//SECTION : Méthodes préalables
	
	//Main appelé au lancement du programme
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
		//Appel de la factory
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		initialize();
	}
	
	//FIN DE SECTION : Méthodes préalables
	
	//SECTION : Corps

	/**
	 * Initialisation
	 */
	private void initialize() {
		//Chargement de la liste des membres/responsables/trésorier
		List<Personne> membres = new ArrayList<Personne>();
		DAO<Membre> membreDAO = adf.getMembreDAO();
		DAO<Responsable> responsableDAO = adf.getResponsableDAO();
		DAO<Tresorier> tresorierDAO = adf.getTresorierDAO();
		
		for(Membre m : membreDAO.findAll())
			membres.add(m);
		
		for(Responsable r : responsableDAO.findAll())
			membres.add(r);
		
		for(Tresorier t : tresorierDAO.findAll())
			membres.add(t);
		
		//SECTION : Création des Panel (Swing)
		
		//Frame principal
		frmConnexion = new JFrame();
		frmConnexion.getContentPane().setForeground(Color.WHITE);
		frmConnexion.setTitle("Connexion");
		frmConnexion.getContentPane().setBackground(Color.DARK_GRAY);
		
		//Panel de titre
		JPanel titlePan = new JPanel();
		titlePan.setBackground(Color.DARK_GRAY);
		
		//Panel des Nom
		JPanel namePan = new JPanel();
		FlowLayout flowLayout = (FlowLayout) namePan.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		flowLayout.setVgap(15);
		namePan.setBackground(Color.DARK_GRAY);
		
		//Panel de la ComboBox des noms
		JPanel nameCBPan = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) nameCBPan.getLayout();
		flowLayout_3.setVgap(15);
		nameCBPan.setBackground(Color.DARK_GRAY);
		
		//Panel de l'input du mot de passe
		JPanel pwdFldPan = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) pwdFldPan.getLayout();
		flowLayout_4.setVgap(15);
		pwdFldPan.setBackground(Color.DARK_GRAY);
		
		//Panel du mot de passe
		JPanel pwdPan = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pwdPan.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		flowLayout_1.setVgap(15);
		pwdPan.setBackground(Color.DARK_GRAY);
		
		//Panel du bouton de connexion
		JPanel connButPan = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) connButPan.getLayout();
		flowLayout_2.setVgap(15);
		connButPan.setBackground(Color.DARK_GRAY);
		
		//Panel pour le message d'erreur
		JPanel errorMessagePan = new JPanel();
		errorMessagePan.setBackground(Color.DARK_GRAY);
		errorMessagePan.setForeground(Color.WHITE);
		
		//Grouplayout principal + ajout des éléments à celui-ci (Swing uniquement)
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
		
		//FIN DE SECTION : Création des Panels
		
		//SECTION : Création des éléments de la fenêtre
		
		//Message d'erreur
		JLabel errorMessage = new JLabel("Mot de passe incorrect");
		errorMessage.setVisible(false);
		errorMessage.setFont(new Font("Arial Black", Font.BOLD, 16));
		errorMessage.setForeground(Color.RED);
		errorMessagePan.add(errorMessage);
		
		//Input pour le mot de passe
		pwdFld = new JPasswordField();
		pwdFld.setBorder(new LineBorder(new Color(0, 0, 0)));
		pwdFld.setBackground(Color.GRAY);
		pwdFld.setForeground(Color.WHITE);
		pwdFld.setPreferredSize(new Dimension(180, 20));
		pwdFld.setSize(new Dimension(170, 20));
		pwdFldPan.add(pwdFld);
		
		//ComboBox des nom
		JComboBox<Personne> nameCB = new JComboBox<>(new DefaultComboBoxModel<Personne>(membres.toArray(new Personne[0])));
		nameCB.setBorder(new LineBorder(Color.BLACK));
		nameCB.setSelectedIndex(-1);
		nameCB.setPreferredSize(new Dimension(180, 20));
		nameCB.setMinimumSize(new Dimension(170, 20));
		nameCB.setForeground(Color.WHITE);
		nameCB.setBackground(Color.GRAY);
		nameCBPan.add(nameCB);
		
		//Label du mot de passe
		JLabel pwd = new JLabel("Mot de passe :");
		pwd.setHorizontalAlignment(SwingConstants.LEFT);
		pwd.setForeground(Color.WHITE);
		pwdPan.add(pwd);
		
		//Label du Nom
		JLabel name = new JLabel("Nom :");
		name.setHorizontalAlignment(SwingConstants.LEFT);
		name.setForeground(Color.WHITE);
		namePan.add(name);
		
		//Bouton de connexion
		JButton connBut = new JButton("Se connecter");
		connBut.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		connBut.setForeground(Color.WHITE);
		
		connBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Sauvegarde de la personne connectée
				Personne selected = (Personne)nameCB.getSelectedItem();
				Personne connected = null;
				if(selected instanceof Membre) {
					DAO<Membre> mbreDAO = adf.getMembreDAO();
					connected = mbreDAO.find((int)selected.getId());
				}
				else if(selected instanceof Responsable) {
					DAO<Responsable> respDAO = adf.getResponsableDAO();
					connected = respDAO.find((int)selected.getId());
				}
				else {
					DAO<Tresorier> tresDAO = adf.getTresorierDAO();
					connected = tresDAO.find((int)selected.getId());
				}
				
				//Si le mot de passe est correct, on connecte, sinon on affiche l'erreur
				if(selected.getMotDePasse().equals(String.valueOf(pwdFld.getPassword()))) {
					errorMessage.setVisible(false);
					frmConnexion.setVisible(false); 
					frmConnexion.dispose();
					MenuWindow menu;
					RespWindow resp;
					//TresWindow tres;
					if(connected instanceof Membre) {
						menu = new MenuWindow((Membre)connected);
						menu.menuDisplay();
					}
					else if(connected instanceof Responsable) {
						resp = new RespWindow((Responsable)connected);
						resp.respDisplay();
					}
					//else {
						//tres = new TresWindow((Tresorier)connected);
						//tres.tresDisplay();
					//}
					
					
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
		
		//Label du titre
		JLabel title = new JLabel("Bienvenue");
		title.setFont(new Font("Arial Black", Font.PLAIN, 20));
		title.setForeground(Color.WHITE);
		title.setBackground(Color.DARK_GRAY);
		titlePan.add(title);
		
		// FIN DE SECTION : Création des éléments de la fenêtre
		
		frmConnexion.getContentPane().setLayout(groupLayout);
		frmConnexion.setBounds(100, 100, 450, 340);
		frmConnexion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//FIN DE SECTION : Corps
}
