package view.admin;

import javax.swing.table.DefaultTableModel;

import controller.admin.CustomerTableController;
import dao.CustomersDao;
import model.Customers;

@SuppressWarnings("serial")
public class CustomersTable extends TablePanel<Customers> {

	public CustomersTable(AdminFrameView adminFrameView) {
		super(adminFrameView);
		getBtnNew().setVisible(false);
		getBtnDelete().setVisible(false);
		initTable();
		loadDataToTable();
		setEvent();
	}

	@Override
	public void setEvent() {
		CustomerTableController customerTableController = new CustomerTableController(this, getAdminFrameView());
		super.getTxtFilter().getDocument().addDocumentListener(customerTableController);
		super.getTable().addMouseListener(customerTableController);
	}

	@Override
	public void initTable() {
		super.setTblModel(new DefaultTableModel());
		super.getTblModel().setColumnIdentifiers(new String[] { "Customer ID", "First name", "Last name", "Sex",
				"Birthday", "Phone", "Email", "Address" });
		super.getTable().setModel(super.getTblModel());

	}

	@Override
	public void loadDataToTable() {
		CustomersDao customersDao = new CustomersDao();
		super.setData(customersDao.selectAll());
		super.getTblModel().setRowCount(0);
		for (Customers customer : super.getData()) {
			super.getTblModel().addRow(new Object[] { customer.getCustomerId(), customer.getFirstName(),
					customer.getLastName(), customer.getSex(), customer.getBirthDay(), customer.getPhone(),
					customer.getEmail(), customer.getAddress(), });
		}

	}

}
