package controller.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import dao.CategoriesDao;
import help.DataValidator;
import model.Categories;
import view.admin.CategoryDialog;

public class CategoryDialogController implements ActionListener, FocusListener {
	private CategoryDialog categoryDialog;

	public CategoryDialogController(CategoryDialog categoryDialog) {
		this.categoryDialog = categoryDialog;
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent e) {
		categoryDialog.getTxtCategoryName().setText(categoryDialog.getTxtCategoryName().getText().trim());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == categoryDialog.getBtnBack()) {
			categoryDialog.dispose();
		}
		if (e.getSource() == categoryDialog.getBtnSave()) {
			ArrayList<JComponent> check = new ArrayList<>();
			check.add(categoryDialog.getTxtCategoryName());
			if (DataValidator.checkEmpty(categoryDialog, check)) {
				Categories category = new Categories();
				category.setCategoryName(categoryDialog.getTxtCategoryName().getText());
				CategoriesDao categoriesDao = new CategoriesDao();
				if (categoriesDao.insert(category) > 0) {
					categoryDialog.getAdminFrameView().getCategories().loadDataToTable();
					JOptionPane.showMessageDialog(categoryDialog, "Success!");
				} else {
					JOptionPane.showMessageDialog(categoryDialog, "Faild!");
				}
			} else {
				JOptionPane.showMessageDialog(categoryDialog, "Category Name can't be left mark!");
			}
		}
	}

}
