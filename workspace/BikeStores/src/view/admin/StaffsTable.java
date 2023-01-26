package view.admin;

import javax.swing.table.DefaultTableModel;

import controller.admin.StaffsTableController;
import dao.StaffsDao;
import model.Staffs;

@SuppressWarnings("serial")
public class StaffsTable extends TablePanel<Staffs> {

	public StaffsTable(AdminFrameView adminFrameView) {
		super(adminFrameView);
		super.getBtnNew().setVisible(false);
		initTable();
		loadDataToTable();
		setEvent();
	}

	@Override
	public void setEvent() {
		StaffsTableController staffsTableController = new StaffsTableController(this, super.getAdminFrameView());
		super.getTxtFilter().getDocument().addDocumentListener(staffsTableController);
		super.getTable().addMouseListener(staffsTableController);
	}

	@Override
	public void initTable() {
		super.setTblModel(new DefaultTableModel());
		super.getTblModel().setColumnIdentifiers(new String[] { "Staff ID", "First name", "Last name", "Sex",
				"Birthday", "phone", "Active", "Store ID", "Manager ID" });
		super.getTable().setModel(super.getTblModel());
	}

	@Override
	public void loadDataToTable() {
		StaffsDao staffsDao = new StaffsDao();
		super.setData(staffsDao.selectAll());
		super.getTblModel().setRowCount(0);
		for (Staffs staff : super.getData()) {
			super.getTblModel().addRow(new Object[] { staff.getStaffId(), staff.getFirstName(), staff.getLastName(),
					staff.getSex(), staff.getBirthDay(), staff.getPhone(), staff.getActive(), staff.getStoreId(),
					staff.getManagerId() });
		}
	}

}
