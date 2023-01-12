package view.admin;

import javax.swing.table.DefaultTableModel;

import controller.admin.BrandsTableController;
import dao.BrandsDao;
import model.Brands;

@SuppressWarnings("serial")
public class BrandsTable extends TablePanel<Brands> {

	public BrandsTable(AdminFrameView adminFrameView) {
		super(adminFrameView);
		initTable();
		loadDataToTable();
		setEvent();
	}

	@Override
	public void setEvent() {
		BrandsTableController brandsTableController = new BrandsTableController(this, super.getAdminFrameView());
		super.getBtnNew().addActionListener(brandsTableController);
		super.getBtnDelete().addActionListener(brandsTableController);
		super.getTxtFilter().getDocument().addDocumentListener(brandsTableController);
		super.getTable().addMouseListener(brandsTableController);
	}

	@Override
	public void initTable() {
		super.setTblModel(new DefaultTableModel());
		super.getTblModel().setColumnIdentifiers(new String[] { "Brand Id", "Brand Name" });
		super.getTable().setModel(super.getTblModel());

	}

	@Override
	public void loadDataToTable() {
		BrandsDao brandsDao = new BrandsDao();
		super.setData(brandsDao.selectAll());
		super.getTblModel().setRowCount(0);
		for (Brands brand : super.getData()) {
			super.getTblModel().addRow(new Object[] { brand.getBrandId(), brand.getBrandName() });
		}

	}

}
