package be.wilson.ClubVeloUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import be.wilson.ClubVeloDAO.BaladeDAO;
import be.wilson.ClubVeloDAO.MembreDAO;
import be.wilson.ClubVeloDAO.VoitureDAO;
import be.wilson.ClubVeloFactory.AbstractDAOFactory;
import be.wilson.ClubVeloPOJO.Balade;
import be.wilson.ClubVeloPOJO.Categorie;
import be.wilson.ClubVeloPOJO.Cyclo;
import be.wilson.ClubVeloPOJO.Membre;
import be.wilson.ClubVeloPOJO.VTT;
import be.wilson.ClubVeloPOJO.Voiture;

public class MenuWindow {
	//SECTION : Création des variables globales
	
	//Affichage
	private JFrame frmMenu;
	private JTable coTable;
	private JTextField nbPlacesFld;
	private Balade selectedBal;
	private JTextField numImmatFld1;
	private JTextField numImmatFld2;
	private JScrollPane coSP;
	private JTable balTable;
	private JLabel lblStatusRand;
	
	//Fonctionnalités
	private Membre connected;
	private AbstractDAOFactory adf;
	private List<Voiture> listVoit = new ArrayList<>();
	private MembreDAO membreDAO;
	private VoitureDAO voitureDAO;
	private BaladeDAO baladeDAO;
	
	//FIN DE SECTION : Création des variables globales
	
	//SECTION : Méthodes préalables

	//Méthode appelée pour afficher la fenêtre après la connexion
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
	 * Méthode appelée pour créer la fenêtre après la connexion
	 */
	public MenuWindow(Membre m) {
		//Initialisation de la factory
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		membreDAO = (MembreDAO) adf.getMembreDAO();	
		voitureDAO = (VoitureDAO) adf.getVoitureDAO();
		baladeDAO = (BaladeDAO) adf.getBaladeDAO();
		
		//Récupération du membre connecté
		connected = m;
		
		initialize();
	}
	
	//FIN DE SECTION : Méthodes préalables
	
	//SECTION : Corps

	/**
	 * Initialisation de la fenêtre
	 */
	private void initialize() {
		frmMenu = new JFrame();
		frmMenu.setTitle(connected.getNom().toUpperCase() + " " + connected.getPrenom()); //Affichage des NOM et Prénom de la personne connectée
		frmMenu.getContentPane().setBackground(Color.DARK_GRAY);
		frmMenu.setBounds(100, 100, 800, 600);
		frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//SECTION : Création des Panels du menu
		
		JPanel menuPan = new JPanel();
		menuPan.setVisible(false);
		menuPan.setBackground(Color.DARK_GRAY);
		
		JPanel codrivingBtnPan = new JPanel();
		FlowLayout fl_codrivingBtnPan = (FlowLayout) codrivingBtnPan.getLayout();
		fl_codrivingBtnPan.setVgap(25);
		codrivingBtnPan.setBackground(Color.DARK_GRAY);
		
		JPanel infosBtnPan = new JPanel();
		FlowLayout fl_infosBtnPan = (FlowLayout) infosBtnPan.getLayout();
		fl_infosBtnPan.setVgap(25);
		infosBtnPan.setBackground(Color.DARK_GRAY);
		
		JPanel catBtnPan = new JPanel();
		FlowLayout fl_catBtnPan = (FlowLayout) catBtnPan.getLayout();
		fl_catBtnPan.setVgap(25);
		catBtnPan.setBackground(Color.DARK_GRAY);
		
		JPanel quitBtnPan = new JPanel();
		FlowLayout fl_quitBtnPan = (FlowLayout) quitBtnPan.getLayout();
		fl_quitBtnPan.setVgap(25);
		quitBtnPan.setBackground(Color.DARK_GRAY);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(Color.DARK_GRAY);
		
		GroupLayout gl_menuPan = new GroupLayout(menuPan);
		gl_menuPan.setHorizontalGroup(
			gl_menuPan.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_menuPan.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_menuPan.createParallelGroup(Alignment.TRAILING)
						.addComponent(titlePanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
						.addComponent(quitBtnPan, GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
						.addComponent(catBtnPan, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
						.addComponent(infosBtnPan, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
						.addComponent(codrivingBtnPan, GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_menuPan.setVerticalGroup(
			gl_menuPan.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_menuPan.createSequentialGroup()
					.addContainerGap()
					.addComponent(titlePanel, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(codrivingBtnPan, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(infosBtnPan, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(catBtnPan, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(quitBtnPan, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		//FIN DE SECTION : Création des Panels du menu
		
		//SECTION : Création des éléments du menu
		
		JLabel lblQueVoulezvouFaire = new JLabel("Que voulez-vous faire ?");
		lblQueVoulezvouFaire.setMaximumSize(new Dimension(400, 50));
		lblQueVoulezvouFaire.setFont(new Font("Century Gothic", Font.BOLD, 65));
		lblQueVoulezvouFaire.setHorizontalAlignment(SwingConstants.CENTER);
		lblQueVoulezvouFaire.setPreferredSize(new Dimension(750, 80));
		lblQueVoulezvouFaire.setForeground(Color.WHITE);
		lblQueVoulezvouFaire.setAlignmentX(Component.CENTER_ALIGNMENT);
		titlePanel.add(lblQueVoulezvouFaire);
		
		//Bouton servant à quitter l'application
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setFocusable(false);
		
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		
		btnQuitter.setPreferredSize(new Dimension(400, 50));
		btnQuitter.setForeground(Color.WHITE);
		btnQuitter.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		btnQuitter.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnQuitter.setBackground(Color.DARK_GRAY);
		quitBtnPan.add(btnQuitter);
		
		//Bouton menant à la fenêtre des catégories
		JButton btnCatgories = new JButton("Cat\u00E9gories");
		btnCatgories.setFocusable(false);
		
		btnCatgories.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)frmMenu.getContentPane().getLayout() ;
				cl.show(frmMenu.getContentPane(), "name_66898688181732");
			}
		});
		
		btnCatgories.setPreferredSize(new Dimension(400, 50));
		btnCatgories.setForeground(Color.WHITE);
		btnCatgories.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		btnCatgories.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnCatgories.setBackground(Color.DARK_GRAY);
		catBtnPan.add(btnCatgories);
		
		//Bouton menant à la fenêtre des informations 
		JButton btnVosInformations = new JButton("Vos informations");
		btnVosInformations.setFocusable(false);
		
		btnVosInformations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)frmMenu.getContentPane().getLayout() ;
				cl.show(frmMenu.getContentPane(), "name_61436559030751");
			}
		});
		
		btnVosInformations.setPreferredSize(new Dimension(400, 50));
		btnVosInformations.setForeground(Color.WHITE);
		btnVosInformations.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		btnVosInformations.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnVosInformations.setBackground(Color.DARK_GRAY);
		infosBtnPan.add(btnVosInformations);
		
		//Bouton menant à la fenêtre du covoiturage
		JButton btnNewButton = new JButton("Covoiturage");
		btnNewButton.setFocusable(false);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout)frmMenu.getContentPane().getLayout() ;
				cl.show(frmMenu.getContentPane(), "name_1879696200068");
			}
		});
		
		btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnNewButton.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		btnNewButton.setPreferredSize(new Dimension(400, 50));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.DARK_GRAY);
		codrivingBtnPan.add(btnNewButton);
		
