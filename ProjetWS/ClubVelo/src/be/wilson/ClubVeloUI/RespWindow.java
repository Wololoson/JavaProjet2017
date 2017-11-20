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
import be.wilson.ClubVeloDAO.CategorieDAO;
import be.wilson.ClubVeloDAO.MembreDAO;
import be.wilson.ClubVeloDAO.VoitureDAO;
import be.wilson.ClubVeloFactory.AbstractDAOFactory;
import be.wilson.ClubVeloPOJO.Adresse;
import be.wilson.ClubVeloPOJO.Balade;
import be.wilson.ClubVeloPOJO.Categorie;
import be.wilson.ClubVeloPOJO.Membre;
import be.wilson.ClubVeloPOJO.Responsable;

public class RespWindow {
	//SECTION : Création des variables globales
	
	//Affichage
	private JFrame frmResp;
	private JTable calTable;
	private JTable catTable;
	private JTextField txtForfait;
	private JTextField txtLibell;
	private JTextField txtDate;
	private JTextField txtRue;
	private JTextField txtCP;
	private JTextField txtVille;
	private JTextField txtPays;
	private JTextField txtNum;
	private JTextField txtLibellAdd;
	private JTextField txtForfaitAdd;
	private JTextField txtRueAdd;
	private JTextField txtNumAdd;
	private JTextField txtCPAdd;
	private JTextField txtVilleAdd;
	private JTextField txtPaysAdd;
	private JTextField txtDateAddJ;
	private JTextField txtDateAddM;
	private JTextField txtDateAddA;
	
	//Fonctionnalités
	private Responsable connected;
	private AbstractDAOFactory adf;
	private MembreDAO membreDAO;
	private VoitureDAO voitureDAO;
	private BaladeDAO baladeDAO;
	private CategorieDAO categorieDAO;
	private Categorie cat;
	private Balade selectedBal;
	
	//FIN DE SECTION : Création des variables globales
	
	//SECTION : Méthodes préalables

	//Méthode appelée pour afficher la fenêtre après la connexion
	public void respDisplay() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmResp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Méthode appelée pour créer la fenêtre après la connexion
	 */
	public RespWindow(Responsable r) {
		//Initialisation de la factory
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		membreDAO = (MembreDAO) adf.getMembreDAO();	
		voitureDAO = (VoitureDAO) adf.getVoitureDAO();
		baladeDAO = (BaladeDAO) adf.getBaladeDAO();
		categorieDAO = (CategorieDAO) adf.getCatDAO();
		
		//Récupération du responsable connecté
		connected = r;
		
		//Récuparation de l'id de la catégorie du responsable
		cat = categorieDAO.findForResp((int)connected.getId());
				
		initialize();
	}
	
	//FIN DE SECTION : Méthodes préalables

