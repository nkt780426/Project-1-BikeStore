package controller.admin;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;

import dao.BrandsDao;
import dao.CategoriesDao;
import dao.ProductsDao;
import dao.StocksDao;
import help.DataValidator;
import help.ImageAccept;
import model.Brands;
import model.Categories;
import model.Products;
import model.Stocks;
import view.admin.ProductDialog;

public class ProductsDialogControler implements ActionListener, FocusListener {
	private ProductDialog productDialog;

	public ProductsDialogControler(ProductDialog productDialog) {
		this.productDialog = productDialog;
	}

	// lấy thông tin từ table
	// int row = table.gếtlectedRow(); int column = table.getSelectedColumn;
	// ObjectType o = (ObjectType)target.getValueAt(row,column);

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == productDialog.getBtnOpen()) {
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
			if (fileChooser.showOpenDialog(productDialog) == JFileChooser.CANCEL_OPTION) {
				return;
			}
			File file = fileChooser.getSelectedFile();
			ImageIcon icon = new ImageIcon(file.getPath());
			Image img = ImageAccept.resize(icon.getImage(), 100, 100);
			ImageIcon resizeIcon = new ImageIcon(img);
			productDialog.getLbIcon().setIcon(resizeIcon);
			try {
				productDialog.setProductIcon(ImageAccept.toByteArray(img, "jpg"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource() == productDialog.getBtnOk()) {
			if (productDialog.getCbbStoreId().getSelectedItem() != null
					&& productDialog.getTxtQuantity().getText().length() > 0) {
				Integer storeId = Integer.valueOf(productDialog.getCbbStoreId().getSelectedItem().toString());
				int quantity = Integer.valueOf(productDialog.getTxtQuantity().getText());
				Stocks stock = new Stocks(storeId, productDialog.getProduct().getProductId(), quantity);
				StocksDao dao = new StocksDao();
				if (dao.find(stock)) {
					if (quantity < 0)
						JOptionPane.showMessageDialog(productDialog, "Quantity must be greater than 0!");
					if (quantity == 0) {
						if (dao.find(stock)) {
							dao.delete(stock);
							productDialog.loadDataToTable();
							JOptionPane.showMessageDialog(productDialog,
									"Success removal from store has id = " + storeId);
						} else {
							JOptionPane.showMessageDialog(productDialog,
									"Can't remove product from store has id = " + storeId);
						}
					}
					if (quantity > 0) {
						if (dao.update(stock)) {
							productDialog.loadDataToTable();
							JOptionPane.showMessageDialog(productDialog, "Update success!");
						} else {
							JOptionPane.showMessageDialog(productDialog, "Update faild!");
						}
					}
				} else {
					if (quantity <= 0)
						JOptionPane.showMessageDialog(productDialog, "Quantity must be greater than 0!");
					if (quantity > 0) {
						if (dao.insert(stock)) {
							productDialog.loadDataToTable();
							JOptionPane.showMessageDialog(productDialog, "Insert success!");
						} else {
							JOptionPane.showMessageDialog(productDialog, "Insert faild!");
						}
					}
				}
			}
		}

		if (e.getSource() == productDialog.getBtnBack1()) {
			productDialog.dispose();
		}
		if (e.getSource() == productDialog.getBtnBack2()) {
			productDialog.dispose();
		}
		if (e.getSource() == productDialog.getBtnSave()) {

			// kiem tra component trống
			ArrayList<JComponent> check = new ArrayList<JComponent>();
			check.add(productDialog.getTxtProductName());
			check.add(productDialog.getTxtModelYear());
			check.add(productDialog.getTxtListPrice());
			if (DataValidator.checkEmpty(productDialog, check)) {
				// tạo instance
				Products product = new Products();
				product.setProductName(productDialog.getTxtProductName().getText());
				BrandsDao brandsDao = new BrandsDao();
				Brands brand = new Brands();
				brand.setBrandName(productDialog.getCbbBrand().getSelectedItem().toString());
				if (brandsDao.findByName(brand)) {
					product.setBrandId(brand.getBrandId());
				}
				CategoriesDao categoriesDao = new CategoriesDao();
				Categories category = new Categories();
				category.setCategoryName(productDialog.getCbbCategory().getSelectedItem().toString());
				if (categoriesDao.findByName(category)) {
					product.setCategoryId(category.getCategoryId());
				}
				product.setModelYear(Integer.valueOf(productDialog.getTxtModelYear().getText()));
				product.setListPrice(new BigDecimal(productDialog.getTxtListPrice().getText()));
				product.setProductIcon(productDialog.getProductIcon());
				// lưu product
				ProductsDao dao = new ProductsDao();
				if (productDialog.getProduct() != null) {
					product.setProductId(productDialog.getProduct().getProductId());
					productDialog.getTxtProductId().setText(String.valueOf(product.getProductId()));
					if (dao.update(product) > 0) {
						productDialog.getAdminFrameView().getProducts().loadDataToTable();
						JOptionPane.showMessageDialog(productDialog, "Success!");
					} else {
						JOptionPane.showMessageDialog(productDialog, "Falid!");
					}
				} else {
					if (dao.insert(product) > 0) {
						JOptionPane.showMessageDialog(productDialog, "Success!");
						productDialog.setProduct(product);
						productDialog.getTxtProductId().setText(String.valueOf(product.getProductId()));
						productDialog.getTxtProductId().setText(String.valueOf(product.getProductId()));
						productDialog.getLbStocks().setVisible(true);
						productDialog.getScrollPane().setVisible(true);
						productDialog.getLbStoreId().setVisible(true);
						productDialog.getCbbStoreId().setVisible(true);
						productDialog.getLbQuantity().setVisible(true);
						productDialog.getTxtQuantity().setVisible(true);
						productDialog.getBtnBack2().setVisible(true);
						productDialog.getBtnOk().setVisible(true);
						productDialog.getBtnBack1().setVisible(false);
						productDialog.pack();
						productDialog.getAdminFrameView().getProducts().loadDataToTable();
						;
					} else {
						JOptionPane.showMessageDialog(productDialog, "Falid!");
					}
				}
			} else {
				JOptionPane.showMessageDialog(productDialog, "Text can't be left mark!");
			}
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == productDialog.getBtnSave()) {
			DataValidator.checkYear(productDialog, productDialog.getTxtModelYear());
			DataValidator.checkPrice(productDialog, productDialog.getTxtListPrice());
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == productDialog.getTxtProductName()) {
			productDialog.getTxtProductName().setText(productDialog.getTxtProductName().getText().trim());
		}
		if (e.getSource() == productDialog.getTxtModelYear()) {
			JTextField txtModelYear = productDialog.getTxtModelYear();
			txtModelYear.setText(txtModelYear.getText().trim());
			if (txtModelYear.getText().length() > 0) {
				DataValidator.checkYear(productDialog, txtModelYear);
			} else {
				txtModelYear.setBackground(Color.WHITE);
			}
		}
		if (e.getSource() == productDialog.getTxtListPrice()) {
			JTextField txtListPrice = productDialog.getTxtListPrice();
			txtListPrice.setText(txtListPrice.getText().trim());
			if (txtListPrice.getText().length() > 0) {
				DataValidator.checkPrice(productDialog, txtListPrice);
			} else {
				txtListPrice.setBackground(Color.WHITE);
			}
		}
	}

}
