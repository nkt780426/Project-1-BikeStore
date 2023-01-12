package view.admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controller.admin.StoresDialogController;
import help.ImageAccept;
import model.Stores;

@SuppressWarnings("serial")
public class StoreDialog extends JDialog {
	private AdminFrameView adminFrameView;
	private Stores store;
	private byte[] storeIcon;

	private JLabel lbIcon;
	private JTextField txtStoreId;
	private JTextField txtStoreName;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JTextField txtAddress;
	private JButton btnOpen;
	private JButton btnBack;
	private JButton btnSave;

	public StoreDialog(AdminFrameView adminFrameView, Stores store) {
		super(adminFrameView, true);
		this.adminFrameView = adminFrameView;
		this.store = store;
		initComponent();
		if (store != null) {
			initInformation();
		}
		pack();
		setTitle("Store View");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setEvent();
		setVisible(true);
	}

	private void setEvent() {
		StoresDialogController storesDialogController = new StoresDialogController(this);
		txtStoreName.addFocusListener(storesDialogController);
		txtPhone.addFocusListener(storesDialogController);
		txtEmail.addFocusListener(storesDialogController);
		txtAddress.addFocusListener(storesDialogController);
		btnOpen.addActionListener(storesDialogController);
		btnBack.addActionListener(storesDialogController);
		btnSave.addActionListener(storesDialogController);
	}

	private void initInformation() {
		txtStoreId.setText(String.valueOf(store.getStoreId()));
		txtStoreName.setText(store.getStoreName());
		if (store.getStoreIcon() != null) {
			storeIcon = store.getStoreIcon();
			try {
				Image img = ImageAccept.createImageFromByteArray(storeIcon, "jpg");
				Image resize = ImageAccept.resize(img, 100, 100);
				ImageIcon icon = new ImageIcon(resize);
				lbIcon.setIcon(icon);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		txtPhone.setText(store.getPhone());
		txtEmail.setText(store.getEmail());
		txtAddress.setText(store.getAddress());
	}

	private void initComponent() {
		// component
		// label
		Font font = new Font("Arial", Font.PLAIN, 16);
		JLabel lbStoreId = new JLabel("StoreID");
		lbStoreId.setFont(font);
		JLabel lbStoreName = new JLabel("StoreName");
		lbStoreName.setFont(font);
		JLabel lbPhone = new JLabel("Phone");
		lbPhone.setFont(font);
		JLabel lbEmail = new JLabel("Email");
		lbEmail.setFont(font);
		JLabel lbAddress = new JLabel("Address");
		lbAddress.setFont(font);
		lbIcon = new JLabel(new ImageIcon(getClass().getResource("/icon/store-icon72.png")));
		lbIcon.setBorder(new LineBorder(Color.CYAN));
		lbIcon.setPreferredSize(new Dimension(72, 72));
		// textfield
		txtStoreId = new JTextField(10);
		txtStoreId.setFont(font);
		txtStoreName = new JTextField(10);
		txtStoreName.setFont(font);
		txtPhone = new JTextField(10);
		txtPhone.setFont(font);
		txtEmail = new JTextField(11);
		txtEmail.setFont(font);
		txtAddress = new JTextField(10);
		txtAddress.setFont(font);
		// button
		btnOpen = new JButton("Open", new ImageIcon(getClass().getResource("/icon/open-file-icon16.png")));
		btnOpen.setFont(font);
		btnBack = new JButton("Back", new ImageIcon(getClass().getResource("/icon/Go-back-icon16.png")));
		btnBack.setFont(font);
		btnSave = new JButton("Save", new ImageIcon(getClass().getResource("/icon/Save-icon16.png")));
		btnSave.setFont(font);
		// table

		// component status
		txtStoreId.setEnabled(false);
		// layout
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weighty = 1;
		gbc.insets = new Insets(5, 5, 5, 5);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		add(lbStoreId, gbc);

		gbc.gridy++;
		add(lbStoreName, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		add(txtStoreId, gbc);

		gbc.gridy++;
		add(txtStoreName, gbc);

		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridheight = 2;
		gbc.gridwidth = 2;
		gbc.weightx = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		add(lbIcon, gbc);

		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridheight = 1;
		add(btnOpen, gbc);

		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(lbPhone, gbc);

		gbc.gridx += 2;
		add(lbEmail, gbc);

		gbc.gridx -= 2;
		gbc.gridy++;
		add(lbAddress, gbc);

		gbc.gridy++;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		add(btnBack, gbc);

		gbc.gridx = 3;
		gbc.anchor = GridBagConstraints.EAST;
		add(btnSave, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(txtPhone, gbc);

		gbc.gridx = 3;
		gbc.weightx = 1;
		add(txtEmail, gbc);

		gbc.gridwidth = 3;
		gbc.gridy++;
		gbc.gridx = 1;
		add(txtAddress, gbc);
	}

	public Stores getStore() {
		return store;
	}

	public void setStore(Stores store) {
		this.store = store;
	}

	public byte[] getStoreIcon() {
		return storeIcon;
	}

	public void setStoreIcon(byte[] storeIcon) {
		this.storeIcon = storeIcon;
	}

	public AdminFrameView getAdminFrameView() {
		return adminFrameView;
	}

	public JLabel getLbIcon() {
		return lbIcon;
	}

	public JTextField getTxtStoreId() {
		return txtStoreId;
	}

	public JTextField getTxtStoreName() {
		return txtStoreName;
	}

	public JTextField getTxtPhone() {
		return txtPhone;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public JTextField getTxtAddress() {
		return txtAddress;
	}

	public JButton getBtnOpen() {
		return btnOpen;
	}

	public JButton getBtnBack() {
		return btnBack;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

}
