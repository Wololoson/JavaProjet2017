package be.wilson.ClubVeloUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import be.wilson.ClubVeloDAO.DAO;
import be.wilson.ClubVeloFactory.AbstractDAOFactory;
import be.wilson.ClubVeloPOJO.Tresorier;

public class TresCodeWindow {

	private JFrame frmCode;
	private JPasswordField codeFld;
	
	private Tresorier selected;

	/**
	 * Méthode appelée pour afficher la fenêtre de vérification du code
	 */
	public void displayTresCode() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmCode.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Création
	 */
	public TresCodeWindow(Tresorier t) {
		selected = t;
		initialize();
	}

	/**
	 * Initialisation
	 */
	private void initialize() {
		frmCode = new JFrame();
		frmCode.getContentPane().setBackground(Color.DARK_GRAY);
		frmCode.setBackground(Color.DARK_GRAY);
		frmCode.setBounds(100, 100, 440, 220);
		frmCode.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel codeTitlePan = new JPanel();
		codeTitlePan.setBackground(Color.DARK_GRAY);
		
		JPanel codePan = new JPanel();
		codePan.setBackground(Color.DARK_GRAY);
		GroupLayout groupLayout = new GroupLayout(frmCode.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(codePan, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
						.addComponent(codeTitlePan, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(codeTitlePan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(codePan, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		codeFld = new JPasswordField();
		codeFld.setBackground(Color.GRAY);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
				DAO<Tresorier> tresDAO = adf.getTresorierDAO();
				Tresorier verif = tresDAO.find((int)selected.getId());
				
				if(String.valueOf(codeFld.getPassword()).equals(verif.getCode())) {
					frmCode.setVisible(false); 
					frmCode.dispose();
					TresWindow tres;
					tres = new TresWindow((Tresorier)selected);
					tres.tresDisplay();
				}
				else {
					JOptionPane.showMessageDialog(frmCode, "Code incorrect");
				}
			}
		});
		btnValider.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnValider.setForeground(Color.WHITE);
		btnValider.setFont(new Font("Arial Black", Font.PLAIN, 20));
		btnValider.setBackground(Color.DARK_GRAY);
		GroupLayout gl_codePan = new GroupLayout(codePan);
		gl_codePan.setHorizontalGroup(
			gl_codePan.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_codePan.createSequentialGroup()
					.addGap(133)
					.addGroup(gl_codePan.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnValider, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
						.addComponent(codeFld, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
					.addGap(125))
		);
		gl_codePan.setVerticalGroup(
			gl_codePan.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_codePan.createSequentialGroup()
					.addContainerGap()
					.addComponent(codeFld, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnValider, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
		);
		codePan.setLayout(gl_codePan);
		
		JLabel codeTitleLbl = new JLabel("Code du t\u00E9sorier");
		codeTitleLbl.setFont(new Font("Century Gothic", Font.BOLD, 30));
		codeTitleLbl.setForeground(Color.WHITE);
		codeTitlePan.add(codeTitleLbl);
		frmCode.getContentPane().setLayout(groupLayout);
		
		
	}
}
