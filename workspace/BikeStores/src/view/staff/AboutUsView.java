package view.staff;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class AboutUsView extends JDialog {
	public AboutUsView(JFrame frame) {
		super(frame, "about us", true);
		initComponent();
		setSize(300, 300);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void initComponent() {
		JLabel lbAboutUs = new JLabel("ABOUT US");
		lbAboutUs.setFont(new Font("Arial", Font.BOLD, 20));
		JLabel ten = new JLabel("Ten: Vo Nguyen Hoang");
		JLabel mssv = new JLabel("MSSV: 20204833");
		JLabel khoa = new JLabel("Khoa: 65");
		// setlayout
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);

		gbc.gridx = 0;
		gbc.gridy = 0;
		add(lbAboutUs, gbc);

		gbc.gridy++;
		gbc.anchor = GridBagConstraints.WEST;
		add(ten, gbc);

		gbc.gridy++;
		add(mssv, gbc);

		gbc.gridy++;
		add(khoa, gbc);

	}
}
