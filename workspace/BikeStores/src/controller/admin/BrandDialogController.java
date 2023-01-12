package controller.admin;

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

import dao.BrandsDao;
import help.DataValidator;
import help.ImageAccept;
import model.Brands;
import view.admin.BrandDialog;

public class BrandDialogController implements ActionListener, FocusListener {
	private BrandDialog brandDialog;

	public BrandDialogController(BrandDialog brandDialog) {
		this.brandDialog = brandDialog;
	}

	@Override
	public void focusGained(FocusEvent e) {
		brandDialog.getTxtBrandName().setText(brandDialog.getTxtBrandName().getText().trim());
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == brandDialog.getTxtBrandName()) {
			brandDialog.getTxtBrandName().setText(brandDialog.getTxtBrandName().getText().trim());
		}
		if (e.getSource() == brandDialog.getAtxtDescribe()) {
			brandDialog.getAtxtDescribe().setText(brandDialog.getAtxtDescribe().getText().trim());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == brandDialog.getBtnOpen()) {
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
			if (fileChooser.showOpenDialog(brandDialog) == JFileChooser.CANCEL_OPTION) {
				return;
			}
			File file = fileChooser.getSelectedFile();
			ImageIcon icon = new ImageIcon(file.getPath());
			Image img = ImageAccept.resize(icon.getImage(), 100, 100);
			ImageIcon resizeIcon = new ImageIcon(img);
			brandDialog.getLbIcon().setIcon(resizeIcon);
			try {
				brandDialog.setBrandIcon(ImageAccept.toByteArray(img, "jpg"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource() == brandDialog.getBtnBack()) {
			brandDialog.dispose();
		}
		if (e.getSource() == brandDialog.getBtnSave()) {
			ArrayList<JComponent> check = new ArrayList<>();
			check.add(brandDialog.getTxtBrandName());
			if (DataValidator.checkEmpty(brandDialog, check)) {
				Brands brand = new Brands();
				brand.setBrandName(brandDialog.getTxtBrandName().getText());
				brand.setBrandIcon(brandDialog.getBrandIcon());
				BrandsDao brandsDao = new BrandsDao();
				if (brandDialog.getBrand() == null) {
					if (brandsDao.insert(brand) > 0) {
						JOptionPane.showMessageDialog(brandDialog, "Success!");
						brandDialog.dispose();
					} else {
						JOptionPane.showMessageDialog(brandDialog, "Faild!");
					}
				} else {
					brand.setBrandId(brandDialog.getBrand().getBrandId());
					if (brandsDao.update(brand) > 0) {
						brandDialog.getAdminFrameView().getBrands().loadDataToTable();
						JOptionPane.showMessageDialog(brandDialog, "Success!");
					} else {
						JOptionPane.showMessageDialog(brandDialog, "Falid!");
					}
				}
			} else {
				JOptionPane.showMessageDialog(brandDialog, "Brand Name can't be left mark!");
			}
		}
	}

}
