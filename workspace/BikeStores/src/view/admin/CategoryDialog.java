package view.admin;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.admin.CategoryDialogController;

@SuppressWarnings("serial")
public class CategoryDialog extends JDialog {
	private AdminFrameView adminFrameView;
	private JTextField txtCategoryName;
	private JButton btnBack;
	private JButton btnSave;

	public CategoryDialog(AdminFrameView adminFrameView) {
		super(adminFrameView, true);
		this.adminFrameView = adminFrameView;
		initComponent();
		setEvent();
		setTitle("Category View");
		pack();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void setEvent() {
		CategoryDialogController categoryDialogController = new CategoryDialogController(this);
		btnBack.addActionListener(categoryDialogController);
		btnSave.addActionListener(categoryDialogController);
		txtCategoryName.addFocusListener(categoryDialogController);
	}

	private void initComponent() {
		Font font = new Font("Arial", Font.PLAIN, 16);
		JLabel lbCategoryName = new JLabel("Category Name");
		lbCategoryName.setFont(font);
		txtCategoryName = new JTextField(10);
		txtCategoryName.setFont(font);
		btnBack = new JButton("Back", new ImageIcon(getClass().getResource("/icon/Go-back-icon16.png")));
		btnBack.setFont(font);
		btnSave = new JButton("Save", new ImageIcon(getClass().getResource("/icon/Save-icon16.png")));
		btnSave.setFont(font);
		// layout
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.weighty = 1;

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		add(lbCategoryName, gbc);

		gbc.gridy++;
		add(btnBack, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(txtCategoryName, gbc);

		gbc.gridy++;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.EAST;
		add(btnSave, gbc);

	}

	public static void main(String[] args) {
		new CategoryDialog(null);
	}

	public AdminFrameView getAdminFrameView() {
		return adminFrameView;
	}

	public JTextField getTxtCategoryName() {
		return txtCategoryName;
	}

	public JButton getBtnBack() {
		return btnBack;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

}
