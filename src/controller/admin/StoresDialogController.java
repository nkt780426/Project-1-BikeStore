package controller.admin;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;

import dao.StoresDao;
import help.DataValidator;
import help.ImageAccept;
import model.Stores;
import view.admin.StoreDialog;

public class StoresDialogController implements ActionListener, FocusListener {
	private StoreDialog storeDialog;

	public StoresDialogController(StoreDialog storeDialog) {
		this.storeDialog = storeDialog;
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == storeDialog.getBtnSave()) {
			DataValidator.checkPhone(storeDialog, storeDialog.getTxtPhone());
			DataValidator.checkEmail(storeDialog, storeDialog.getTxtEmail());
			DataValidator.checkEmail(storeDialog, storeDialog.getTxtAddress());
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == storeDialog.getTxtStoreName()) {
			storeDialog.getTxtStoreName().setText(storeDialog.getTxtStoreName().getText().trim());
		}
		if (e.getSource() == storeDialog.getTxtPhone()) {
			storeDialog.getTxtPhone().setText(storeDialog.getTxtPhone().getText().trim());
			if (storeDialog.getTxtPhone().getText().length() != 0) {
				DataValidator.checkPhone(storeDialog, storeDialog.getTxtPhone());
			} else {
				storeDialog.getTxtPhone().setBackground(Color.WHITE);
			}
		}
		if (e.getSource() == storeDialog.getTxtEmail()) {
			storeDialog.getTxtEmail().setText(storeDialog.getTxtEmail().getText().trim());
			if (storeDialog.getTxtEmail().getText().length() != 0) {
				DataValidator.checkEmail(storeDialog, storeDialog.getTxtEmail());
			} else {
				storeDialog.getTxtEmail().setBackground(Color.WHITE);
			}
		}
		if (e.getSource() == storeDialog.getTxtAddress()) {
			storeDialog.getTxtAddress().setText(storeDialog.getTxtAddress().getText().trim());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == storeDialog.getBtnOpen()) {
			JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView());
			fileChooser.setFileFilter(new FileFilter() {

				@Override
				public String getDescription() {
					return "Image File (*.jpg)";
				}

				@Override
				public boolean accept(File f) {
					if (f.isDirectory()) {
						return true;
					} else {
						return f.getName().toLowerCase().endsWith(".jpg");
					}
				}
			});
			if (fileChooser.showOpenDialog(storeDialog) == JFileChooser.CANCEL_OPTION) {
				return;
			}
			File file = fileChooser.getSelectedFile();
			ImageIcon icon = new ImageIcon(file.getPath());
			Image img = ImageAccept.resize(icon.getImage(), 100, 100);
			ImageIcon resizeIcon = new ImageIcon(img);
			storeDialog.getLbIcon().setIcon(resizeIcon);
			try {
				storeDialog.setStoreIcon(ImageAccept.toByteArray(img, "jpg"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource() == storeDialog.getBtnBack()) {
			storeDialog.dispose();
		}
		if (e.getSource() == storeDialog.getBtnSave()) {
			ArrayList<JComponent> check = new ArrayList<JComponent>();
			check.add(storeDialog.getTxtStoreName());
			check.add(storeDialog.getTxtPhone());
			check.add(storeDialog.getTxtEmail());
			check.add(storeDialog.getTxtAddress());
			if (DataValidator.checkEmpty(storeDialog, check)) {
				Stores store = new Stores();
				store.setStoreName(storeDialog.getTxtStoreName().getText());
				store.setPhone(storeDialog.getTxtPhone().getText());
				store.setEmail(storeDialog.getTxtEmail().getText());
				store.setAddress(storeDialog.getTxtAddress().getText());
				StoresDao dao = new StoresDao();
				if (storeDialog.getStore() != null) {
					store.setStoreId(storeDialog.getStore().getStoreId());
					if (dao.update(store) > 0) {
						storeDialog.getAdminFrameView().getStores().loadDataToTable();
						JOptionPane.showMessageDialog(storeDialog, "Success!");
					} else {
						JOptionPane.showMessageDialog(storeDialog, "Falid!");
					}
				} else {
					if (dao.insert(store) > 0) {
						storeDialog.getTxtStoreId().setText(String.valueOf(store.getStoreId()));
						storeDialog.getAdminFrameView().getStores().loadDataToTable();
						JOptionPane.showMessageDialog(storeDialog, "Success!");
					} else {
						JOptionPane.showMessageDialog(storeDialog, "Falid!");
					}
				}
			} else {
				JOptionPane.showMessageDialog(storeDialog, "Text can't be left mark!");
			}
		}
	}

}
