package view.admin;

import javax.swing.table.DefaultTableModel;

import controller.admin.StoresTableController;
import dao.StoresDao;
import model.Stores;

@SuppressWarnings("serial")
public class StoresTable extends TablePanel<Stores> {

	public StoresTable(AdminFrameView adminFrameView) {
		super(adminFrameView);
		initTable();
		loadDataToTable();
		setEvent();
	}

	public void setEvent() {
		StoresTableController storesTableController = new StoresTableController(this, super.getAdminFrameView());
		super.getBtnNew().addActionListener(storesTableController);
		super.getBtnDelete().addActionListener(storesTableController);
		super.getTxtFilter().getDocument().addDocumentListener(storesTableController);
		super.getTable().addMouseListener(storesTableController);
	}

	public void initTable() {
		super.setTblModel(new DefaultTableModel());
		super.getTblModel()
				.setColumnIdentifiers(new String[] { "Store ID", "Store Name", "Phone", "Email", "Address" });
		super.getTable().setModel(super.getTblModel());
	}

	public void loadDataToTable() {
		StoresDao storesDao = new StoresDao();
		super.setData(storesDao.selectAll());
		super.getTblModel().setRowCount(0);
		for (Stores store : super.getData()) {
			super.getTblModel().addRow(new Object[] { store.getStoreId(), store.getStoreName(), store.getPhone(),
					store.getEmail(), store.getAddress() });
		}
	}

}
