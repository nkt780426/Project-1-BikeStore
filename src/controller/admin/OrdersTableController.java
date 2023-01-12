package controller.admin;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dao.OrdersDao;
import model.Orders;
import view.admin.AdminFrameView;
import view.admin.OrderDialogAdmin;
import view.admin.OrdersTable;

public class OrdersTableController implements DocumentListener, MouseListener {
	private AdminFrameView adminFrameView;
	private OrdersTable ordersTable;

	public OrdersTableController(OrdersTable ordersTable, AdminFrameView adminFrameView) {
		this.ordersTable = ordersTable;
		this.adminFrameView = adminFrameView;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		ordersTable.sortAndFilter(ordersTable.getTxtFilter().getText());
		ordersTable.getTblModel().fireTableDataChanged();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		ordersTable.sortAndFilter(ordersTable.getTxtFilter().getText());
		ordersTable.getTblModel().fireTableDataChanged();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		ordersTable.sortAndFilter(ordersTable.getTxtFilter().getText());
		ordersTable.getTblModel().fireTableDataChanged();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			int row = ordersTable.getTable().getSelectedRow();
			int orderId = Integer.valueOf(ordersTable.getTable().getValueAt(row, 0).toString());
			Orders order = new Orders();
			order.setOrderId(orderId);
			OrdersDao dao = new OrdersDao();
			if (dao.find(order)) {
				new OrderDialogAdmin(adminFrameView, order);
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