		menuPan.setLayout(gl_menuPan);
		
		frmMenu.getContentPane().setLayout(new CardLayout(0, 0));
		frmMenu.getContentPane().add(menuPan, "name_1028370164432");
		
		//FIN DE SECTION : Création des éléments du menu
		
		//SECTION : Création des Panels de la sélection de Balade
		
		JPanel codrivingBalPan = new JPanel();
		codrivingBalPan.setVisible(false);
		codrivingBalPan.setBackground(Color.DARK_GRAY);
		frmMenu.getContentPane().add(codrivingBalPan, "name_1879696200068");
		
		JPanel titleBalPan = new JPanel();
		titleBalPan.setBackground(Color.DARK_GRAY);
		
		JPanel balPan = new JPanel();
		balPan.setBackground(Color.DARK_GRAY);
		GroupLayout gl_codrivingBalPan = new GroupLayout(codrivingBalPan);
		gl_codrivingBalPan.setHorizontalGroup(
			gl_codrivingBalPan.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_codrivingBalPan.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_codrivingBalPan.createParallelGroup(Alignment.TRAILING)
						.addComponent(balPan, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
						.addComponent(titleBalPan, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_codrivingBalPan.setVerticalGroup(
			gl_codrivingBalPan.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_codrivingBalPan.createSequentialGroup()
					.addContainerGap()
					.addComponent(titleBalPan, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(balPan, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		//FIN DE SECTION : Création des Panels de la sélection de Balade
		
		//SECTION : Création des éléments de la sélection de Balade
		
		//Table qui va contenir les balades de la catégorie de la personne connectée
		balTable = new JTable();
		balTable.setFocusable(false);
		balTable.setGridColor(new Color(0, 0, 0));
		balTable.setShowVerticalLines(false);
		balTable.setPreferredScrollableViewportSize(new Dimension(740, 320));
		balTable.setRowHeight(60);
		balTable.setFont(new Font("Arial Black", Font.PLAIN, 25));
		balTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		balTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		balTable.setPreferredSize(new Dimension(740, 315));
		balTable.setSelectionBackground(new Color(128, 128, 128));
		balTable.setForeground(Color.WHITE);
		balTable.setBackground(Color.DARK_GRAY);
		balTable.getTableHeader().setFont(new Font("Arial Black", Font.PLAIN, 20));
		
		//Appel de la fonction de remplissage de la table et d'une liste contenant ces mêmes balades
		List<Balade> listBal = populateBal(new BaladeTableModel());
		
		JScrollPane balSP = new JScrollPane(balTable);
		balSP.setBorder(new LineBorder(new Color(0, 0, 0)));
		balSP.setForeground(new Color(0, 0, 0));
		balSP.setPreferredSize(new Dimension(740, 350));
		balSP.setBackground(Color.DARK_GRAY);
		balPan.add(balSP);
		
		JLabel titleCo = new JLabel("");
		
		//Bouton permettant de choisir une balade
		JButton selectBalBtn = new JButton("Choisir");
		
		selectBalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Changement de fenêtre
				CardLayout cl = (CardLayout)frmMenu.getContentPane().getLayout() ;
				cl.show(frmMenu.getContentPane(), "name_176546207356321");
				
				//Appel de la fonction de remplissage de la table 
				//et d'une liste contenant les disponibilités pour cette balade
				selectedBal = listBal.get(balTable.getSelectedRow());
				populateVoit(new VoitureTableModel());
				titleCo.setText(selectedBal.getLibelle() + " (Forfais : " + selectedBal.getFraisDepla() + " €)");
			}
		});
		
		selectBalBtn.setFocusable(false);
		selectBalBtn.setForeground(Color.WHITE);
		selectBalBtn.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		selectBalBtn.setPreferredSize(new Dimension(300, 50));
		selectBalBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		selectBalBtn.setBackground(Color.DARK_GRAY);
		balPan.add(selectBalBtn);
		
		//Bouton premettant de revenir en arrière
		JButton balBackBtn = new JButton("Retour");
		balBackBtn.setFocusable(false);
		balBackBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout)frmMenu.getContentPane().getLayout() ;
				cl.show(frmMenu.getContentPane(), "name_1028370164432");
			}
		});
		
		balBackBtn.setPreferredSize(new Dimension(300, 50));
		balBackBtn.setForeground(Color.WHITE);
		balBackBtn.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		balBackBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		balBackBtn.setBackground(Color.DARK_GRAY);
		balPan.add(balBackBtn);
		
		JLabel lblBalades = new JLabel("Balades");
		lblBalades.setFont(new Font("Century Gothic", Font.BOLD, 65));
		lblBalades.setHorizontalAlignment(SwingConstants.CENTER);
		lblBalades.setPreferredSize(new Dimension(750, 75));
		lblBalades.setForeground(Color.WHITE);
		titleBalPan.add(lblBalades);
		codrivingBalPan.setLayout(gl_codrivingBalPan);
		
		//FIN DE SECTION : Création des éléments de la sélection de Balade
		
		//SECTION : Création des Panels de la fenêtre d'informations
		
		JPanel infosPan = new JPanel();
		infosPan.setBackground(Color.DARK_GRAY);
		frmMenu.getContentPane().add(infosPan, "name_61436559030751");
		
		JPanel infoTitlePan = new JPanel();
		infoTitlePan.setBackground(Color.DARK_GRAY);
		
		JLabel infoTitle = new JLabel("Vos informations");
		infoTitle.setPreferredSize(new Dimension(750, 75));
		infoTitle.setHorizontalAlignment(SwingConstants.CENTER);
		infoTitle.setForeground(Color.WHITE);
		infoTitle.setFont(new Font("Century Gothic", Font.BOLD, 65));
		infoTitlePan.add(infoTitle);
		
		JPanel infosPanIn = new JPanel();
		infosPanIn.setBackground(Color.DARK_GRAY);
		
		JPanel infos = new JPanel();
		infos.setBackground(Color.DARK_GRAY);
		infos.setPreferredSize(new Dimension(740, 350));
		infosPanIn.add(infos);
		infos.setLayout(new GridLayout(8, 2, 0, 0));
		
		//FIN DE SECTION : Création des Panels de la fenêtre d'informations
		
		//SECTION : Création des éléments de la fenêtre d'informations
		
		JLabel nomLbl = new JLabel("Nom :");
		nomLbl.setForeground(Color.WHITE);
		nomLbl.setFont(new Font("Arial Black", Font.PLAIN, 20));
		infos.add(nomLbl);
		
		JLabel nomTxt = new JLabel(connected.getNom());
		nomTxt.setForeground(Color.WHITE);
		nomTxt.setFont(new Font("Century Gothic", Font.BOLD, 20));
		infos.add(nomTxt);
		
		JLabel prenomLbl = new JLabel("Pr\u00E9nom :");
		prenomLbl.setForeground(Color.WHITE);
		prenomLbl.setFont(new Font("Arial Black", Font.PLAIN, 20));
		infos.add(prenomLbl);
		
		JLabel prenomTxt = new JLabel(connected.getPrenom());
		prenomTxt.setForeground(Color.WHITE);
		prenomTxt.setFont(new Font("Century Gothic", Font.BOLD, 20));
		infos.add(prenomTxt);
		
		JLabel dateNaissLbl = new JLabel("Date de naissance :");
		dateNaissLbl.setForeground(Color.WHITE);
		dateNaissLbl.setFont(new Font("Arial Black", Font.PLAIN, 20));
		infos.add(dateNaissLbl);
		
		JLabel dateNaissTxt = new JLabel(connected.getDateNaiss().toString());
		dateNaissTxt.setForeground(Color.WHITE);
		dateNaissTxt.setFont(new Font("Century Gothic", Font.BOLD, 20));
		infos.add(dateNaissTxt);
		
		JLabel rueLbl = new JLabel("Rue :");
		rueLbl.setForeground(Color.WHITE);
		rueLbl.setFont(new Font("Arial Black", Font.PLAIN, 20));
		infos.add(rueLbl);
		
		JLabel rueTxt = new JLabel(connected.getAdr().getRue() + " " + connected.getAdr().getNum());
		rueTxt.setFont(new Font("Century Gothic", Font.BOLD, 20));
		rueTxt.setForeground(Color.WHITE);
		infos.add(rueTxt);
		
		JLabel codePostLbl = new JLabel("Code postal :");
		codePostLbl.setForeground(Color.WHITE);
		codePostLbl.setFont(new Font("Arial Black", Font.PLAIN, 20));
		infos.add(codePostLbl);
		
		JLabel codePostTxt = new JLabel(connected.getAdr().getCodePost());
		codePostTxt.setForeground(Color.WHITE);
		codePostTxt.setFont(new Font("Century Gothic", Font.BOLD, 20));
		infos.add(codePostTxt);
		
		JLabel villeLbl = new JLabel("Ville :");
		villeLbl.setForeground(Color.WHITE);
		villeLbl.setFont(new Font("Arial Black", Font.PLAIN, 20));
		infos.add(villeLbl);
		
		JLabel villeTxt = new JLabel(connected.getAdr().getVille());
		villeTxt.setForeground(Color.WHITE);
		villeTxt.setFont(new Font("Century Gothic", Font.BOLD, 20));
		infos.add(villeTxt);
		
		JLabel paysLbl = new JLabel("Pays :");
		paysLbl.setForeground(Color.WHITE);
		paysLbl.setFont(new Font("Arial Black", Font.PLAIN, 20));
		infos.add(paysLbl);
		
		JLabel paysTxt = new JLabel(connected.getAdr().getPays());
		paysTxt.setForeground(Color.WHITE);
		paysTxt.setFont(new Font("Century Gothic", Font.BOLD, 20));
		infos.add(paysTxt);
		
		float suppl = membreDAO.getSuppl(connected.getId());
		JLabel otherLbl = new JLabel("Cotisation :");
		JLabel otherTxt = new JLabel(Float.toString(((Membre) connected).getCotisation()) + Float.toString(suppl) + " €");
		
		otherLbl.setForeground(Color.WHITE);
		otherLbl.setFont(new Font("Arial Black", Font.PLAIN, 20));
		infos.add(otherLbl);
		
		otherTxt.setForeground(Color.WHITE);
		otherTxt.setFont(new Font("Century Gothic", Font.BOLD, 20));
		infos.add(otherTxt);
		
		//Bouton de retour en arrière
		JButton infosBackBtn = new JButton("Retour");
		infosBackBtn.setFocusable(false);
		
		infosBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)frmMenu.getContentPane().getLayout() ;
				cl.show(frmMenu.getContentPane(), "name_1028370164432");
			}
		});
		
		infosBackBtn.setPreferredSize(new Dimension(300, 50));
		infosBackBtn.setForeground(Color.WHITE);
		infosBackBtn.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		infosBackBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		infosBackBtn.setBackground(Color.DARK_GRAY);
		infosPanIn.add(infosBackBtn);
		
		GroupLayout gl_infosPan = new GroupLayout(infosPan);
		gl_infosPan.setHorizontalGroup(
			gl_infosPan.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_infosPan.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_infosPan.createParallelGroup(Alignment.LEADING)
						.addComponent(infoTitlePan, GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
						.addComponent(infosPanIn, GroupLayout.PREFERRED_SIZE, 748, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_infosPan.setVerticalGroup(
			gl_infosPan.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_infosPan.createSequentialGroup()
					.addContainerGap()
					.addComponent(infoTitlePan, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(infosPanIn, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		infosPan.setLayout(gl_infosPan);
		
		//FIN DE SECTION : Création des éléments de la fenêtre d'informations
		
		//SECTION : Création des Panels de la fenêtre des catégories
		
		JPanel catPan = new JPanel();
		catPan.setBackground(Color.DARK_GRAY);
		frmMenu.getContentPane().add(catPan, "name_66898688181732");
		
		JPanel titleCatPan = new JPanel();
		FlowLayout flowLayout = (FlowLayout) titleCatPan.getLayout();
		flowLayout.setVgap(1);
		flowLayout.setHgap(1);
		titleCatPan.setBackground(Color.DARK_GRAY);
		
		JLabel titleCat = new JLabel("Cat\u00E9gories");
		titleCat.setVerticalAlignment(SwingConstants.TOP);
		titleCat.setPreferredSize(new Dimension(750, 82));
		titleCat.setHorizontalAlignment(SwingConstants.CENTER);
		titleCat.setForeground(Color.WHITE);
		titleCat.setFont(new Font("Century Gothic", Font.BOLD, 65));
		titleCatPan.add(titleCat);
		
		JPanel catPanIn = new JPanel();
		catPanIn.setBackground(Color.DARK_GRAY);
		catPanIn.setPreferredSize(new Dimension(740, 350));
		
		//FIN DE SECTION : Création des Panels de la fenêtre des catégories
		
		//SECTION : Création des éléments de la fenêtre des catégories
		
		//Bouton de retour en arrière
		JButton backCatBtn = new JButton("Retour");
		
		backCatBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout)frmMenu.getContentPane().getLayout() ;
				cl.show(frmMenu.getContentPane(), "name_1028370164432");
			}
		});
		
		backCatBtn.setPreferredSize(new Dimension(300, 50));
		backCatBtn.setForeground(Color.WHITE);
		backCatBtn.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		backCatBtn.setFocusable(false);
		backCatBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		backCatBtn.setBackground(Color.DARK_GRAY);
		
		GroupLayout gl_catPan = new GroupLayout(catPan);
		gl_catPan.setHorizontalGroup(
			gl_catPan.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_catPan.createSequentialGroup()
					.addGroup(gl_catPan.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_catPan.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_catPan.createParallelGroup(Alignment.TRAILING)
								.addComponent(catPanIn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
								.addComponent(titleCatPan, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)))
						.addGroup(gl_catPan.createSequentialGroup()
							.addGap(234)
							.addComponent(backCatBtn, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
							.addGap(229)))
					.addContainerGap())
		);
		gl_catPan.setVerticalGroup(
			gl_catPan.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_catPan.createSequentialGroup()
					.addContainerGap()
					.addComponent(titleCatPan, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(catPanIn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(backCatBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		catPanIn.setLayout(new GridLayout(5, 3, 0, 0));
		
		JLabel lblLibell = new JLabel("Libell\u00E9");
		lblLibell.setOpaque(true);
		lblLibell.setBackground(Color.GRAY);
		lblLibell.setFont(new Font("Century Gothic", Font.BOLD, 25));
		lblLibell.setForeground(Color.WHITE);
		catPanIn.add(lblLibell);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setOpaque(true);
		lblStatus.setBackground(Color.GRAY);
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setFont(new Font("Century Gothic", Font.BOLD, 25));
		catPanIn.add(lblStatus);
		
		JLabel lblInscription = new JLabel("Inscription (5\u20AC)");
		lblInscription.setOpaque(true);
		lblInscription.setBackground(Color.GRAY);
		lblInscription.setForeground(Color.WHITE);
		lblInscription.setFont(new Font("Century Gothic", Font.BOLD, 25));
		catPanIn.add(lblInscription);
		
		JLabel lblRandonneur = new JLabel("Randonneur");
		lblRandonneur.setFont(new Font("Century Gothic", Font.BOLD, 25));
		lblRandonneur.setForeground(Color.WHITE);
		catPanIn.add(lblRandonneur);
		
		lblStatusRand = new JLabel("Non inscrit");
		
		//Bouton servant à enregistrer l'inscription d'un membre à une autre catégorie (Randonneur)
		JButton btnInscRand = new JButton("S'inscrire");
		
		btnInscRand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblStatusRand.setText("Inscrit");
				btnInscRand.setEnabled(false);
				//Ajout à la catégorie 1 (Randonneur)
				((Membre)connected).ajouterCat(1);
			}
		});
		
		//Affichage différent si la personne est déjà inscrite dans cette catégorie
		for(Categorie c : ((Membre)connected).getListeCat()) {
			if(c instanceof VTT && ((VTT) c).getType().toString() == "Randonneur") {
				lblStatusRand.setText("Inscrit");
				btnInscRand.setEnabled(false);
			}	
		}
		
		lblStatusRand.setForeground(Color.WHITE);
		lblStatusRand.setFont(new Font("Century Gothic", Font.BOLD, 25));
		catPanIn.add(lblStatusRand);
		
		btnInscRand.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnInscRand.setFont(new Font("Century Gothic", Font.BOLD, 25));
		btnInscRand.setBackground(Color.DARK_GRAY);
		btnInscRand.setForeground(Color.WHITE);
		catPanIn.add(btnInscRand);
		
		JLabel lblDescendeur = new JLabel("Descendeur");
		lblDescendeur.setFont(new Font("Century Gothic", Font.BOLD, 25));
		lblDescendeur.setForeground(Color.WHITE);
		catPanIn.add(lblDescendeur);
		
		JLabel lblStatusDesc = new JLabel("Non inscrit");
		
		//Bouton servant à enregistrer l'inscription d'un membre à une autre catégorie (Descendeur)
		JButton btnInscDesc = new JButton("S'inscrire");
		btnInscDesc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblStatusDesc.setText("Inscrit");
				btnInscDesc.setEnabled(false);
				//Ajout à la catégorie 2 (Descendeur)
				((Membre)connected).ajouterCat(2);
			}
		});
		
		//Affichage différent si la personne est déjà inscrite dans cette catégorie
		for(Categorie c : ((Membre)connected).getListeCat()) {
			if(c instanceof VTT && ((VTT) c).getType().toString() == "Descendeur") {
				lblStatusDesc.setText("Inscrit");
				btnInscDesc.setEnabled(false);
			}	
		}
		
		lblStatusDesc.setForeground(Color.WHITE);
		lblStatusDesc.setFont(new Font("Century Gothic", Font.BOLD, 25));
		catPanIn.add(lblStatusDesc);
		
		btnInscDesc.setForeground(Color.WHITE);
		btnInscDesc.setFont(new Font("Century Gothic", Font.BOLD, 25));
		btnInscDesc.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnInscDesc.setBackground(Color.DARK_GRAY);
		catPanIn.add(btnInscDesc);
		
		JLabel lblTrialiste = new JLabel("Trialiste");
		lblTrialiste.setFont(new Font("Century Gothic", Font.BOLD, 25));
		lblTrialiste.setForeground(Color.WHITE);
		catPanIn.add(lblTrialiste);
		
		JLabel lblStatusTri = new JLabel("Non inscrit");
		
		//Bouton servant à enregistrer l'inscription d'un membre à une autre catégorie (Trialiste)
		JButton btnInscTri = new JButton("S'inscrire");
		btnInscTri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblStatusTri.setText("Inscrit");
				btnInscTri.setEnabled(false);
				//Ajout à la catégorie 3 (Trialiste)
				((Membre)connected).ajouterCat(3);
			}
		});
		
		//Affichage différent si la personne est déjà inscrite dans cette catégorie
		for(Categorie c : ((Membre)connected).getListeCat()) {
			if(c instanceof VTT && ((VTT) c).getType().toString() == "Trialiste") {
				lblStatusTri.setText("Inscrit");
				btnInscTri.setEnabled(false);
			}	
		}
		
		lblStatusTri.setForeground(Color.WHITE);
		lblStatusTri.setFont(new Font("Century Gothic", Font.BOLD, 25));
		catPanIn.add(lblStatusTri);
		
		btnInscTri.setForeground(Color.WHITE);
		btnInscTri.setFont(new Font("Century Gothic", Font.BOLD, 25));
		btnInscTri.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnInscTri.setBackground(Color.DARK_GRAY);
		catPanIn.add(btnInscTri);
		
		JLabel lblCyclo = new JLabel("Cyclo");
		lblCyclo.setFont(new Font("Century Gothic", Font.BOLD, 25));
		lblCyclo.setForeground(Color.WHITE);
		catPanIn.add(lblCyclo);
		
		JLabel lblStatusCyc = new JLabel("Non inscrit");
		
		//Bouton servant à enregistrer l'inscription d'un membre à une autre catégorie (Cyclo)
		JButton btnInscCyc = new JButton("S'inscrire");
		btnInscCyc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblStatusCyc.setText("Inscrit");
				btnInscCyc.setEnabled(false);
				//Ajout à la catégorie 4 (Cyclo)
				((Membre)connected).ajouterCat(4);
			}
		});
		
		//Affichage différent si la personne est déjà inscrite dans cette catégorie
		for(Categorie c : ((Membre)connected).getListeCat()) {
			if(c instanceof Cyclo) {
				lblStatusCyc.setText("Inscrit");
				btnInscCyc.setEnabled(false);
			}
		}
		
		lblStatusCyc.setForeground(Color.WHITE);
		lblStatusCyc.setFont(new Font("Century Gothic", Font.BOLD, 25));
		catPanIn.add(lblStatusCyc);
		
		btnInscCyc.setForeground(Color.WHITE);
		btnInscCyc.setFont(new Font("Century Gothic", Font.BOLD, 25));
		btnInscCyc.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnInscCyc.setBackground(Color.DARK_GRAY);
		catPanIn.add(btnInscCyc);
		catPan.setLayout(gl_catPan);
		
		//FIN DE SECTION : Création des éléments de la fenêtre des catégories
		
		//SECTION : Création des Panels et éléments de la fenêtre de covoiturage
		
		JPanel codrivingPan = new JPanel();
		codrivingPan.setBackground(Color.DARK_GRAY);
		frmMenu.getContentPane().add(codrivingPan, "name_176546207356321");
		
		JPanel titleCoPan = new JPanel();
		titleCoPan.setBackground(Color.DARK_GRAY);
		
		titleCo.setPreferredSize(new Dimension(750, 75));
		titleCo.setHorizontalAlignment(SwingConstants.CENTER);
		titleCo.setForeground(Color.WHITE);
		titleCo.setFont(new Font("Century Gothic", Font.BOLD, 45));
		titleCoPan.add(titleCo);
		
		JPanel coPan = new JPanel();
		coPan.setBackground(Color.DARK_GRAY);
		
		coSP = new JScrollPane((Component) null);
		coSP.setPreferredSize(new Dimension(740, 350));
		coSP.setForeground(Color.BLACK);
		coSP.setBorder(new LineBorder(new Color(0, 0, 0)));
		coSP.setBackground(Color.DARK_GRAY);
		coPan.add(coSP);
		
		//Table qui va contenir les disponibilités de la balade sélectionnée
		coTable = new JTable();
		coTable.setShowVerticalLines(false);
		coTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		coTable.setSelectionBackground(Color.GRAY);
		coTable.setRowHeight(60);
		coTable.setPreferredSize(new Dimension(740, 315));
		coTable.setPreferredScrollableViewportSize(new Dimension(740, 320));
		coTable.setGridColor(Color.BLACK);
		coTable.setForeground(Color.WHITE);
		coTable.setFont(new Font("Arial Black", Font.PLAIN, 25));
		coTable.setFocusable(false);
		coTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		coTable.setBackground(Color.DARK_GRAY);
		coTable.setModel(new VoitureTableModel());
		coSP.setViewportView(coTable);
		
		//Bouton de retour en arrière
		JButton coBackBtn = new JButton("Retour");
		coBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout)frmMenu.getContentPane().getLayout() ;
				cl.show(frmMenu.getContentPane(), "name_1879696200068");
			}
		});
		
		//Bouton de choix de la disponibilité
		JButton coChoiceBtn = new JButton("Choisir");
		coChoiceBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				Boolean isIn = false;
				//Détermine si le membre est déjà inscrit dans cette voiture
				for(Membre m : listVoit.get(coTable.getSelectedRow()).getPassagers()) {
					if(m.getId() == connected.getId())
						isIn = true;
				}
				
				//Ajoute le membre dans la voiture s'il n'y est pas déjà et qu'il y a encore de la place
				if(!isIn) {
					if(listVoit.get(coTable.getSelectedRow()).getPassagers().size() < listVoit.get(coTable.getSelectedRow()).getNbPlaces()) {
						voitureDAO.addPerson((int)connected.getId(), selectedBal.getId(), listVoit.get(coTable.getSelectedRow()).getId());
						populateVoit(new VoitureTableModel());
					}
					else
						JOptionPane.showMessageDialog(frmMenu, "Voiture complète");
				}
				else
					JOptionPane.showMessageDialog(frmMenu, "Vous faites déjà partie de cette voiture");
			}
		});
		
		coChoiceBtn.setPreferredSize(new Dimension(200, 50));
		coChoiceBtn.setForeground(Color.WHITE);
		coChoiceBtn.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		coChoiceBtn.setFocusable(false);
		coChoiceBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		coChoiceBtn.setBackground(Color.DARK_GRAY);
		coPan.add(coChoiceBtn);
		
		//Bouton menant à la fenêtre d'ajout d'une voiture
		JButton coAddBtn = new JButton("Ajouter");
		
		coAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)frmMenu.getContentPane().getLayout() ;
				cl.show(frmMenu.getContentPane(), "name_179564125834505");
			}
		});
		
		coAddBtn.setPreferredSize(new Dimension(200, 50));
		coAddBtn.setForeground(Color.WHITE);
		coAddBtn.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		coAddBtn.setFocusable(false);
		coAddBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		coAddBtn.setBackground(Color.DARK_GRAY);
		coPan.add(coAddBtn);
		coBackBtn.setPreferredSize(new Dimension(200, 50));
		coBackBtn.setForeground(Color.WHITE);
		coBackBtn.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		coBackBtn.setFocusable(false);
		coBackBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		coBackBtn.setBackground(Color.DARK_GRAY);
		coPan.add(coBackBtn);
		
		GroupLayout gl_codrivingPan = new GroupLayout(codrivingPan);
		gl_codrivingPan.setHorizontalGroup(
			gl_codrivingPan.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_codrivingPan.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_codrivingPan.createParallelGroup(Alignment.LEADING)
						.addComponent(titleCoPan, GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
						.addComponent(coPan, GroupLayout.PREFERRED_SIZE, 748, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_codrivingPan.setVerticalGroup(
			gl_codrivingPan.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_codrivingPan.createSequentialGroup()
					.addContainerGap()
					.addComponent(titleCoPan, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(coPan, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		codrivingPan.setLayout(gl_codrivingPan);
		
		JPanel addCodrivingPan = new JPanel();
		addCodrivingPan.setBackground(Color.DARK_GRAY);
		frmMenu.getContentPane().add(addCodrivingPan, "name_179564125834505");
		
		//FIN DE SECTION : Création des Panels et des éléments de la fenêtre de covoiturage
		
		//SECTION : Création des Panels de la fenêtre d'ajout de voiture
		
		JPanel addCarTitlePan = new JPanel();
		addCarTitlePan.setBackground(Color.DARK_GRAY);
		
		JLabel addCarLbl = new JLabel("Ajouter votre voiture");
		addCarLbl.setPreferredSize(new Dimension(750, 75));
		addCarLbl.setHorizontalAlignment(SwingConstants.CENTER);
		addCarLbl.setForeground(Color.WHITE);
		addCarLbl.setFont(new Font("Century Gothic", Font.BOLD, 65));
		addCarTitlePan.add(addCarLbl);
		
		JPanel addCarPan = new JPanel();
		addCarPan.setBackground(Color.DARK_GRAY);
		
		GroupLayout gl_addCodrivingPan = new GroupLayout(addCodrivingPan);
		gl_addCodrivingPan.setHorizontalGroup(
			gl_addCodrivingPan.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_addCodrivingPan.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_addCodrivingPan.createParallelGroup(Alignment.LEADING)
						.addComponent(addCarPan, GroupLayout.PREFERRED_SIZE, 751, GroupLayout.PREFERRED_SIZE)
						.addComponent(addCarTitlePan, GroupLayout.PREFERRED_SIZE, 748, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_addCodrivingPan.setVerticalGroup(
			gl_addCodrivingPan.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_addCodrivingPan.createSequentialGroup()
					.addContainerGap()
					.addComponent(addCarTitlePan, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(addCarPan, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		addCarPan.setLayout(new GridLayout(3, 2, 0, 0));
		
		JPanel numImmatLblPan = new JPanel();
		numImmatLblPan.setBackground(Color.DARK_GRAY);
		addCarPan.add(numImmatLblPan);
		
		JLabel numImmatLbl = new JLabel("Numero d'immatriculation :");
		numImmatLbl.setFont(new Font("Arial Black", Font.PLAIN, 17));
		numImmatLbl.setForeground(Color.WHITE);
		
		GroupLayout gl_numImmatLblPan = new GroupLayout(numImmatLblPan);
		gl_numImmatLblPan.setHorizontalGroup(
			gl_numImmatLblPan.createParallelGroup(Alignment.LEADING)
				.addComponent(numImmatLbl, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
		);
		gl_numImmatLblPan.setVerticalGroup(
			gl_numImmatLblPan.createParallelGroup(Alignment.LEADING)
				.addComponent(numImmatLbl, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
		);
		
		numImmatLblPan.setLayout(gl_numImmatLblPan);
		
		JPanel numImmatFldPan = new JPanel();
		numImmatFldPan.setBackground(Color.DARK_GRAY);
		addCarPan.add(numImmatFldPan);
		
		//FIN DE SECTION : Création des Panels de la fenêtre d'ajout de voiture
		
		//SECTION : Création des éléments de la fenêtre d'ajout de voiture
		
		JLabel numImmatStart = new JLabel("1-");
		numImmatStart.setFont(new Font("Arial Black", Font.PLAIN, 17));
		numImmatStart.setForeground(Color.WHITE);
		numImmatStart.setBackground(Color.DARK_GRAY);
		
		numImmatFld1 = new JTextField();
		numImmatFld1.setToolTipText("1-ABC-123");
		numImmatFld1.setForeground(Color.WHITE);
		numImmatFld1.setFont(new Font("Arial Black", Font.PLAIN, 17));
		numImmatFld1.setColumns(10);
		numImmatFld1.setBackground(Color.GRAY);
		
		numImmatFld2 = new JTextField();
		numImmatFld2.setToolTipText("1-ABC-123");
		numImmatFld2.setForeground(Color.WHITE);
		numImmatFld2.setFont(new Font("Arial Black", Font.PLAIN, 17));
		numImmatFld2.setColumns(10);
		numImmatFld2.setBackground(Color.GRAY);
		
		GroupLayout gl_numImmatFldPan = new GroupLayout(numImmatFldPan);
		gl_numImmatFldPan.setHorizontalGroup(
			gl_numImmatFldPan.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_numImmatFldPan.createSequentialGroup()
					.addContainerGap()
					.addComponent(numImmatStart)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(numImmatFld1, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(numImmatFld2, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		gl_numImmatFldPan.setVerticalGroup(
			gl_numImmatFldPan.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_numImmatFldPan.createSequentialGroup()
					.addGap(56)
					.addGroup(gl_numImmatFldPan.createParallelGroup(Alignment.BASELINE)
						.addComponent(numImmatStart)
						.addComponent(numImmatFld1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(numImmatFld2, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(52, Short.MAX_VALUE))
		);
		
		numImmatFldPan.setLayout(gl_numImmatFldPan);
		
		JPanel nbPlacesLblPan = new JPanel();
		nbPlacesLblPan.setBackground(Color.DARK_GRAY);
		addCarPan.add(nbPlacesLblPan);
		
		JLabel nbPlacesLbl = new JLabel("Nombre de places (chauffeur compris) :");
		nbPlacesLbl.setFont(new Font("Arial Black", Font.PLAIN, 17));
		nbPlacesLbl.setForeground(Color.WHITE);
		
		GroupLayout gl_nbPlacesLblPan = new GroupLayout(nbPlacesLblPan);
		gl_nbPlacesLblPan.setHorizontalGroup(
			gl_nbPlacesLblPan.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_nbPlacesLblPan.createSequentialGroup()
					.addComponent(nbPlacesLbl, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(53, Short.MAX_VALUE))
		);
		gl_nbPlacesLblPan.setVerticalGroup(
			gl_nbPlacesLblPan.createParallelGroup(Alignment.TRAILING)
				.addComponent(nbPlacesLbl, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
		);
		
		nbPlacesLblPan.setLayout(gl_nbPlacesLblPan);
		
		JPanel nbPlacesFldPan = new JPanel();
		nbPlacesFldPan.setBackground(Color.DARK_GRAY);
		addCarPan.add(nbPlacesFldPan);
		
		nbPlacesFld = new JTextField();
		nbPlacesFld.setFont(new Font("Arial Black", Font.PLAIN, 17));
		nbPlacesFld.setForeground(Color.WHITE);
		nbPlacesFld.setColumns(10);
		nbPlacesFld.setBackground(Color.GRAY);
		
		GroupLayout gl_nbPlacesFldPan = new GroupLayout(nbPlacesFldPan);
		gl_nbPlacesFldPan.setHorizontalGroup(
			gl_nbPlacesFldPan.createParallelGroup(Alignment.LEADING)
				.addComponent(nbPlacesFld, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
		);
		gl_nbPlacesFldPan.setVerticalGroup(
			gl_nbPlacesFldPan.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_nbPlacesFldPan.createSequentialGroup()
					.addGap(57)
					.addComponent(nbPlacesFld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(56, Short.MAX_VALUE))
		);
		
		nbPlacesFldPan.setLayout(gl_nbPlacesFldPan);
		
		JPanel validateBtnPan = new JPanel();
		validateBtnPan.setBackground(Color.DARK_GRAY);
		addCarPan.add(validateBtnPan);
		
		//Bouton permettant de valider les saisie et d'enregistrer la disponibilité
		JButton validateBtn = new JButton("Valider");
		
		validateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Gestion d'exceptions personnalisées
				try {
					//Si les champs du numéro d'immatriculation sont remplis...(1)
					if(!numImmatFld1.getText().isEmpty() && !numImmatFld2.getText().isEmpty() && !nbPlacesFld.getText().isEmpty()) {
						//... et s'ils ne contiennent pas plus de 3 caractères (2)
						if(numImmatFld1.getText().toCharArray().length <= 3 && numImmatFld2.getText().toCharArray().length <= 3) {
							//On crée la voiture et on l'enregistre
							String numImmat = "1-"+numImmatFld1.getText()+"-"+numImmatFld2.getText();
							Voiture newV = new Voiture(0, numImmat, (Membre)connected, Integer.parseInt(nbPlacesFld.getText()), selectedBal);
							
							voitureDAO.create(newV);
							newV.setPassagers(membreDAO.getPassagers(newV.getId(), selectedBal.getId()));
							//On rempli le tableau des disponibilités à nouveau
							populateVoit(new VoitureTableModel());
							
							//Et on revient à l'écran  de sélection
							CardLayout cl = (CardLayout)frmMenu.getContentPane().getLayout() ;
							cl.show(frmMenu.getContentPane(), "name_176546207356321");
						}
						else {
							//Sinon on vide les inputs et on affiche une erreur (2)
							numImmatFld1.setText("");
							numImmatFld2.setText("");
							throw(new Exception("Veuillez respecter le format de plaque"));
						}
					}
					else {
						//Sinon on affiche une erreur (1)
						throw(new Exception("Veuillez remplir tous les champs."));
					}
				}
				catch(Exception e1) {
					//Afichage de l'erreur dans un message dialog
					JOptionPane.showMessageDialog(frmMenu, e1.getMessage());
				}
			}
		});
		
		validateBtn.setPreferredSize(new Dimension(300, 50));
		validateBtn.setForeground(Color.WHITE);
		validateBtn.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		validateBtn.setFocusable(false);
		validateBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		validateBtn.setBackground(Color.DARK_GRAY);
		
		GroupLayout gl_validateBtnPan = new GroupLayout(validateBtnPan);
		gl_validateBtnPan.setHorizontalGroup(
			gl_validateBtnPan.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_validateBtnPan.createSequentialGroup()
					.addContainerGap()
					.addComponent(validateBtn, GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_validateBtnPan.setVerticalGroup(
			gl_validateBtnPan.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_validateBtnPan.createSequentialGroup()
					.addContainerGap(50, Short.MAX_VALUE)
					.addComponent(validateBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(39))
		);
		
		validateBtnPan.setLayout(gl_validateBtnPan);
		
		JPanel acBackBtnPan = new JPanel();
		acBackBtnPan.setBackground(Color.DARK_GRAY);
		addCarPan.add(acBackBtnPan);
		
		//Bouton de retour en arrière
		JButton acBackBtn = new JButton("Retour");
		acBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)frmMenu.getContentPane().getLayout() ;
				cl.show(frmMenu.getContentPane(), "name_176546207356321");
			}
		});
		
		acBackBtn.setPreferredSize(new Dimension(300, 50));
		acBackBtn.setForeground(Color.WHITE);
		acBackBtn.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		acBackBtn.setFocusable(false);
		acBackBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		acBackBtn.setBackground(Color.DARK_GRAY);
		
		GroupLayout gl_acBackBtnPan = new GroupLayout(acBackBtnPan);
		gl_acBackBtnPan.setHorizontalGroup(
			gl_acBackBtnPan.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_acBackBtnPan.createSequentialGroup()
					.addContainerGap()
					.addComponent(acBackBtn, GroupLayout.PREFERRED_SIZE, 345, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_acBackBtnPan.setVerticalGroup(
			gl_acBackBtnPan.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_acBackBtnPan.createSequentialGroup()
					.addContainerGap(50, Short.MAX_VALUE)
					.addComponent(acBackBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(39))
		);
		acBackBtnPan.setLayout(gl_acBackBtnPan);
		addCodrivingPan.setLayout(gl_addCodrivingPan);
		
		//FIN DE SECTION : Création des éléments de la fenêtre d'ajout de voiture
	}
	
	//FIN DE SECTION : Corps
	
	//SECTION : Méthodes supplémentaires
	
	//Remplissage de la table dezs balade. Retourne une liste de balade
	public List<Balade> populateBal(DefaultTableModel model) {
		String catName;
		List<Integer> idCat = new ArrayList<>();
		List<Balade> listBal = new ArrayList<>();
		
		//Nettoyage du tableau
		balTable.removeAll();
		
		//Récupération de la/des catégorie(s) du membre connecté
		for(Categorie c : ((Membre)connected).getListeCat()) {
			idCat.add(c.getId());
		}
		
		//Récupération des balades correspondantes
		for(Balade b : baladeDAO.findAll(idCat)) {
			if(b.getCat() instanceof VTT) {
				VTT v = (VTT)b.getCat();
				catName = v.getType().toString();
			}
			else
				catName = "Cyclo";
			
			model.addRow(new Object[] {b.getLibelle(), b.getDate().toString(), catName});
			listBal.add(b);
			
			b.setListVoit(voitureDAO.getVoitList(b.getId()));
		}
		balTable.setModel(model);
		
		return listBal;
	}
	
	//Remplissage de la table et de la liste des disponibiltés
	public void populateVoit(DefaultTableModel model) {
		
		//Netoyage du tableau
		coTable.removeAll();
		
		//Nettoyage de la liste si besoin
		if(listVoit.size() > 0)
			listVoit.clear();
		
		//Récupération des voitures
		for(Voiture v : voitureDAO.findAll(selectedBal.getId())) {
			v.setPassagers(membreDAO.getPassagers(v.getId(), selectedBal.getId()));
			model.addRow(new Object[] {v.getChauffeur().getNom().toUpperCase() + " " + v.getChauffeur().getPrenom(), 
									   v.getNumImmat(), 
									   (v.getPassagers().size()) + "/" + v.getNbPlaces()});
			listVoit.add(v);
		}
		coTable.setModel(model);
	}
	
	//FIN DE SECTION : Méthodes supplémentaires
}
