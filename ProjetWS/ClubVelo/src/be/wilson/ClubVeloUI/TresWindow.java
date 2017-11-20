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

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import be.wilson.ClubVeloDAO.CategorieDAO;
import be.wilson.ClubVeloDAO.MembreDAO;
import be.wilson.ClubVeloFactory.AbstractDAOFactory;
import be.wilson.ClubVeloPOJO.Adresse;
import be.wilson.ClubVeloPOJO.Categorie;
import be.wilson.ClubVeloPOJO.Cyclo;
import be.wilson.ClubVeloPOJO.Membre;
import be.wilson.ClubVeloPOJO.Tresorier;
import be.wilson.ClubVeloPOJO.VTT;

public class TresWindow {
	//SECTION : Création des variables globales
	
	//Affichage
	private JFrame frmTres;
	private JTable cotTable;
	
	//Fonctionnalités
	private Tresorier connected;
	private AbstractDAOFactory adf;
	private MembreDAO membreDAO;
	private CategorieDAO categorieDAO;
	private List<Membre> membres = new ArrayList<>();
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtJour;
	private JTextField txtMois;
	private JTextField txtAnnee;
	private JTextField txtRue;
	private JTextField txtNum;
	private JTextField txtCP;
	private JTextField txtVille;
	private JTextField txtPays;
	private JTextField txtMdp;
	
	//FIN DE SECTION : Création des variables globales
	
	//SECTION : Méthodes préalables

	//Méthode appelée pour afficher la fenêtre après la connexion
	public void tresDisplay() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmTres.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Méthode appelée pour créer la fenêtre après la connexion
	 */
	public TresWindow(Tresorier t) {
		//Initialisation de la factory
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		membreDAO = (MembreDAO) adf.getMembreDAO();
		categorieDAO = (CategorieDAO) adf.getCatDAO();
				
		//Récupération du responsable connecté
		connected = t;
						
		initialize();
	}
	
	//FIN DE SECTION : Méthodes préalables

