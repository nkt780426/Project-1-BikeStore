package view.admin;

import javax.swing.table.DefaultTableModel;

import controller.admin.OrdersTableController;
import dao.OrdersDao;
import model.Orders;

@SuppressWarnings("serial")
public class OrdersTable extends TablePanel<Orders> {

	public OrdersTable(AdminFrameView adminFrameView) {
		super(adminFrameView);
		getBtnNew().setVisible(false);
		getBtnDelete().setVisible(false);
		initTable();
		loadDataToTable();
		setEvent();
	}

	@Override
	public void setEvent() {
		OrdersTableController orderTableController = new OrdersTableController(this, super.getAdminFrameView());
		super.getTxtFilter().getDocument().addDocumentListener(orderTableController);
		super.getTable().addMouseListener(orderTableController);
	}

	@Override
	public void initTable() {
		super.setTblModel(new DefaultTableModel());
		super.getTblModel().setColumnIdentifiers(new String[] { "Order ID", "Customer ID", "Order Status", "Order Date",
				"Shipped Date", "Store ID", "Staff ID" });
		super.getTable().setModel(super.getTblModel());
	}

	@Override
	public void loadDataToTable() {
		OrdersDao ordersDao = new OrdersDao();
		super.setData(ordersDao.selectAll());
		super.getTblModel().setRowCount(0);
		for (Orders order : super.getData()) {
			super.getTblModel().addRow(new Object[] { order.getOrderId(), order.getCustomerId(), order.getOrderStatus(),
					order.getOrderDate(), order.getShippedDate(), order.getStoreId(), order.getStaffId() });
		}
	}

}
