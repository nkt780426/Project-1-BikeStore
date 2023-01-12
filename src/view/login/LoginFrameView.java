package view.login;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controller.login.LoginFrameController;

@SuppressWarnings("serial")
public class LoginFrameView extends JFrame {
	public JButton btnSignIn;
	public JButton btnSignUp;
	public JRadioButton rdbStaff;
	public JRadioButton rdbAdmin;
	public JTextField txtUsername;
	public JPasswordField password;

	public LoginFrameView() {
		initComnponent();
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void initComnponent() {
		// component
		// Page_Start
		JLabel lbBikeStores = new JLabel("BIKE STORES", JLabel.CENTER);
		lbBikeStores.setFont(new Font("Arial", Font.BOLD, 20));
		// CENTER
		Font font = new Font("Arial", Font.PLAIN, 16);
		rdbStaff = new JRadioButton("staff");
		rdbStaff.setFont(font);
		rdbAdmin = new JRadioButton("admin");
		rdbAdmin.setFont(font);
		JLabel lbUsername = new JLabel("Username");
		lbUsername.setFont(font);
		txtUsername = new JTextField();
		txtUsername.setFont(font);
		txtUsername.setToolTipText("Unsigned");
		password = new JPasswordField();
		password.setFont(font);
		JLabel lbPassword = new JLabel("Password");
		lbPassword.setFont(font);
		btnSignIn = new JButton("Sign In", new ImageIcon(getClass().getResource("/icon/Sign_In16.png")));
		btnSignIn.setFont(font);
		btnSignUp = new JButton("Sign Up", new ImageIcon(getClass().getResource("/icon/Sign_Up16.png")));
		btnSignUp.setFont(font);
		// Line_Start
		JLabel lbIcon = new JLabel(new ImageIcon(getClass().getResource("/icon/Bicycle_128.png")), JLabel.CENTER);

		// Component Status: trạng thái các component

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbStaff);
		buttonGroup.add(rdbAdmin);
		rdbStaff.setSelected(true);

		// layout

		setLayout(new BorderLayout());
		add(lbBikeStores, BorderLayout.PAGE_START);
		add(lbIcon, BorderLayout.LINE_START);

		JPanel jPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		jPanel.add(rdbStaff, gbc);

		gbc.gridx++;
		jPanel.add(rdbAdmin, gbc);

		gbc.gridy = 1;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.WEST;
		jPanel.add(lbUsername, gbc);

		gbc.gridy++;
		jPanel.add(lbPassword, gbc);

		gbc.gridy = 1;
		gbc.gridx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = 2;
		jPanel.add(txtUsername, gbc);

		gbc.gridy++;
		jPanel.add(password, gbc);

		gbc.gridy++;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.EAST;
		jPanel.add(btnSignUp, gbc);

		gbc.gridx++;
		jPanel.add(btnSignIn, gbc);

		add(jPanel, BorderLayout.CENTER);
		// Event
		ActionListener loginFrameController = new LoginFrameController(this);
		btnSignIn.addActionListener(loginFrameController);
		btnSignUp.addActionListener(loginFrameController);
	}

}
