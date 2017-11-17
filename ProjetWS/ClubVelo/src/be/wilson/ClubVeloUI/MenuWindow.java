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
import be.wilson.ClubVeloPOJO.Personne;
import be.wilson.ClubVeloPOJO.VTT;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JPanel;
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
		btnVosInformations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVosInformations.setPreferredSize(new Dimension(400, 50));
		btnVosInformations.setForeground(Color.WHITE);
		btnVosInformations.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		btnVosInformations.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnVosInformations.setBackground(Color.DARK_GRAY);
		infosBtnPan.add(btnVosInformations);
		
		JButton btnNewButton = new JButton("Covoiturage");
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
		
		JPanel codrivingPan = new JPanel();
		codrivingPan.setVisible(false);
		codrivingPan.setBackground(Color.DARK_GRAY);
		frmMenu.getContentPane().add(codrivingPan, "name_1879696200068");
		
		JPanel titlePan = new JPanel();
		titlePan.setBackground(Color.DARK_GRAY);
		
		JPanel balPan = new JPanel();
		balPan.setBackground(Color.DARK_GRAY);
		GroupLayout gl_codrivingPan = new GroupLayout(codrivingPan);
		gl_codrivingPan.setHorizontalGroup(
			gl_codrivingPan.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_codrivingPan.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_codrivingPan.createParallelGroup(Alignment.TRAILING)
						.addComponent(balPan, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
						.addComponent(titlePan, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_codrivingPan.setVerticalGroup(
			gl_codrivingPan.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_codrivingPan.createSequentialGroup()
					.addContainerGap()
					.addComponent(titlePan, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(balPan, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		balTable = new JTable();
		balTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		balTable.setShowGrid(false);
		balTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		balTable.setPreferredSize(new Dimension(740, 350));
		balTable.setSelectionBackground(new Color(128, 128, 128));
		balTable.setForeground(Color.WHITE);
		balTable.setBackground(Color.DARK_GRAY);
		populate(new BaladeTableModel());
		
		balPan.add(balTable);
		
		JButton selectBalBtn = new JButton("Choisir");
		selectBalBtn.setForeground(Color.WHITE);
		selectBalBtn.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		selectBalBtn.setPreferredSize(new Dimension(300, 50));
		selectBalBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		selectBalBtn.setBackground(Color.DARK_GRAY);
		balPan.add(selectBalBtn);
		
		JButton balBackBtn = new JButton("Retour");
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
		titlePan.add(lblBalades);
		codrivingPan.setLayout(gl_codrivingPan);
	}
	
	public void populate(DefaultTableModel model) {
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
