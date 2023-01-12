package controller.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dao.BrandsDao;
import model.Brands;
import view.admin.AdminFrameView;
import view.admin.BrandDialog;
import view.admin.BrandsTable;

public class BrandsTableController implements ActionListener, DocumentListener, MouseListener {
	private AdminFrameView adminFrameView;
	private BrandsTable brandsTable;

	public BrandsTableController(BrandsTable brandsTable, AdminFrameView adminFrameView) {
		this.brandsTable = brandsTable;
		this.adminFrameView = adminFrameView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == brandsTable.getBtnNew()) {
			new BrandDialog(adminFrameView, null);
		}
		if (e.getSource() == brandsTable.getBtnDelete()) {
			int row = brandsTable.getTable().getSelectedRow();
			int brandId = Integer.valueOf(brandsTable.getTable().getValueAt(row, 0).toString());
			Brands brand = new Brands();
			brand.setBrandId(brandId);
			BrandsDao dao = new BrandsDao();
			if (dao.delete(brand) > 0) {
				brandsTable.loadDataToTable();
				JOptionPane.showMessageDialog(adminFrameView, "Success!");
			} else {
				JOptionPane.showMessageDialog(adminFrameView, "Faild!");
			}
		}
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		brandsTable.sortAndFilter(brandsTable.getTxtFilter().getText());
		brandsTable.getTblModel().fireTableDataChanged();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		brandsTable.sortAndFilter(brandsTable.getTxtFilter().getText());
		brandsTable.getTblModel().fireTableDataChanged();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		brandsTable.sortAndFilter(brandsTable.getTxtFilter().getText());
		brandsTable.getTblModel().fireTableDataChanged();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			int row = brandsTable.getTable().getSelectedRow();
			int brandId = Integer.valueOf(brandsTable.getTable().getValueAt(row, 0).toString());
			Brands brand = new Brands();
			brand.setBrandId(brandId);
			BrandsDao dao = new BrandsDao();
			if (dao.findById(brand)) {
				new BrandDialog(adminFrameView, brand);
			} else {
				JOptionPane.showMessageDialog(adminFrameView, "Error!");
			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
