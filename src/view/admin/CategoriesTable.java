package view.admin;

import javax.swing.table.DefaultTableModel;

import controller.admin.CategoriesTableController;
import dao.CategoriesDao;
import model.Categories;

@SuppressWarnings("serial")
public class CategoriesTable extends TablePanel<Categories> {
	public CategoriesTable(AdminFrameView adminFrameView) {
		super(adminFrameView);
		initTable();
		loadDataToTable();
		setEvent();
	}

	@Override
	public void setEvent() {
		CategoriesTableController categoriesTableController = new CategoriesTableController(this,
				super.getAdminFrameView());
		super.getBtnNew().addActionListener(categoriesTableController);
		super.getBtnDelete().addActionListener(categoriesTableController);
		super.getTxtFilter().getDocument().addDocumentListener(categoriesTableController);
	}

	@Override
	public void initTable() {
		super.setTblModel(new DefaultTableModel());
		super.getTblModel().setColumnIdentifiers(new String[] { "Category Id", "Category Name" });
		super.getTable().setModel(super.getTblModel());

	}

	@Override
	public void loadDataToTable() {
		CategoriesDao categoriesDao = new CategoriesDao();
		super.setData(categoriesDao.selectAll());
		super.getTblModel().setRowCount(0);
		for (Categories categories : super.getData()) {
			super.getTblModel().addRow(new Object[] { categories.getCategoryId(), categories.getCategoryName() });
		}

	}

}