	/**
	 * Initialisation de la fenêtre
	 */
	private void initialize() {
		frmResp = new JFrame();
		frmResp.setTitle(connected.getNom().toUpperCase() + " " + connected.getPrenom()); //Affichage des NOM et Prénom de la personne connectée
		frmResp.getContentPane().setBackground(Color.DARK_GRAY);
		frmResp.setBounds(100, 100, 800, 600);
		frmResp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//SECTION : Création des Panels du menu
		
		JPanel menuRespPan = new JPanel();
		menuRespPan.setVisible(false);
		menuRespPan.setBackground(Color.DARK_GRAY);
		
		JPanel calBtnPan = new JPanel();
		FlowLayout fl_calBtnPan = (FlowLayout) calBtnPan.getLayout();
		fl_calBtnPan.setVgap(25);
		calBtnPan.setBackground(Color.DARK_GRAY);
		
		JPanel infosBtnRespPan = new JPanel();
		FlowLayout fl_infosBtnRespPan = (FlowLayout) infosBtnRespPan.getLayout();
		fl_infosBtnRespPan.setVgap(25);
		infosBtnRespPan.setBackground(Color.DARK_GRAY);
		
		JPanel catBtnRespPan = new JPanel();
		FlowLayout fl_catBtnRespPan = (FlowLayout) catBtnRespPan.getLayout();
		fl_catBtnRespPan.setVgap(25);
		catBtnRespPan.setBackground(Color.DARK_GRAY);
		
		JPanel quitBtnRespPan = new JPanel();
		FlowLayout fl_quitBtnRespPan = (FlowLayout) quitBtnRespPan.getLayout();
		fl_quitBtnRespPan.setVgap(25);
		quitBtnRespPan.setBackground(Color.DARK_GRAY);
		
		JPanel titleRespMenuPan = new JPanel();
		titleRespMenuPan.setBackground(Color.DARK_GRAY);
		
		GroupLayout gl_menuRespPan = new GroupLayout(menuRespPan);
		gl_menuRespPan.setHorizontalGroup(
			gl_menuRespPan.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_menuRespPan.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_menuRespPan.createParallelGroup(Alignment.TRAILING)
						.addComponent(titleRespMenuPan, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
						.addComponent(quitBtnRespPan, GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
						.addComponent(catBtnRespPan, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
						.addComponent(infosBtnRespPan, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
						.addComponent(calBtnPan, GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_menuRespPan.setVerticalGroup(
			gl_menuRespPan.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_menuRespPan.createSequentialGroup()
					.addContainerGap()
					.addComponent(titleRespMenuPan, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(calBtnPan, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(infosBtnRespPan, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(catBtnRespPan, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(quitBtnRespPan, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		//FIN DE SECTION : Création des Panels du menu
		
		//SECTION : Création des éléments du menu
		
		JLabel blbTitleResp = new JLabel("Que voulez-vous faire ?");
		blbTitleResp.setMaximumSize(new Dimension(400, 50));
		blbTitleResp.setFont(new Font("Century Gothic", Font.BOLD, 65));
		blbTitleResp.setHorizontalAlignment(SwingConstants.CENTER);
		blbTitleResp.setPreferredSize(new Dimension(750, 80));
		blbTitleResp.setForeground(Color.WHITE);
		blbTitleResp.setAlignmentX(Component.CENTER_ALIGNMENT);
		titleRespMenuPan.add(blbTitleResp);
		
		//Bouton servant à quitter l'application
		JButton quitBtnResp = new JButton("Quitter");
		quitBtnResp.setFocusable(false);
		
		quitBtnResp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		
		quitBtnResp.setPreferredSize(new Dimension(400, 50));
		quitBtnResp.setForeground(Color.WHITE);
		quitBtnResp.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		quitBtnResp.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		quitBtnResp.setBackground(Color.DARK_GRAY);
		quitBtnRespPan.add(quitBtnResp);
		
		//Bouton menant à la fenêtre de la catégorie
		JButton catBtnResp = new JButton("Cat\u00E9gorie");
		catBtnResp.setFocusable(false);
		
		catBtnResp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				populateCat(new CategorieTableModel());
				CardLayout cl = (CardLayout)frmResp.getContentPane().getLayout() ;
				cl.show(frmResp.getContentPane(), "name_catResp");
			}
		});
		
		catBtnResp.setPreferredSize(new Dimension(400, 50));
		catBtnResp.setForeground(Color.WHITE);
		catBtnResp.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		catBtnResp.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		catBtnResp.setBackground(Color.DARK_GRAY);
		catBtnRespPan.add(catBtnResp);
		
		//Bouton menant à la fenêtre des informations 
		JButton infosBtnResp = new JButton("Vos informations");
		infosBtnResp.setFocusable(false);
		
		infosBtnResp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)frmResp.getContentPane().getLayout() ;
				cl.show(frmResp.getContentPane(), "name_infosResp");
			}
		});
		
		infosBtnResp.setPreferredSize(new Dimension(400, 50));
		infosBtnResp.setForeground(Color.WHITE);
		infosBtnResp.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		infosBtnResp.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		infosBtnResp.setBackground(Color.DARK_GRAY);
		infosBtnRespPan.add(infosBtnResp);
		
		//Bouton menant à la fenêtre du calendrier
		JButton btnCal = new JButton("Calendrier");
		btnCal.setFocusable(false);
		
		btnCal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout)frmResp.getContentPane().getLayout() ;
				cl.show(frmResp.getContentPane(), "name_calResp");
			}
		});
		
		btnCal.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnCal.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		btnCal.setPreferredSize(new Dimension(400, 50));
		btnCal.setForeground(Color.WHITE);
		btnCal.setBackground(Color.DARK_GRAY);
		calBtnPan.add(btnCal);
		
		menuRespPan.setLayout(gl_menuRespPan);
		
		frmResp.getContentPane().setLayout(new CardLayout(0, 0));
		frmResp.getContentPane().add(menuRespPan, "name_menuResp");
		
		//FIN DE SECTION : Création des éléments du menu
		
		//SECTION : Création des Panels de la sélection de Balade
		
		JPanel calPan = new JPanel();
		calPan.setVisible(false);
		calPan.setBackground(Color.DARK_GRAY);
		frmResp.getContentPane().add(calPan, "name_calResp");
		
		JPanel titleCalPan = new JPanel();
		titleCalPan.setBackground(Color.DARK_GRAY);
		
		JPanel calInPan = new JPanel();
		calInPan.setBackground(Color.DARK_GRAY);
		
		GroupLayout gl_codrivingBalPan = new GroupLayout(calPan);
		gl_codrivingBalPan.setHorizontalGroup(
			gl_codrivingBalPan.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_codrivingBalPan.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_codrivingBalPan.createParallelGroup(Alignment.TRAILING)
						.addComponent(calInPan, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
						.addComponent(titleCalPan, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_codrivingBalPan.setVerticalGroup(
			gl_codrivingBalPan.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_codrivingBalPan.createSequentialGroup()
					.addContainerGap()
					.addComponent(titleCalPan, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(calInPan, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		//FIN DE SECTION : Création des Panels de la sélection de Balade
		
		//SECTION : Création des éléments de la sélection de Balade
		
		//Table qui va contenir les balades de la catégorie du responsable connecté
		calTable = new JTable();
		calTable.setFocusable(false);
		calTable.setGridColor(new Color(0, 0, 0));
		calTable.setShowVerticalLines(false);
		calTable.setPreferredScrollableViewportSize(new Dimension(740, 320));
		calTable.setRowHeight(60);
		calTable.setFont(new Font("Arial Black", Font.PLAIN, 25));
		calTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		calTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		calTable.setPreferredSize(new Dimension(740, 315));
		calTable.setSelectionBackground(new Color(128, 128, 128));
		calTable.setForeground(Color.WHITE);
		calTable.setBackground(Color.DARK_GRAY);
		calTable.getTableHeader().setFont(new Font("Arial Black", Font.PLAIN, 20));
		
		//Appel de la fonction de remplissage de la table et d'une liste contenant ces mêmes balades
		List<Balade> listBal = populateCal(new CalendrierTableModel());
		
		JPanel respBalPan = new JPanel();
		JLabel respBalTitle = new JLabel("");
		
		JScrollPane calSP = new JScrollPane(calTable);
		calSP.setBorder(new LineBorder(new Color(0, 0, 0)));
		calSP.setForeground(new Color(0, 0, 0));
		calSP.setPreferredSize(new Dimension(740, 350));
		calSP.setBackground(Color.DARK_GRAY);
		calInPan.add(calSP);
		
		//Bouton permettant de choisir une balade
		JButton selectCalBtn = new JButton("Modifier");
		
		selectCalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout)frmResp.getContentPane().getLayout() ;
				cl.show(frmResp.getContentPane(), "name_respBalPan");
				selectedBal = listBal.get(calTable.getSelectedRow());
				respBalTitle.setText(selectedBal.getLibelle());
				displayBal();
			}
		});
		
		//Bouton d'affichage de la page d'ajout d'une Balade
		JButton addBalBtn = new JButton("Ajouter");
		addBalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)frmResp.getContentPane().getLayout() ;
				cl.show(frmResp.getContentPane(), "name_addBalPan");
			}
		});
		addBalBtn.setPreferredSize(new Dimension(200, 50));
		addBalBtn.setForeground(Color.WHITE);
		addBalBtn.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		addBalBtn.setFocusable(false);
		addBalBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		addBalBtn.setBackground(Color.DARK_GRAY);
		calInPan.add(addBalBtn);
		
