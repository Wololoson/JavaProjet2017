package be.wilson.windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Window.Type;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import java.awt.Dimension;
import javax.swing.JPasswordField;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;

public class Connexion {

	private JFrame frmConnexion;
	private JPasswordField pwdFld;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connexion window = new Connexion();
					window.frmConnexion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Connexion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConnexion = new JFrame();
		frmConnexion.getContentPane().setForeground(Color.WHITE);
		frmConnexion.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Wilson\\OneDrive - HEPH-Condorcet\\Troisieme_bloc\\Programmation Avanc\u00E9e\\Projets\\ProjetJava\\ProjetWS\\Windows\\logo-\u00E9cole.jpg"));
		frmConnexion.setTitle("Connexion");
		frmConnexion.getContentPane().setBackground(Color.DARK_GRAY);
		
		JPanel titlePan = new JPanel();
		titlePan.setBackground(Color.DARK_GRAY);
		
		JPanel namePan = new JPanel();
		FlowLayout flowLayout = (FlowLayout) namePan.getLayout();
		flowLayout.setVgap(15);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		namePan.setBackground(Color.DARK_GRAY);
		
		JPanel nameInputPan = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) nameInputPan.getLayout();
		flowLayout_3.setVgap(15);
		nameInputPan.setBackground(Color.DARK_GRAY);
		
		JPanel pwdFldPan = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) pwdFldPan.getLayout();
		flowLayout_4.setVgap(15);
		pwdFldPan.setBackground(Color.DARK_GRAY);
		
		JPanel pwdPan = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pwdPan.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		flowLayout_1.setVgap(15);
		pwdPan.setBackground(Color.DARK_GRAY);
		
		JPanel connButPan = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) connButPan.getLayout();
		flowLayout_2.setVgap(15);
		connButPan.setBackground(Color.DARK_GRAY);
		GroupLayout groupLayout = new GroupLayout(frmConnexion.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(connButPan, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(namePan, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(nameInputPan, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
								.addComponent(titlePan, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE))
							.addGap(15))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(pwdPan, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pwdFldPan, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(titlePan, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(namePan, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addComponent(nameInputPan, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(pwdFldPan, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addComponent(pwdPan, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(connButPan, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		pwdFld = new JPasswordField();
		pwdFld.setPreferredSize(new Dimension(170, 20));
		pwdFld.setSize(new Dimension(170, 20));
		pwdFldPan.add(pwdFld);
		
		JComboBox nameCB = new JComboBox();
		nameCB.setPreferredSize(new Dimension(170, 20));
		nameCB.setMinimumSize(new Dimension(170, 20));
		nameCB.setForeground(Color.BLACK);
		nameCB.setBackground(Color.LIGHT_GRAY);
		nameCB.setMaximumRowCount(999);
		nameInputPan.add(nameCB);
		
		JLabel pwd = new JLabel("Mot de passe :");
		pwd.setHorizontalAlignment(SwingConstants.LEFT);
		pwd.setForeground(Color.WHITE);
		pwdPan.add(pwd);
		
		JLabel name = new JLabel("Nom :");
		name.setHorizontalAlignment(SwingConstants.LEFT);
		name.setForeground(Color.WHITE);
		namePan.add(name);
		
		JButton connBut = new JButton("Se connecter");
		connBut.setAlignmentX(Component.CENTER_ALIGNMENT);
		connBut.setPreferredSize(new Dimension(200, 30));
		connBut.setBackground(Color.GRAY);
		connButPan.add(connBut);
		
		JLabel lblBienvenue = new JLabel("Bienvenue");
		lblBienvenue.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblBienvenue.setForeground(Color.WHITE);
		lblBienvenue.setBackground(Color.DARK_GRAY);
		titlePan.add(lblBienvenue);
		frmConnexion.getContentPane().setLayout(groupLayout);
		frmConnexion.setBounds(100, 100, 450, 300);
		frmConnexion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
