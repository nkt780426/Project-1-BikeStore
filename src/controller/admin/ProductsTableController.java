package controller.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dao.ProductsDao;
import model.Products;
import view.admin.AdminFrameView;
import view.admin.ProductDialog;
import view.admin.ProductsTable;

public class ProductsTableController implements ActionListener, DocumentListener, MouseListener {
	private AdminFrameView adminFrameView;
	private ProductsTable productsTable;

	public ProductsTableController(ProductsTable productsTable, AdminFrameView adminFrameView) {
		this.productsTable = productsTable;
		this.adminFrameView = adminFrameView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == productsTable.getBtnNew()) {
			new ProductDialog(adminFrameView, null);
		}
		if (e.getSource() == productsTable.getBtnDelete()) {
			int row = productsTable.getTable().getSelectedRow();
			int productId = Integer.valueOf(productsTable.getTable().getValueAt(row, 0).toString());
			Products product = new Products();
			product.setProductId(productId);
			ProductsDao dao = new ProductsDao();
			if (dao.delete(product) > 0) {
				productsTable.loadDataToTable();
				JOptionPane.showMessageDialog(adminFrameView, "Success!");
			} else {
				JOptionPane.showMessageDialog(adminFrameView, "Faild!");
			}
		}
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		productsTable.sortAndFilter(productsTable.getTxtFilter().getText());
		productsTable.getTblModel().fireTableDataChanged();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		productsTable.sortAndFilter(productsTable.getTxtFilter().getText());
		productsTable.getTblModel().fireTableDataChanged();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		productsTable.sortAndFilter(productsTable.getTxtFilter().getText());
		productsTable.getTblModel().fireTableDataChanged();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			int row = productsTable.getTable().getSelectedRow();
			int productId = Integer.valueOf(productsTable.getTable().getValueAt(row, 0).toString());
			Products product = new Products();
			product.setProductId(productId);
			ProductsDao dao = new ProductsDao();
			if (dao.findById(product)) {
				new ProductDialog(adminFrameView, product);
			} else {
				JOptionPane.showMessageDialog(adminFrameView, "Error!");
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