	/**
	 * Initialisation de la fenêtre
	 */
	private void initialize() {
		frmTres = new JFrame();
		frmTres.setTitle(connected.getNom().toUpperCase() + " " + connected.getPrenom()); //Affichage des NOM et Prénom de la personne connectée
		frmTres.getContentPane().setBackground(Color.DARK_GRAY);
		frmTres.setBounds(100, 100, 800, 600);
		frmTres.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//SECTION : Création des Panels du menu
		
		JPanel menuTresPan = new JPanel();
		menuTresPan.setVisible(false);
		menuTresPan.setBackground(Color.DARK_GRAY);
		
		JPanel infosBtnTresPan = new JPanel();
		FlowLayout fl_infosBtnTresPan = (FlowLayout) infosBtnTresPan.getLayout();
		fl_infosBtnTresPan.setVgap(25);
		infosBtnTresPan.setBackground(Color.DARK_GRAY);
		
		JPanel catBtntresPan = new JPanel();
		FlowLayout fl_catBtntresPan = (FlowLayout) catBtntresPan.getLayout();
		fl_catBtntresPan.setVgap(25);
		catBtntresPan.setBackground(Color.DARK_GRAY);
		
		JPanel quitBtnTresPan = new JPanel();
		FlowLayout fl_quitBtnTresPan = (FlowLayout) quitBtnTresPan.getLayout();
		fl_quitBtnTresPan.setVgap(25);
		quitBtnTresPan.setBackground(Color.DARK_GRAY);
		
		JPanel titleTresMenuPan = new JPanel();
		titleTresMenuPan.setBackground(Color.DARK_GRAY);
		
		GroupLayout gl_menuTresPan = new GroupLayout(menuTresPan);
		gl_menuTresPan.setHorizontalGroup(
			gl_menuTresPan.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_menuTresPan.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_menuTresPan.createParallelGroup(Alignment.LEADING)
						.addComponent(titleTresMenuPan, GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
						.addComponent(catBtntresPan, GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
						.addComponent(infosBtnTresPan, GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
						.addComponent(quitBtnTresPan, GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_menuTresPan.setVerticalGroup(
			gl_menuTresPan.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_menuTresPan.createSequentialGroup()
					.addContainerGap()
					.addComponent(titleTresMenuPan, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(catBtntresPan, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(infosBtnTresPan, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(quitBtnTresPan, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addGap(104))
		);
		
		//FIN DE SECTION : Création des Panels du menu
		
		//SECTION : Création des éléments du menu
		
		JLabel blbTitleTres = new JLabel("Que voulez-vous faire ?");
		blbTitleTres.setMaximumSize(new Dimension(400, 50));
		blbTitleTres.setFont(new Font("Century Gothic", Font.BOLD, 65));
		blbTitleTres.setHorizontalAlignment(SwingConstants.CENTER);
		blbTitleTres.setPreferredSize(new Dimension(750, 80));
		blbTitleTres.setForeground(Color.WHITE);
		blbTitleTres.setAlignmentX(Component.CENTER_ALIGNMENT);
		titleTresMenuPan.add(blbTitleTres);
		
		//Bouton servant à quitter l'application
		JButton quitBtnTres = new JButton("Quitter");
		quitBtnTres.setFocusable(false);
		
		quitBtnTres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		
		quitBtnTres.setPreferredSize(new Dimension(400, 50));
		quitBtnTres.setForeground(Color.WHITE);
		quitBtnTres.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		quitBtnTres.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		quitBtnTres.setBackground(Color.DARK_GRAY);
		quitBtnTresPan.add(quitBtnTres);
		
		//Bouton menant à la fenêtre des cotisations
		JButton catBtnTres = new JButton("Cotisations");
		catBtnTres.setFocusable(false);
		
		catBtnTres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				populateCot(new CotisationTableModel());
				CardLayout cl = (CardLayout)frmTres.getContentPane().getLayout() ;
				cl.show(frmTres.getContentPane(), "name_cotTres");
			}
		});
		
		catBtnTres.setPreferredSize(new Dimension(400, 50));
		catBtnTres.setForeground(Color.WHITE);
		catBtnTres.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		catBtnTres.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		catBtnTres.setBackground(Color.DARK_GRAY);
		catBtntresPan.add(catBtnTres);
		
		//Bouton menant à la fenêtre des informations 
		JButton infosBtnTres = new JButton("Vos informations");
		infosBtnTres.setFocusable(false);
		
		infosBtnTres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)frmTres.getContentPane().getLayout() ;
				cl.show(frmTres.getContentPane(), "name_infosTres");
			}
		});
		
		infosBtnTres.setPreferredSize(new Dimension(400, 50));
		infosBtnTres.setForeground(Color.WHITE);
		infosBtnTres.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		infosBtnTres.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		infosBtnTres.setBackground(Color.DARK_GRAY);
		infosBtnTresPan.add(infosBtnTres);
		
		menuTresPan.setLayout(gl_menuTresPan);
		
		frmTres.getContentPane().setLayout(new CardLayout(0, 0));
		frmTres.getContentPane().add(menuTresPan, "name_menuTres");
		
		//FIN DE SECTION : Création des Panels de la fenêtre d'informations
		
		//SECTION : Création des Panels de la fenêtre d'informations
		
		JPanel infosTresPan = new JPanel();
		infosTresPan.setBackground(Color.DARK_GRAY);
		frmTres.getContentPane().add(infosTresPan, "name_infosTres");
		
		JPanel infoTitleTresPan = new JPanel();
		infoTitleTresPan.setBackground(Color.DARK_GRAY);
		
		JLabel infoTitleTres = new JLabel("Vos informations");
		infoTitleTres.setPreferredSize(new Dimension(750, 75));
		infoTitleTres.setHorizontalAlignment(SwingConstants.CENTER);
		infoTitleTres.setForeground(Color.WHITE);
		infoTitleTres.setFont(new Font("Century Gothic", Font.BOLD, 65));
		infoTitleTresPan.add(infoTitleTres);
		
		JPanel infosTresPanIn = new JPanel();
		infosTresPanIn.setBackground(Color.DARK_GRAY);
		
		JPanel infosTres = new JPanel();
		infosTres.setBackground(Color.DARK_GRAY);
		infosTres.setPreferredSize(new Dimension(740, 350));
		infosTresPanIn.add(infosTres);
		infosTres.setLayout(new GridLayout(7, 2, 0, 0));
		
		//FIN DE SECTION : Création des Panels de la fenêtre d'informations
		
		//SECTION : Création des éléments de la fenêtre d'informations
		
		JLabel nomLbl = new JLabel("Nom :");
		nomLbl.setForeground(Color.WHITE);
		nomLbl.setFont(new Font("Arial Black", Font.PLAIN, 20));
		infosTres.add(nomLbl);
		
		JLabel nomTxt = new JLabel(connected.getNom());
		nomTxt.setForeground(Color.WHITE);
		nomTxt.setFont(new Font("Century Gothic", Font.BOLD, 20));
		infosTres.add(nomTxt);
		
		JLabel prenomLbl = new JLabel("Pr\u00E9nom :");
		prenomLbl.setForeground(Color.WHITE);
		prenomLbl.setFont(new Font("Arial Black", Font.PLAIN, 20));
		infosTres.add(prenomLbl);
		
		JLabel prenomTxt = new JLabel(connected.getPrenom());
		prenomTxt.setForeground(Color.WHITE);
		prenomTxt.setFont(new Font("Century Gothic", Font.BOLD, 20));
		infosTres.add(prenomTxt);
		
		JLabel dateNaissLbl = new JLabel("Date de naissance :");
		dateNaissLbl.setForeground(Color.WHITE);
		dateNaissLbl.setFont(new Font("Arial Black", Font.PLAIN, 20));
		infosTres.add(dateNaissLbl);
		
		JLabel dateNaissTxt = new JLabel(connected.getDateNaiss().toString());
		dateNaissTxt.setForeground(Color.WHITE);
		dateNaissTxt.setFont(new Font("Century Gothic", Font.BOLD, 20));
		infosTres.add(dateNaissTxt);
		
		JLabel rueLbl = new JLabel("Rue :");
		rueLbl.setForeground(Color.WHITE);
		rueLbl.setFont(new Font("Arial Black", Font.PLAIN, 20));
		infosTres.add(rueLbl);
		
		JLabel rueTxt = new JLabel(connected.getAdr().getRue() + " " + connected.getAdr().getNum());
		rueTxt.setFont(new Font("Century Gothic", Font.BOLD, 20));
		rueTxt.setForeground(Color.WHITE);
		infosTres.add(rueTxt);
		
		JLabel codePostLbl = new JLabel("Code postal :");
		codePostLbl.setForeground(Color.WHITE);
		codePostLbl.setFont(new Font("Arial Black", Font.PLAIN, 20));
		infosTres.add(codePostLbl);
		
		JLabel codePostTxt = new JLabel(connected.getAdr().getCodePost());
		codePostTxt.setForeground(Color.WHITE);
		codePostTxt.setFont(new Font("Century Gothic", Font.BOLD, 20));
		infosTres.add(codePostTxt);
		
		JLabel villeLbl = new JLabel("Ville :");
		villeLbl.setForeground(Color.WHITE);
		villeLbl.setFont(new Font("Arial Black", Font.PLAIN, 20));
		infosTres.add(villeLbl);
		
		JLabel villeTxt = new JLabel(connected.getAdr().getVille());
		villeTxt.setForeground(Color.WHITE);
		villeTxt.setFont(new Font("Century Gothic", Font.BOLD, 20));
		infosTres.add(villeTxt);
		
		JLabel paysLbl = new JLabel("Pays :");
		paysLbl.setForeground(Color.WHITE);
		paysLbl.setFont(new Font("Arial Black", Font.PLAIN, 20));
		infosTres.add(paysLbl);
		
		JLabel paysTxt = new JLabel(connected.getAdr().getPays());
		paysTxt.setForeground(Color.WHITE);
		paysTxt.setFont(new Font("Century Gothic", Font.BOLD, 20));
		infosTres.add(paysTxt);
		
		//Bouton de retour en arrière
		JButton infosTresBackBtn = new JButton("Retour");
		infosTresBackBtn.setFocusable(false);
		
		infosTresBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)frmTres.getContentPane().getLayout() ;
				cl.show(frmTres.getContentPane(), "name_menuTres");
			}
		});
		
		infosTresBackBtn.setPreferredSize(new Dimension(300, 50));
		infosTresBackBtn.setForeground(Color.WHITE);
		infosTresBackBtn.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		infosTresBackBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		infosTresBackBtn.setBackground(Color.DARK_GRAY);
		infosTresPanIn.add(infosTresBackBtn);
		
		GroupLayout gl_infosTresPan = new GroupLayout(infosTresPan);
		gl_infosTresPan.setHorizontalGroup(
			gl_infosTresPan.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_infosTresPan.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_infosTresPan.createParallelGroup(Alignment.LEADING)
						.addComponent(infoTitleTresPan, GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
						.addComponent(infosTresPanIn, GroupLayout.PREFERRED_SIZE, 748, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_infosTresPan.setVerticalGroup(
			gl_infosTresPan.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_infosTresPan.createSequentialGroup()
					.addContainerGap()
					.addComponent(infoTitleTresPan, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(infosTresPanIn, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		infosTresPan.setLayout(gl_infosTresPan);
		
		//FIN DE SECTION : Création des éléments de la fenêtre d'informations
		
		//SECTION : Création des Panels de la fenêtre des cotisations
		
		JPanel cotPan = new JPanel();
		cotPan.setBackground(Color.DARK_GRAY);
		frmTres.getContentPane().add(cotPan, "name_cotTres");
		
		JPanel titleCotPan = new JPanel();
		FlowLayout fl_titleCotPan = (FlowLayout) titleCotPan.getLayout();
		fl_titleCotPan.setVgap(1);
		fl_titleCotPan.setHgap(1);
		titleCotPan.setBackground(Color.DARK_GRAY);
		
		JLabel titleCot = new JLabel("Cotisations");
		titleCot.setVerticalAlignment(SwingConstants.TOP);
		titleCot.setPreferredSize(new Dimension(750, 82));
		titleCot.setHorizontalAlignment(SwingConstants.CENTER);
		titleCot.setForeground(Color.WHITE);
		titleCot.setFont(new Font("Century Gothic", Font.BOLD, 65));
		titleCotPan.add(titleCot);
		
		//FIN DE SECTION : Création des Panels de la fenêtre des cotisations
		
		//SECTION : Création des éléments de la fenêtre des cotisations
		
		//Bouton de retour en arrière
		JButton backCotBtn = new JButton("Retour");
		
		backCotBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout)frmTres.getContentPane().getLayout() ;
				cl.show(frmTres.getContentPane(), "name_menuTres");
			}
		});
		
		backCotBtn.setPreferredSize(new Dimension(200, 50));
		backCotBtn.setForeground(Color.WHITE);
		backCotBtn.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		backCotBtn.setFocusable(false);
		backCotBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		backCotBtn.setBackground(Color.DARK_GRAY);
		
		JPanel cotPanIn = new JPanel();
		cotPanIn.setBackground(Color.DARK_GRAY);
		cotPanIn.setPreferredSize(new Dimension(740, 350));
		
		//Bouton de suppression d'un membre
		JButton cotDelBtn = new JButton("Supprimer");
		cotDelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				membreDAO.delete(membres.get(cotTable.getSelectedRow()));
				populateCot(new CotisationTableModel());
			}
		});
		cotDelBtn.setPreferredSize(new Dimension(200, 50));
		cotDelBtn.setForeground(Color.WHITE);
		cotDelBtn.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		cotDelBtn.setFocusable(false);
		cotDelBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		cotDelBtn.setBackground(Color.DARK_GRAY);
		
		//Bouton d'ajout d'un membre
		JButton cotAddBtn = new JButton("Ajouter");
		cotAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)frmTres.getContentPane().getLayout() ;
				cl.show(frmTres.getContentPane(), "name_addMbr");
			}
		});
		cotAddBtn.setPreferredSize(new Dimension(200, 50));
		cotAddBtn.setForeground(Color.WHITE);
		cotAddBtn.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		cotAddBtn.setFocusable(false);
		cotAddBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		cotAddBtn.setBackground(Color.DARK_GRAY);
		
		GroupLayout gl_cotPan = new GroupLayout(cotPan);
		gl_cotPan.setHorizontalGroup(
			gl_cotPan.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_cotPan.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_cotPan.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_cotPan.createSequentialGroup()
							.addComponent(titleCotPan, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cotPanIn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(372))
						.addGroup(gl_cotPan.createSequentialGroup()
							.addComponent(cotAddBtn, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cotDelBtn, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(backCotBtn, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
							.addGap(228))))
		);
		gl_cotPan.setVerticalGroup(
			gl_cotPan.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_cotPan.createSequentialGroup()
					.addGroup(gl_cotPan.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_cotPan.createSequentialGroup()
							.addContainerGap()
							.addComponent(titleCotPan, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addGap(364))
						.addGroup(gl_cotPan.createSequentialGroup()
							.addContainerGap(94, Short.MAX_VALUE)
							.addComponent(cotPanIn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_cotPan.createParallelGroup(Alignment.LEADING)
						.addComponent(cotAddBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(cotDelBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(backCotBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
					.addGap(62))
		);
		
		JScrollPane cotSP = new JScrollPane((Component) null);
		cotSP.setPreferredSize(new Dimension(740, 350));
		cotSP.setForeground(Color.BLACK);
		cotSP.setBorder(new LineBorder(new Color(0, 0, 0)));
		cotSP.setBackground(Color.DARK_GRAY);
		
		GroupLayout gl_cotPanIn = new GroupLayout(cotPanIn);
		gl_cotPanIn.setHorizontalGroup(
			gl_cotPanIn.createParallelGroup(Alignment.LEADING)
				.addComponent(cotSP, GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
		);
		gl_cotPanIn.setVerticalGroup(
			gl_cotPanIn.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_cotPanIn.createSequentialGroup()
					.addComponent(cotSP, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		//Table contenaant les membres du club
		cotTable = new JTable();
		cotTable.setShowVerticalLines(false);
		cotTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cotTable.setSelectionBackground(Color.GRAY);
		cotTable.setRowHeight(60);
		cotTable.setPreferredSize(new Dimension(740, 328));
		cotTable.setPreferredScrollableViewportSize(new Dimension(740, 320));
		cotTable.setGridColor(Color.BLACK);
		cotTable.setForeground(Color.WHITE);
		cotTable.setFont(new Font("Arial Black", Font.PLAIN, 25));
		cotTable.setFocusable(false);
		cotTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		cotTable.setBackground(Color.DARK_GRAY);
		cotTable.setModel(new CotisationTableModel());
		cotSP.setViewportView(cotTable);
		cotPanIn.setLayout(gl_cotPanIn);
		
		//FIN DE SECTION : Création des éléments de la fenêtre des cotisations
		
		//SECTION : Création des Panels de la fenêtre d'ajout d'un membre
		
		JPanel addMbrPan = new JPanel();
		addMbrPan.setBackground(Color.DARK_GRAY);
		frmTres.getContentPane().add(addMbrPan, "name_addMbr");
		
		JPanel addMbrTitlePan = new JPanel();
		addMbrTitlePan.setBackground(Color.DARK_GRAY);
		
		//FIN DE SECTION : Création des Panels de la fenêtre d'ajout d'un membre
		
		//SECTION : Création des éléments de la fenêtre d'ajout d'un membre
		
		JLabel addMbrTitle = new JLabel("Nouveau membre");
		addMbrTitle.setPreferredSize(new Dimension(750, 75));
		addMbrTitle.setHorizontalAlignment(SwingConstants.CENTER);
		addMbrTitle.setForeground(Color.WHITE);
		addMbrTitle.setFont(new Font("Century Gothic", Font.BOLD, 65));
		addMbrTitlePan.add(addMbrTitle);
		
		JPanel addMbrPanIn = new JPanel();
		addMbrPanIn.setBackground(Color.DARK_GRAY);
		
		JPanel addMbr = new JPanel();
		addMbr.setPreferredSize(new Dimension(740, 350));
		addMbr.setBackground(Color.DARK_GRAY);
		addMbrPanIn.add(addMbr);
		addMbr.setLayout(new GridLayout(10, 2, 0, 0));
		
		JLabel lblNom = new JLabel("Nom : ");
		lblNom.setForeground(Color.WHITE);
		lblNom.setFont(new Font("Arial Black", Font.PLAIN, 20));
		addMbr.add(lblNom);
		
		txtNom = new JTextField();
		txtNom.setForeground(Color.WHITE);
		txtNom.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtNom.setColumns(10);
		txtNom.setBorder(new LineBorder(Color.BLACK));
		txtNom.setBackground(Color.GRAY);
		addMbr.add(txtNom);
		
		JLabel lblPrenom = new JLabel("Pr\u00E9nom :");
		lblPrenom.setForeground(Color.WHITE);
		lblPrenom.setFont(new Font("Arial Black", Font.PLAIN, 20));
		addMbr.add(lblPrenom);
		
		txtPrenom = new JTextField();
		txtPrenom.setForeground(Color.WHITE);
		txtPrenom.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtPrenom.setColumns(10);
		txtPrenom.setBorder(new LineBorder(Color.BLACK));
		txtPrenom.setBackground(Color.GRAY);
		addMbr.add(txtPrenom);
		
		JLabel lblMdp = new JLabel("Mot de passe :");
		lblMdp.setForeground(Color.WHITE);
		lblMdp.setFont(new Font("Arial Black", Font.PLAIN, 20));
		addMbr.add(lblMdp);
		
		txtMdp = new JTextField();
		txtMdp.setForeground(Color.WHITE);
		txtMdp.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtMdp.setColumns(10);
		txtMdp.setBorder(new LineBorder(Color.BLACK));
		txtMdp.setBackground(Color.GRAY);
		addMbr.add(txtMdp);
		
		JLabel lblDateNaiss = new JLabel("Date de naissance :");
		lblDateNaiss.setForeground(Color.WHITE);
		lblDateNaiss.setFont(new Font("Arial Black", Font.PLAIN, 20));
		addMbr.add(lblDateNaiss);
		
		JPanel dateNaissPan = new JPanel();
		dateNaissPan.setBackground(Color.DARK_GRAY);
		addMbr.add(dateNaissPan);
		dateNaissPan.setLayout(new GridLayout(1, 3, 0, 0));
		
		txtJour = new JTextField();
		txtJour.setText("JJ");
		txtJour.setForeground(Color.WHITE);
		txtJour.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtJour.setColumns(10);
		txtJour.setBorder(new LineBorder(Color.BLACK));
		txtJour.setBackground(Color.GRAY);
		dateNaissPan.add(txtJour);
		
		txtMois = new JTextField();
		txtMois.setText("MM");
		txtMois.setForeground(Color.WHITE);
		txtMois.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtMois.setColumns(10);
		txtMois.setBorder(new LineBorder(Color.BLACK));
		txtMois.setBackground(Color.GRAY);
		dateNaissPan.add(txtMois);
		
		txtAnnee = new JTextField();
		txtAnnee.setText("AAAA");
		txtAnnee.setForeground(Color.WHITE);
		txtAnnee.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtAnnee.setColumns(10);
		txtAnnee.setBorder(new LineBorder(Color.BLACK));
		txtAnnee.setBackground(Color.GRAY);
		dateNaissPan.add(txtAnnee);
		
		JLabel lblRue = new JLabel("Rue :");
		lblRue.setForeground(Color.WHITE);
		lblRue.setFont(new Font("Arial Black", Font.PLAIN, 20));
		addMbr.add(lblRue);
		
		txtRue = new JTextField();
		txtRue.setForeground(Color.WHITE);
		txtRue.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtRue.setColumns(10);
		txtRue.setBorder(new LineBorder(Color.BLACK));
		txtRue.setBackground(Color.GRAY);
		addMbr.add(txtRue);
		
		JLabel lblNum = new JLabel("Num\u00E9ro :");
		lblNum.setForeground(Color.WHITE);
		lblNum.setFont(new Font("Arial Black", Font.PLAIN, 20));
		addMbr.add(lblNum);
		
		txtNum = new JTextField();
		txtNum.setForeground(Color.WHITE);
		txtNum.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtNum.setColumns(10);
		txtNum.setBorder(new LineBorder(Color.BLACK));
		txtNum.setBackground(Color.GRAY);
		addMbr.add(txtNum);
		
		JLabel lblCP = new JLabel("Code postal :");
		lblCP.setForeground(Color.WHITE);
		lblCP.setFont(new Font("Arial Black", Font.PLAIN, 20));
		addMbr.add(lblCP);
		
		txtCP = new JTextField();
		txtCP.setForeground(Color.WHITE);
		txtCP.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtCP.setColumns(10);
		txtCP.setBorder(new LineBorder(Color.BLACK));
		txtCP.setBackground(Color.GRAY);
		addMbr.add(txtCP);
		
		JLabel lblVille = new JLabel("Ville :");
		lblVille.setForeground(Color.WHITE);
		lblVille.setFont(new Font("Arial Black", Font.PLAIN, 20));
		addMbr.add(lblVille);
		
		txtVille = new JTextField();
		txtVille.setForeground(Color.WHITE);
		txtVille.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtVille.setColumns(10);
		txtVille.setBorder(new LineBorder(Color.BLACK));
		txtVille.setBackground(Color.GRAY);
		addMbr.add(txtVille);
		
		JLabel lblPays = new JLabel("Pays :");
		lblPays.setForeground(Color.WHITE);
		lblPays.setFont(new Font("Arial Black", Font.PLAIN, 20));
		addMbr.add(lblPays);
		
		txtPays = new JTextField();
		txtPays.setForeground(Color.WHITE);
		txtPays.setFont(new Font("Arial Black", Font.PLAIN, 20));
		txtPays.setColumns(10);
		txtPays.setBorder(new LineBorder(Color.BLACK));
		txtPays.setBackground(Color.GRAY);
		addMbr.add(txtPays);
		
		JLabel lblCat = new JLabel("Cat\u00E9gorie :");
		lblCat.setForeground(Color.WHITE);
		lblCat.setFont(new Font("Arial Black", Font.PLAIN, 20));
		addMbr.add(lblCat);
		
		JComboBox<Categorie> catCB = new JComboBox<>(new DefaultComboBoxModel<Categorie>(categorieDAO.findAll().toArray(new Categorie[0])));
		catCB.setMaximumRowCount(4);
		catCB.setForeground(Color.WHITE);
		catCB.setFont(new Font("Arial Black", Font.PLAIN, 20));
		catCB.setBackground(new Color(128, 128, 128));
		addMbr.add(catCB);
		
		//Bouton servant à valider l'ajout d'un membre
		JButton addMbrValidBtn = new JButton("Valider");
		addMbrValidBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Récupération de la catégorie
				Categorie selected = (Categorie)catCB.getSelectedItem();
				Categorie cat = null;
				if(selected instanceof VTT) {
					cat = categorieDAO.find((int)selected.getId());
				}
				else if(selected instanceof Cyclo) {
					cat = categorieDAO.find((int)selected.getId());
				}
				
				@SuppressWarnings("deprecation")
				long dateTmp = java.sql.Date.UTC(Integer.parseInt(txtAnnee.getText()) - 1900,Integer.parseInt(txtMois.getText()), Integer.parseInt(txtJour.getText()), 0, 0, 0);
				
				boolean mbreCreated = membreDAO.create(new Membre(0,
											txtNom.getText(),
											txtPrenom.getText(),
											new java.sql.Date(dateTmp),
											new Adresse(0,
														txtRue.getText(),
														Integer.parseInt(txtNum.getText()),
														txtVille.getText(),
														txtCP.getText(),
														txtPays.getText()),
											20f,
											txtMdp.getText()));
				
				membreDAO.addCat(cat.getId(), (int)membreDAO.getGeneratedId(), 0f);
				
				if(mbreCreated) {
					populateCot(new CotisationTableModel());
					CardLayout cl = (CardLayout)frmTres.getContentPane().getLayout() ;
					cl.show(frmTres.getContentPane(), "name_cotTres");
				}
				else {
					JOptionPane.showMessageDialog(frmTres, "La création ne s'est pas effectuée");
				}
			}
		});
		addMbrValidBtn.setPreferredSize(new Dimension(300, 50));
		addMbrValidBtn.setForeground(Color.WHITE);
		addMbrValidBtn.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		addMbrValidBtn.setFocusable(false);
		addMbrValidBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		addMbrValidBtn.setBackground(Color.DARK_GRAY);
		addMbrPanIn.add(addMbrValidBtn);
		
		//Bouton de retour en arrière
		JButton addMbrBackBtn = new JButton("Retour");
		addMbrBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)frmTres.getContentPane().getLayout() ;
				cl.show(frmTres.getContentPane(), "name_cotTres");
			}
		});
		addMbrBackBtn.setPreferredSize(new Dimension(300, 50));
		addMbrBackBtn.setForeground(Color.WHITE);
		addMbrBackBtn.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		addMbrBackBtn.setFocusable(false);
		addMbrBackBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		addMbrBackBtn.setBackground(Color.DARK_GRAY);
		addMbrPanIn.add(addMbrBackBtn);
		GroupLayout gl_addMbrPan = new GroupLayout(addMbrPan);
		gl_addMbrPan.setHorizontalGroup(
			gl_addMbrPan.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 784, Short.MAX_VALUE)
				.addGroup(gl_addMbrPan.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_addMbrPan.createParallelGroup(Alignment.LEADING)
						.addComponent(addMbrTitlePan, GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
						.addComponent(addMbrPanIn, GroupLayout.PREFERRED_SIZE, 748, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_addMbrPan.setVerticalGroup(
			gl_addMbrPan.createParallelGroup(Alignment.LEADING)
				.addGap(0, 561, Short.MAX_VALUE)
				.addGroup(gl_addMbrPan.createSequentialGroup()
					.addContainerGap()
					.addComponent(addMbrTitlePan, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(addMbrPanIn, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(41, Short.MAX_VALUE))
		);
		addMbrPan.setLayout(gl_addMbrPan);
		
		//SECTION : Création des éléments de la fenêtre d'ajout d'un membre
	}
	
	//FIN DE SECTION : Corps
	
	//SECTION : Méthodes supplémentaires
	
	//Remplissage de la table de cotisations
	public void populateCot(DefaultTableModel model) {
		//Netoyage du tableau
		cotTable.removeAll();
		
		//Nettoyage de la liste de membres si besoin
		if(membres.size() > 0)
			membres.clear();
		
		//Récupération des membres
		for(Membre m : membreDAO.findAll()) {
			model.addRow(new Object[] {m.getNom().toUpperCase(),
									   m.getPrenom(),
									   Float.toString(m.getCotisation())});
			membres.add(m);
		}
		cotTable.setModel(model);
	}
}
