package be.wilson.ClubVeloUI;

import java.awt.EventQueue;

import javax.swing.JFrame;

import be.wilson.ClubVeloConnection.DBConnection;
import be.wilson.ClubVeloDAO.AdresseDAO;
import be.wilson.ClubVeloDAO.BaladeDAO;
import be.wilson.ClubVeloDAO.DAO;
import be.wilson.ClubVeloPOJO.Adresse;
import be.wilson.ClubVeloPOJO.Balade;
import be.wilson.ClubVeloPOJO.Categorie;
import be.wilson.ClubVeloPOJO.Cyclo;
import be.wilson.ClubVeloPOJO.Membre;
import be.wilson.ClubVeloPOJO.Personne;
import be.wilson.ClubVeloPOJO.Responsable;
import be.wilson.ClubVeloPOJO.Tresorier;
import be.wilson.ClubVeloPOJO.VTT;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class MenuWindow {

	private JFrame frmMenu;
	private Personne connected;
	private JTable balTable;
	Connection conn;

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
		conn = DBConnection.getInstance();
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
			otherLbl = new JLabel("Cotisation :");
			otherTxt = new JLabel(Float.toString(((Membre) connected).getCotisation()) + " €");
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
	}
	
	public void populateBal(DefaultTableModel model) {
		String catName;
		DAO<Balade> balDAO = new BaladeDAO(conn);
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