		selectCalBtn.setFocusable(false);
		selectCalBtn.setForeground(Color.WHITE);
		selectCalBtn.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		selectCalBtn.setPreferredSize(new Dimension(200, 50));
		selectCalBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		selectCalBtn.setBackground(Color.DARK_GRAY);
		calInPan.add(selectCalBtn);
		
		//Bouton premettant de revenir en arrière
		JButton calBackBtn = new JButton("Retour");
		calBackBtn.setFocusable(false);
		calBackBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout)frmResp.getContentPane().getLayout() ;
				cl.show(frmResp.getContentPane(), "name_menuResp");
			}
		});
		
		calBackBtn.setPreferredSize(new Dimension(200, 50));
		calBackBtn.setForeground(Color.WHITE);
		calBackBtn.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		calBackBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		calBackBtn.setBackground(Color.DARK_GRAY);
		calInPan.add(calBackBtn);
		
		JLabel titleCal = new JLabel("Calendrier");
		titleCal.setFont(new Font("Century Gothic", Font.BOLD, 65));
		titleCal.setHorizontalAlignment(SwingConstants.CENTER);
		titleCal.setPreferredSize(new Dimension(750, 75));
		titleCal.setForeground(Color.WHITE);
		titleCalPan.add(titleCal);
		calPan.setLayout(gl_codrivingBalPan);
		
		//FIN DE SECTION : Création des éléments de la sélection de Balade
		
		//SECTION : Création des Panels de la fenêtre d'informations
		
		JPanel infosRespPan = new JPanel();
		infosRespPan.setBackground(Color.DARK_GRAY);
		frmResp.getContentPane().add(infosRespPan, "name_infosResp");
		
		JPanel infoTitleRespPan = new JPanel();
		infoTitleRespPan.setBackground(Color.DARK_GRAY);
		
		JLabel infoTitleResp = new JLabel("Vos informations");
		infoTitleResp.setPreferredSize(new Dimension(750, 75));
		infoTitleResp.setHorizontalAlignment(SwingConstants.CENTER);
		infoTitleResp.setForeground(Color.WHITE);
		infoTitleResp.setFont(new Font("Century Gothic", Font.BOLD, 65));
		infoTitleRespPan.add(infoTitleResp);
		
		JPanel infosRespPanIn = new JPanel();
		infosRespPanIn.setBackground(Color.DARK_GRAY);
		
		JPanel infosResp = new JPanel();
		infosResp.setBackground(Color.DARK_GRAY);
		infosResp.setPreferredSize(new Dimension(740, 350));
		infosRespPanIn.add(infosResp);
		infosResp.setLayout(new GridLayout(8, 2, 0, 0));
		
		//FIN DE SECTION : Création des Panels de la fenêtre d'informations
		
		//SECTION : Création des éléments de la fenêtre d'informations
		
		JLabel nomLbl = new JLabel("Nom :");
		nomLbl.setForeground(Color.WHITE);
		nomLbl.setFont(new Font("Arial Black", Font.PLAIN, 20));
		infosResp.add(nomLbl);
		
		JLabel nomTxt = new JLabel(connected.getNom());
		nomTxt.setForeground(Color.WHITE);
		nomTxt.setFont(new Font("Century Gothic", Font.BOLD, 20));
		infosResp.add(nomTxt);
		
		JLabel prenomLbl = new JLabel("Pr\u00E9nom :");
		prenomLbl.setForeground(Color.WHITE);
		prenomLbl.setFont(new Font("Arial Black", Font.PLAIN, 20));
		infosResp.add(prenomLbl);
		
		JLabel prenomTxt = new JLabel(connected.getPrenom());
		prenomTxt.setForeground(Color.WHITE);
		prenomTxt.setFont(new Font("Century Gothic", Font.BOLD, 20));
		infosResp.add(prenomTxt);
		
		JLabel dateNaissLbl = new JLabel("Date de naissance :");
		dateNaissLbl.setForeground(Color.WHITE);
		dateNaissLbl.setFont(new Font("Arial Black", Font.PLAIN, 20));
		infosResp.add(dateNaissLbl);
		
		JLabel dateNaissTxt = new JLabel(connected.getDateNaiss().toString());
		dateNaissTxt.setForeground(Color.WHITE);
		dateNaissTxt.setFont(new Font("Century Gothic", Font.BOLD, 20));
		infosResp.add(dateNaissTxt);
		
		JLabel rueLbl = new JLabel("Rue :");
		rueLbl.setForeground(Color.WHITE);
		rueLbl.setFont(new Font("Arial Black", Font.PLAIN, 20));
		infosResp.add(rueLbl);
		
		JLabel rueTxt = new JLabel(connected.getAdr().getRue() + " " + connected.getAdr().getNum());
		rueTxt.setFont(new Font("Century Gothic", Font.BOLD, 20));
		rueTxt.setForeground(Color.WHITE);
		infosResp.add(rueTxt);
		
		JLabel codePostLbl = new JLabel("Code postal :");
		codePostLbl.setForeground(Color.WHITE);
		codePostLbl.setFont(new Font("Arial Black", Font.PLAIN, 20));
		infosResp.add(codePostLbl);
		
		JLabel codePostTxt = new JLabel(connected.getAdr().getCodePost());
		codePostTxt.setForeground(Color.WHITE);
		codePostTxt.setFont(new Font("Century Gothic", Font.BOLD, 20));
		infosResp.add(codePostTxt);
		
		JLabel villeLbl = new JLabel("Ville :");
		villeLbl.setForeground(Color.WHITE);
		villeLbl.setFont(new Font("Arial Black", Font.PLAIN, 20));
		infosResp.add(villeLbl);
		
		JLabel villeTxt = new JLabel(connected.getAdr().getVille());
		villeTxt.setForeground(Color.WHITE);
		villeTxt.setFont(new Font("Century Gothic", Font.BOLD, 20));
		infosResp.add(villeTxt);
		
		JLabel paysLbl = new JLabel("Pays :");
		paysLbl.setForeground(Color.WHITE);
		paysLbl.setFont(new Font("Arial Black", Font.PLAIN, 20));
		infosResp.add(paysLbl);
		
		JLabel paysTxt = new JLabel(connected.getAdr().getPays());
		paysTxt.setForeground(Color.WHITE);
		paysTxt.setFont(new Font("Century Gothic", Font.BOLD, 20));
		infosResp.add(paysTxt);
		
		JLabel otherLbl = new JLabel("Date de fin :");
		JLabel otherTxt = new JLabel(((Responsable)connected).getDateExp().toString());
		
		/*JLabel otherLbl = new JLabel("");
		JLabel otherTxt = new JLabel("");*/
		otherLbl.setForeground(Color.WHITE);
		otherLbl.setFont(new Font("Arial Black", Font.PLAIN, 20));
		infosResp.add(otherLbl);
		
		otherTxt.setForeground(Color.WHITE);
		otherTxt.setFont(new Font("Century Gothic", Font.BOLD, 20));
		infosResp.add(otherTxt);
		
		//Bouton de retour en arrière
		JButton infosRespBackBtn = new JButton("Retour");
		infosRespBackBtn.setFocusable(false);
		
		infosRespBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)frmResp.getContentPane().getLayout() ;
				cl.show(frmResp.getContentPane(), "name_menuResp");
			}
		});
		
		infosRespBackBtn.setPreferredSize(new Dimension(300, 50));
		infosRespBackBtn.setForeground(Color.WHITE);
		infosRespBackBtn.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		infosRespBackBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		infosRespBackBtn.setBackground(Color.DARK_GRAY);
		infosRespPanIn.add(infosRespBackBtn);
		
		GroupLayout gl_infosRespPan = new GroupLayout(infosRespPan);
		gl_infosRespPan.setHorizontalGroup(
			gl_infosRespPan.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_infosRespPan.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_infosRespPan.createParallelGroup(Alignment.LEADING)
						.addComponent(infoTitleRespPan, GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
						.addComponent(infosRespPanIn, GroupLayout.PREFERRED_SIZE, 748, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_infosRespPan.setVerticalGroup(
			gl_infosRespPan.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_infosRespPan.createSequentialGroup()
					.addContainerGap()
					.addComponent(infoTitleRespPan, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(infosRespPanIn, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		infosRespPan.setLayout(gl_infosRespPan);
		
		//FIN DE SECTION : Création des éléments de la fenêtre d'informations
		
		//SECTION : Création des Panels de la fenêtre de catégorie
		
		JPanel catPan = new JPanel();
		catPan.setBackground(Color.DARK_GRAY);
		frmResp.getContentPane().add(catPan, "name_catResp");
		
		JPanel titleCatPan = new JPanel();
		FlowLayout flowLayout = (FlowLayout) titleCatPan.getLayout();
		flowLayout.setVgap(1);
		flowLayout.setHgap(1);
		titleCatPan.setBackground(Color.DARK_GRAY);
		
		JLabel titleCat = new JLabel("Cat\u00E9gorie");
		titleCat.setVerticalAlignment(SwingConstants.TOP);
		titleCat.setPreferredSize(new Dimension(750, 82));
		titleCat.setHorizontalAlignment(SwingConstants.CENTER);
		titleCat.setForeground(Color.WHITE);
		titleCat.setFont(new Font("Century Gothic", Font.BOLD, 65));
		titleCatPan.add(titleCat);
		
		//FIN DE SECTION : Création des Panels de la fenêtre de catégorie
		
		//SECTION : Création des éléments de la fenêtre de catégorie
		
		//Bouton de retour en arrière
		JButton backCatBtn = new JButton("Retour");
		
		backCatBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout)frmResp.getContentPane().getLayout() ;
				cl.show(frmResp.getContentPane(), "name_menuResp");
			}
		});
		
		backCatBtn.setPreferredSize(new Dimension(300, 50));
		backCatBtn.setForeground(Color.WHITE);
		backCatBtn.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		backCatBtn.setFocusable(false);
		backCatBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		backCatBtn.setBackground(Color.DARK_GRAY);
		
		JPanel catPanIn = new JPanel();
		catPanIn.setBackground(Color.DARK_GRAY);
		catPanIn.setPreferredSize(new Dimension(740, 350));
		
		GroupLayout gl_catPan = new GroupLayout(catPan);
		gl_catPan.setHorizontalGroup(
			gl_catPan.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_catPan.createSequentialGroup()
					.addContainerGap()
					.addComponent(titleCatPan, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(catPanIn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(372))
				.addGroup(Alignment.TRAILING, gl_catPan.createSequentialGroup()
					.addGap(250)
					.addComponent(backCatBtn, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
					.addGap(228))
		);
		gl_catPan.setVerticalGroup(
			gl_catPan.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_catPan.createSequentialGroup()
					.addGroup(gl_catPan.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_catPan.createSequentialGroup()
							.addContainerGap()
							.addComponent(titleCatPan, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addGap(364))
						.addGroup(gl_catPan.createSequentialGroup()
							.addContainerGap(149, Short.MAX_VALUE)
							.addComponent(catPanIn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(backCatBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(41))
		);
		
		JScrollPane catSP = new JScrollPane((Component) null);
		catSP.setPreferredSize(new Dimension(740, 350));
		catSP.setForeground(Color.BLACK);
		catSP.setBorder(new LineBorder(new Color(0, 0, 0)));
		catSP.setBackground(Color.DARK_GRAY);
		
		GroupLayout gl_catPanIn = new GroupLayout(catPanIn);
		gl_catPanIn.setHorizontalGroup(
			gl_catPanIn.createParallelGroup(Alignment.LEADING)
				.addComponent(catSP, GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
		);
		gl_catPanIn.setVerticalGroup(
			gl_catPanIn.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_catPanIn.createSequentialGroup()
					.addComponent(catSP, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		//Table contenaant les membres de la catégorie du responsable
		catTable = new JTable();
		catTable.setRowSelectionAllowed(false);
		catTable.setShowVerticalLines(false);
		catTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		catTable.setSelectionBackground(Color.GRAY);
		catTable.setRowHeight(60);
		catTable.setPreferredSize(new Dimension(740, 315));
		catTable.setPreferredScrollableViewportSize(new Dimension(740, 320));
		catTable.setGridColor(Color.BLACK);
		catTable.setForeground(Color.WHITE);
		catTable.setFont(new Font("Arial Black", Font.PLAIN, 25));
		catTable.setFocusable(false);
		catTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		catTable.setBackground(Color.DARK_GRAY);
		catTable.setModel(new CategorieTableModel());
		catSP.setViewportView(catTable);
		catPanIn.setLayout(gl_catPanIn);
		
		respBalPan.setBackground(Color.DARK_GRAY);
		frmResp.getContentPane().add(respBalPan, "name_respBalPan");
		
		JPanel respBalTitlePan = new JPanel();
		respBalTitlePan.setBackground(Color.DARK_GRAY);
		
		respBalTitle.setPreferredSize(new Dimension(750, 75));
		respBalTitle.setHorizontalAlignment(SwingConstants.CENTER);
		respBalTitle.setForeground(Color.WHITE);
		respBalTitle.setFont(new Font("Century Gothic", Font.BOLD, 65));
		respBalTitlePan.add(respBalTitle);
		
		JPanel respBalPanIn = new JPanel();
		respBalPanIn.setBackground(Color.DARK_GRAY);
		
		JPanel respBalGrid = new JPanel();
		respBalGrid.setPreferredSize(new Dimension(740, 350));
		respBalGrid.setBackground(Color.DARK_GRAY);
		respBalPanIn.add(respBalGrid);
		respBalGrid.setLayout(new GridLayout(8, 2, 0, 0));
		
		JLabel lblLibell = new JLabel("Libell\u00E9 : ");
		lblLibell.setForeground(Color.WHITE);
		lblLibell.setFont(new Font("Arial Black", Font.PLAIN, 20));
		respBalGrid.add(lblLibell);
		
		txtLibell = new JTextField();
		txtLibell.setForeground(Color.WHITE);
		txtLibell.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtLibell.setColumns(10);
		txtLibell.setBorder(new LineBorder(Color.BLACK));
		txtLibell.setBackground(Color.GRAY);
		respBalGrid.add(txtLibell);
		
		JLabel lblForfait = new JLabel("Forfait :");
		lblForfait.setForeground(Color.WHITE);
		lblForfait.setFont(new Font("Arial Black", Font.PLAIN, 20));
		respBalGrid.add(lblForfait);
		
		txtForfait = new JTextField();
		txtForfait.setForeground(Color.WHITE);
		txtForfait.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtForfait.setBorder(new LineBorder(Color.BLACK));
		txtForfait.setBackground(Color.GRAY);
		respBalGrid.add(txtForfait);
		txtForfait.setColumns(10);
		
		JLabel lblDate = new JLabel("Date :");
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Arial Black", Font.PLAIN, 20));
		respBalGrid.add(lblDate);
		
		txtDate = new JTextField();
		txtDate.setForeground(Color.WHITE);
		txtDate.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtDate.setEnabled(false);
		txtDate.setEditable(false);
		txtDate.setColumns(10);
		txtDate.setBorder(new LineBorder(Color.BLACK));
		txtDate.setBackground(Color.GRAY);
		respBalGrid.add(txtDate);
		
		JLabel label_7 = new JLabel("Rue :");
		label_7.setForeground(Color.WHITE);
		label_7.setFont(new Font("Arial Black", Font.PLAIN, 20));
		respBalGrid.add(label_7);
		
		txtRue = new JTextField();
		txtRue.setForeground(Color.WHITE);
		txtRue.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtRue.setColumns(10);
		txtRue.setBorder(new LineBorder(Color.BLACK));
		txtRue.setBackground(Color.GRAY);
		respBalGrid.add(txtRue);
		
		JLabel lblNum = new JLabel("Num\u00E9ro :");
		lblNum.setForeground(Color.WHITE);
		lblNum.setFont(new Font("Arial Black", Font.PLAIN, 20));
		respBalGrid.add(lblNum);
		
		txtNum = new JTextField();
		txtNum.setForeground(Color.WHITE);
		txtNum.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtNum.setColumns(10);
		txtNum.setBorder(new LineBorder(Color.BLACK));
		txtNum.setBackground(Color.GRAY);
		respBalGrid.add(txtNum);
		
		JLabel label_9 = new JLabel("Code postal :");
		label_9.setForeground(Color.WHITE);
		label_9.setFont(new Font("Arial Black", Font.PLAIN, 20));
		respBalGrid.add(label_9);
		
		txtCP = new JTextField();
		txtCP.setForeground(Color.WHITE);
		txtCP.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtCP.setColumns(10);
		txtCP.setBorder(new LineBorder(Color.BLACK));
		txtCP.setBackground(Color.GRAY);
		respBalGrid.add(txtCP);
		
		JLabel label_11 = new JLabel("Ville :");
		label_11.setForeground(Color.WHITE);
		label_11.setFont(new Font("Arial Black", Font.PLAIN, 20));
		respBalGrid.add(label_11);
		
		txtVille = new JTextField();
		txtVille.setForeground(Color.WHITE);
		txtVille.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtVille.setColumns(10);
		txtVille.setBorder(new LineBorder(Color.BLACK));
		txtVille.setBackground(Color.GRAY);
		respBalGrid.add(txtVille);
		
		JLabel label_13 = new JLabel("Pays :");
		label_13.setForeground(Color.WHITE);
		label_13.setFont(new Font("Arial Black", Font.PLAIN, 20));
		respBalGrid.add(label_13);
		
		txtPays = new JTextField();
		txtPays.setForeground(Color.WHITE);
		txtPays.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtPays.setColumns(10);
		txtPays.setBorder(new LineBorder(Color.BLACK));
		txtPays.setBackground(Color.GRAY);
		respBalGrid.add(txtPays);
		
		//Bouton de retour en arrière
		JButton respBalBackBtn = new JButton("Retour");
		respBalBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout)frmResp.getContentPane().getLayout() ;
				cl.show(frmResp.getContentPane(), "name_calResp");
			}
		});
		
		//Bouton de validation de la modification d'une balade (calcul forfait)
		JButton btnForfait = new JButton("Valider");
		btnForfait.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean updated = baladeDAO.update(new Balade(selectedBal.getId(),
										    txtLibell.getText(),
										    new Adresse(selectedBal.getAdr().getId(),
										    			txtRue.getText(),
										    			Integer.parseInt(txtNum.getText()),
										    			txtVille.getText(),
										    			txtCP.getText(),
										    			txtPays.getText()),
										    selectedBal.getDate(),
										    Float.parseFloat(txtForfait.getText()),
										    selectedBal.getCat()));
				if(updated) {
					populateCal(new CalendrierTableModel());
					CardLayout cl = (CardLayout)frmResp.getContentPane().getLayout() ;
					cl.show(frmResp.getContentPane(), "name_calResp");
				}
				else {
					JOptionPane.showMessageDialog(frmResp, "La mise à jour ne s'est pas effectuée");
				}
			}
		});
		btnForfait.setPreferredSize(new Dimension(200, 50));
		btnForfait.setForeground(Color.WHITE);
		btnForfait.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		btnForfait.setFocusable(false);
		btnForfait.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnForfait.setBackground(Color.DARK_GRAY);
		respBalPanIn.add(btnForfait);
		
		//Bouton de suppression d'une balade
		JButton respBalDelBtn = new JButton("Supprimer");
		respBalDelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean deleted = baladeDAO.delete(new Balade(selectedBal.getId(),
										    txtLibell.getText(),
										    new Adresse(selectedBal.getAdr().getId(),
										    			txtRue.getText(),
										    			Integer.parseInt(txtNum.getText()),
										    			txtVille.getText(),
										    			txtCP.getText(),
										    			txtPays.getText()),
										    selectedBal.getDate(),
										    Float.parseFloat(txtForfait.getText()),
										    selectedBal.getCat()));
				
				if(deleted) {
					populateCal(new CalendrierTableModel());
					CardLayout cl = (CardLayout)frmResp.getContentPane().getLayout() ;
					cl.show(frmResp.getContentPane(), "name_calResp");
				}
				else {
					JOptionPane.showMessageDialog(frmResp, "La suppression n'est pas possible");
				}
			}
		});
		
		respBalDelBtn.setPreferredSize(new Dimension(200, 50));
		respBalDelBtn.setForeground(Color.WHITE);
		respBalDelBtn.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		respBalDelBtn.setFocusable(false);
		respBalDelBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		respBalDelBtn.setBackground(Color.DARK_GRAY);
		respBalPanIn.add(respBalDelBtn);
		respBalBackBtn.setPreferredSize(new Dimension(200, 50));
		respBalBackBtn.setForeground(Color.WHITE);
		respBalBackBtn.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		respBalBackBtn.setFocusable(false);
		respBalBackBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		respBalBackBtn.setBackground(Color.DARK_GRAY);
		respBalPanIn.add(respBalBackBtn);
		
		GroupLayout gl_respBalPan = new GroupLayout(respBalPan);
		gl_respBalPan.setHorizontalGroup(
			gl_respBalPan.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 778, Short.MAX_VALUE)
				.addGroup(gl_respBalPan.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_respBalPan.createParallelGroup(Alignment.LEADING)
						.addComponent(respBalTitlePan, GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
						.addComponent(respBalPanIn, GroupLayout.PREFERRED_SIZE, 748, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_respBalPan.setVerticalGroup(
			gl_respBalPan.createParallelGroup(Alignment.LEADING)
				.addGap(0, 544, Short.MAX_VALUE)
				.addGroup(gl_respBalPan.createSequentialGroup()
					.addContainerGap()
					.addComponent(respBalTitlePan, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(respBalPanIn, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		respBalPan.setLayout(gl_respBalPan);
		
		JPanel addBalPan = new JPanel();
		addBalPan.setBackground(Color.DARK_GRAY);
		frmResp.getContentPane().add(addBalPan, "name_addBalPan");
		
		JPanel addBalTitlePan = new JPanel();
		addBalTitlePan.setBackground(Color.DARK_GRAY);
		
		JLabel addBalTitle = new JLabel("Nouvelle balade");
		addBalTitle.setPreferredSize(new Dimension(750, 75));
		addBalTitle.setHorizontalAlignment(SwingConstants.CENTER);
		addBalTitle.setForeground(Color.WHITE);
		addBalTitle.setFont(new Font("Century Gothic", Font.BOLD, 65));
		addBalTitlePan.add(addBalTitle);
		
		JPanel addBalPanIn = new JPanel();
		addBalPanIn.setBackground(Color.DARK_GRAY);
		
		JPanel addBal = new JPanel();
		addBal.setPreferredSize(new Dimension(740, 350));
		addBal.setBackground(Color.DARK_GRAY);
		addBalPanIn.add(addBal);
		addBal.setLayout(new GridLayout(8, 2, 0, 0));
		
		JLabel lblLibellAdd = new JLabel("Libell\u00E9 : ");
		lblLibellAdd.setForeground(Color.WHITE);
		lblLibellAdd.setFont(new Font("Arial Black", Font.PLAIN, 20));
		addBal.add(lblLibellAdd);
		
		txtLibellAdd = new JTextField();
		txtLibellAdd.setForeground(Color.WHITE);
		txtLibellAdd.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtLibellAdd.setColumns(10);
		txtLibellAdd.setBorder(new LineBorder(Color.BLACK));
		txtLibellAdd.setBackground(Color.GRAY);
		addBal.add(txtLibellAdd);
		
		JLabel lblForfaitAdd = new JLabel("Forfait :");
		lblForfaitAdd.setForeground(Color.WHITE);
		lblForfaitAdd.setFont(new Font("Arial Black", Font.PLAIN, 20));
		addBal.add(lblForfaitAdd);
		
		txtForfaitAdd = new JTextField();
		txtForfaitAdd.setForeground(Color.WHITE);
		txtForfaitAdd.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtForfaitAdd.setColumns(10);
		txtForfaitAdd.setBorder(new LineBorder(Color.BLACK));
		txtForfaitAdd.setBackground(Color.GRAY);
		addBal.add(txtForfaitAdd);
		
		JLabel lblDateAdd = new JLabel("Date :");
		lblDateAdd.setForeground(Color.WHITE);
		lblDateAdd.setFont(new Font("Arial Black", Font.PLAIN, 20));
		addBal.add(lblDateAdd);
		
		JPanel txtDateAddPan = new JPanel();
		txtDateAddPan.setBackground(Color.DARK_GRAY);
		addBal.add(txtDateAddPan);
		txtDateAddPan.setLayout(new GridLayout(1, 3, 0, 0));
		
		txtDateAddJ = new JTextField();
		txtDateAddJ.setText("JJ");
		txtDateAddJ.setForeground(Color.WHITE);
		txtDateAddJ.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtDateAddJ.setColumns(10);
		txtDateAddJ.setBorder(new LineBorder(Color.BLACK));
		txtDateAddJ.setBackground(Color.GRAY);
		txtDateAddPan.add(txtDateAddJ);
		
		txtDateAddM = new JTextField();
		txtDateAddM.setText("MM");
		txtDateAddM.setForeground(Color.WHITE);
		txtDateAddM.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtDateAddM.setColumns(10);
		txtDateAddM.setBorder(new LineBorder(Color.BLACK));
		txtDateAddM.setBackground(Color.GRAY);
		txtDateAddPan.add(txtDateAddM);
		
		txtDateAddA = new JTextField();
		txtDateAddA.setText("AAAA");
		txtDateAddA.setForeground(Color.WHITE);
		txtDateAddA.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtDateAddA.setColumns(10);
		txtDateAddA.setBorder(new LineBorder(Color.BLACK));
		txtDateAddA.setBackground(Color.GRAY);
		txtDateAddPan.add(txtDateAddA);
		
		JLabel lblRueAdd = new JLabel("Rue :");
		lblRueAdd.setForeground(Color.WHITE);
		lblRueAdd.setFont(new Font("Arial Black", Font.PLAIN, 20));
		addBal.add(lblRueAdd);
		
		txtRueAdd = new JTextField();
		txtRueAdd.setForeground(Color.WHITE);
		txtRueAdd.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtRueAdd.setColumns(10);
		txtRueAdd.setBorder(new LineBorder(Color.BLACK));
		txtRueAdd.setBackground(Color.GRAY);
		addBal.add(txtRueAdd);
		
		JLabel lblNumAdd = new JLabel("Num\u00E9ro :");
		lblNumAdd.setForeground(Color.WHITE);
		lblNumAdd.setFont(new Font("Arial Black", Font.PLAIN, 20));
		addBal.add(lblNumAdd);
		
		txtNumAdd = new JTextField();
		txtNumAdd.setForeground(Color.WHITE);
		txtNumAdd.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtNumAdd.setColumns(10);
		txtNumAdd.setBorder(new LineBorder(Color.BLACK));
		txtNumAdd.setBackground(Color.GRAY);
		addBal.add(txtNumAdd);
		
		JLabel lblCPAdd = new JLabel("Code postal :");
		lblCPAdd.setForeground(Color.WHITE);
		lblCPAdd.setFont(new Font("Arial Black", Font.PLAIN, 20));
		addBal.add(lblCPAdd);
		
		txtCPAdd = new JTextField();
		txtCPAdd.setForeground(Color.WHITE);
		txtCPAdd.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtCPAdd.setColumns(10);
		txtCPAdd.setBorder(new LineBorder(Color.BLACK));
		txtCPAdd.setBackground(Color.GRAY);
		addBal.add(txtCPAdd);
		
		JLabel lblVilleAdd = new JLabel("Ville :");
		lblVilleAdd.setForeground(Color.WHITE);
		lblVilleAdd.setFont(new Font("Arial Black", Font.PLAIN, 20));
		addBal.add(lblVilleAdd);
		
		txtVilleAdd = new JTextField();
		txtVilleAdd.setForeground(Color.WHITE);
		txtVilleAdd.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtVilleAdd.setColumns(10);
		txtVilleAdd.setBorder(new LineBorder(Color.BLACK));
		txtVilleAdd.setBackground(Color.GRAY);
		addBal.add(txtVilleAdd);
		
		JLabel lblPaysAdd = new JLabel("Pays :");
		lblPaysAdd.setForeground(Color.WHITE);
		lblPaysAdd.setFont(new Font("Arial Black", Font.PLAIN, 20));
		addBal.add(lblPaysAdd);
		
		txtPaysAdd = new JTextField();
		txtPaysAdd.setForeground(Color.WHITE);
		txtPaysAdd.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtPaysAdd.setColumns(10);
		txtPaysAdd.setBorder(new LineBorder(Color.BLACK));
		txtPaysAdd.setBackground(Color.GRAY);
		addBal.add(txtPaysAdd);
		
		//Bouton de création d'une balade
		JButton createBalBtn = new JButton("Valider");
		createBalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean created = false;
				try {
					@SuppressWarnings("deprecation")
					long dateTmp = java.sql.Date.UTC(Integer.parseInt(txtDateAddA.getText()) - 1900,Integer.parseInt(txtDateAddM.getText()), Integer.parseInt(txtDateAddJ.getText()), 0, 0, 0);
					
					created = baladeDAO.create(new Balade(0,
											   txtLibellAdd.getText(),
											   new Adresse(0,
														  txtRueAdd.getText(),
														  Integer.parseInt(txtNumAdd.getText()),
														  				   txtVilleAdd.getText(),
																    	   txtCPAdd.getText(),
																    	   txtPaysAdd.getText()),
											   			  new java.sql.Date(dateTmp),
														  Float.parseFloat(txtForfaitAdd.getText()),
														  categorieDAO.find(cat.getId())));
					
					if(created) {
						populateCal(new CalendrierTableModel());
						CardLayout cl = (CardLayout)frmResp.getContentPane().getLayout() ;
						cl.show(frmResp.getContentPane(), "name_calResp");
					}
					else {
						JOptionPane.showMessageDialog(frmResp, "La création est impossible");
					}
					
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} 
			}
		});
		
		createBalBtn.setPreferredSize(new Dimension(300, 50));
		createBalBtn.setForeground(Color.WHITE);
		createBalBtn.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		createBalBtn.setFocusable(false);
		createBalBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		createBalBtn.setBackground(Color.DARK_GRAY);
		addBalPanIn.add(createBalBtn);
		
		//Bouton de retour en arrière
		JButton addBalBackBtn = new JButton("Retour");
		addBalBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)frmResp.getContentPane().getLayout() ;
				cl.show(frmResp.getContentPane(), "name_calResp");
			}
		});
		addBalBackBtn.setPreferredSize(new Dimension(300, 50));
		addBalBackBtn.setForeground(Color.WHITE);
		addBalBackBtn.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		addBalBackBtn.setFocusable(false);
		addBalBackBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		addBalBackBtn.setBackground(Color.DARK_GRAY);
		addBalPanIn.add(addBalBackBtn);
		GroupLayout gl_addBalPan = new GroupLayout(addBalPan);
		gl_addBalPan.setHorizontalGroup(
			gl_addBalPan.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 778, Short.MAX_VALUE)
				.addGroup(gl_addBalPan.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_addBalPan.createParallelGroup(Alignment.LEADING)
						.addComponent(addBalTitlePan, GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
						.addComponent(addBalPanIn, GroupLayout.PREFERRED_SIZE, 748, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_addBalPan.setVerticalGroup(
			gl_addBalPan.createParallelGroup(Alignment.LEADING)
				.addGap(0, 544, Short.MAX_VALUE)
				.addGroup(gl_addBalPan.createSequentialGroup()
					.addContainerGap()
					.addComponent(addBalTitlePan, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(addBalPanIn, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		addBalPan.setLayout(gl_addBalPan);
		
		//FIN DE SECTION : Création des éléments de la fenêtre de catégorie
	}
	
	//FIN DE SECTION : Corps
	
	//SECTION : Méthodes supplémentaires
	
	//Remplissage de la table dezs balade. Retourne une liste de balade
	public List<Balade> populateCal(DefaultTableModel model) {
		List<Balade> listBal = new ArrayList<>();
		
		//Nettoyage du tableau
		calTable.removeAll();
		
		//Récupération des balades correspondantes
		for(Balade b : baladeDAO.findAll(cat.getId())) {
			model.addRow(new Object[] {b.getLibelle(), b.getDate().toString(), b.getFraisDepla()});
			listBal.add(b);
			
			b.setListVoit(voitureDAO.getVoitList(b.getId()));
		}
		calTable.setModel(model);
		
		return listBal;
	}
	
	//Remplissage de la table de la catégorie
	public void populateCat(DefaultTableModel model) {
		//Netoyage du tableau
		catTable.removeAll();
		
		//Récupération des membres
		for(Membre m : membreDAO.getMembreList(cat.getId())) {
			model.addRow(new Object[] {m.getNom().toUpperCase(),
									   m.getPrenom(),
									   m.getDateNaiss().toString()});
		}
		catTable.setModel(model);
	}
	
	//Affichage des informations de la balade sélectionée
	public void displayBal() {
		txtLibell.setText(selectedBal.getLibelle());
		txtForfait.setText(Float.toString(selectedBal.getFraisDepla()));
		txtDate.setText(selectedBal.getDate().toString());
		txtRue.setText(selectedBal.getAdr().getRue());
		txtNum.setText(Integer.toString(selectedBal.getAdr().getNum()));
		txtCP.setText(selectedBal.getAdr().getCodePost());
		txtVille.setText(selectedBal.getAdr().getVille());
		txtPays.setText(selectedBal.getAdr().getPays());
	}
	
	//FIN DE SECTION : Méthodes supplémentaires
}