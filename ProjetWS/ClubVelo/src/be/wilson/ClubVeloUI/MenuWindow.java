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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import be.wilson.ClubVeloDAO.DAO;
import be.wilson.ClubVeloDAO.MembreDAO;
import be.wilson.ClubVeloFactory.AbstractDAOFactory;
import be.wilson.ClubVeloPOJO.Balade;
import be.wilson.ClubVeloPOJO.Categorie;
import be.wilson.ClubVeloPOJO.Cyclo;
import be.wilson.ClubVeloPOJO.Membre;
import be.wilson.ClubVeloPOJO.Personne;
import be.wilson.ClubVeloPOJO.Responsable;
import be.wilson.ClubVeloPOJO.Tresorier;
import be.wilson.ClubVeloPOJO.VTT;

public class MenuWindow {

	private JFrame frmMenu;
	private Personne connected;
	private JTable balTable;
	JLabel lblStatusRand;
	AbstractDAOFactory adf;

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
	public MenuWindow(long id, String type) {
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		if(type == "Membre") {
			DAO<Membre> mbreDAO = adf.getMembreDAO();
			connected = mbreDAO.find((int)id);
		}
		else if(type == "Responsable") {
			DAO<Responsable> respDAO = adf.getResponsableDAO();
			connected = respDAO.find((int)id);
		}
		else {
			DAO<Tresorier> tresDAO = adf.getTresorierDAO();
			connected = tresDAO.find((int)id);
		}
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
		
		JLabel lblQueCoulezvouFaire = new JLabel("Que voulez-vous faire ?");
		lblQueCoulezvouFaire.setMaximumSize(new Dimension(400, 50));
		lblQueCoulezvouFaire.setFont(new Font("Century Gothic", Font.BOLD, 65));
		lblQueCoulezvouFaire.setHorizontalAlignment(SwingConstants.CENTER);
		lblQueCoulezvouFaire.setPreferredSize(new Dimension(750, 80));
		lblQueCoulezvouFaire.setForeground(Color.WHITE);
		lblQueCoulezvouFaire.setAlignmentX(Component.CENTER_ALIGNMENT);
		titlePanel.add(lblQueCoulezvouFaire);
		
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
		populateBal(new BaladeTableModel());
		JScrollPane balSP = new JScrollPane(balTable);
		balSP.setBorder(new LineBorder(new Color(0, 0, 0)));
		balSP.setForeground(new Color(0, 0, 0));
		balSP.setPreferredSize(new Dimension(740, 350));
		balSP.setBackground(Color.DARK_GRAY);
		balPan.add(balSP);
		
		JButton selectBalBtn = new JButton("Choisir");
		selectBalBtn.setFocusable(false);
		selectBalBtn.setForeground(Color.WHITE);
		selectBalBtn.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		selectBalBtn.setPreferredSize(new Dimension(300, 50));
		selectBalBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		selectBalBtn.setBackground(Color.DARK_GRAY);
		balPan.add(selectBalBtn);
		
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
		
		JLabel otherLbl;
		JLabel otherTxt;
		if(connected instanceof Membre) {
			MembreDAO membreDAO = (MembreDAO) adf.getMembreDAO();
			float suppl = membreDAO.getSuppl(connected.getId());
			otherLbl = new JLabel("Cotisation :");
			otherTxt = new JLabel(Float.toString(((Membre) connected).getCotisation()) + Float.toString(suppl) + " €");
		}
		else if(connected instanceof Responsable) {
			otherLbl = new JLabel("Date de fin :");
			otherTxt = new JLabel(((Responsable)connected).getDateExp().toString());
		}
		else if(connected instanceof Tresorier) {
			otherLbl = new JLabel("");
			otherTxt = new JLabel("");
		}
		else {
			otherLbl = new JLabel("Error");
			otherTxt = new JLabel("Error");
		}
		
		
		otherLbl.setForeground(Color.WHITE);
		otherLbl.setFont(new Font("Arial Black", Font.PLAIN, 20));
		infos.add(otherLbl);
		
		
		otherTxt.setForeground(Color.WHITE);
		otherTxt.setFont(new Font("Century Gothic", Font.BOLD, 20));
		infos.add(otherTxt);
		
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
		JButton btnInscRand = new JButton("S'inscrire");
		btnInscRand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblStatusRand.setText("Inscrit");
				btnInscRand.setEnabled(false);
				((Membre)connected).ajouterCat(1);
			}
		});
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
		JButton btnInscDesc = new JButton("S'inscrire");
		btnInscDesc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblStatusDesc.setText("Inscrit");
				btnInscDesc.setEnabled(false);
				((Membre)connected).ajouterCat(2);
			}
		});
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
		JButton btnInscTri = new JButton("S'inscrire");
		btnInscTri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblStatusTri.setText("Inscrit");
				btnInscTri.setEnabled(false);
				((Membre)connected).ajouterCat(3);
			}
		});
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
		JButton btnInscCyc = new JButton("S'inscrire");
		btnInscCyc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblStatusCyc.setText("Inscrit");
				btnInscCyc.setEnabled(false);
				((Membre)connected).ajouterCat(4);
			}
		});
		
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
	}
	
	public void populateBal(DefaultTableModel model) {
		String catName;
		DAO<Balade> balDAO = adf.getBaladeDAO();
		for(Balade b : balDAO.findAll()) {
			if(b.getCat() instanceof VTT) {
				VTT v = (VTT)b.getCat();
				catName = v.getType().toString();
			}
			else
				catName = "Cyclo";
			
			model.addRow(new Object[] {b.getLibelle(), b.getDate().toString(), catName});
		}
		balTable.setModel(model);
	}
}
