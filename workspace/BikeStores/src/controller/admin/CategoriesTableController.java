package controller.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dao.CategoriesDao;
import model.Categories;
import view.admin.AdminFrameView;
import view.admin.CategoriesTable;
import view.admin.CategoryDialog;

public class CategoriesTableController implements ActionListener, DocumentListener {
	private AdminFrameView adminFrameView;
	private CategoriesTable categoriesTable;

	public CategoriesTableController(CategoriesTable categoriesTable, AdminFrameView adminFrameView) {
		this.categoriesTable = categoriesTable;
		this.adminFrameView = adminFrameView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == categoriesTable.getBtnNew()) {
			new CategoryDialog(adminFrameView);
		}
		if (e.getSource() == categoriesTable.getBtnDelete()) {
			int row = categoriesTable.getTable().getSelectedRow();
			int categoryId = Integer.valueOf(categoriesTable.getTable().getValueAt(row, 0).toString());
			Categories category = new Categories();
			category.setCategoryId(categoryId);
			CategoriesDao dao = new CategoriesDao();
			if (dao.delete(category) > 0) {
				categoriesTable.loadDataToTable();
				JOptionPane.showMessageDialog(adminFrameView, "Success!");
			} else {
				JOptionPane.showMessageDialog(adminFrameView, "Faild!");
			}
		}
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		categoriesTable.sortAndFilter(categoriesTable.getTxtFilter().getText());
		categoriesTable.getTblModel().fireTableDataChanged();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		categoriesTable.sortAndFilter(categoriesTable.getTxtFilter().getText());
		categoriesTable.getTblModel().fireTableDataChanged();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		categoriesTable.sortAndFilter(categoriesTable.getTxtFilter().getText());
		categoriesTable.getTblModel().fireTableDataChanged();
	}

}
