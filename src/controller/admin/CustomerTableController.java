package controller.admin;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dao.CustomersDao;
import model.Customers;
import view.admin.AdminFrameView;
import view.admin.CustomerAdminDialog;
import view.admin.CustomersTable;

public class CustomerTableController implements DocumentListener, MouseListener {
	private AdminFrameView adminFrameView;
	private CustomersTable customersTable;

	public CustomerTableController(CustomersTable customersTable, AdminFrameView adminFrameView) {
		this.customersTable = customersTable;
		this.adminFrameView = adminFrameView;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		customersTable.sortAndFilter(customersTable.getTxtFilter().getText());
		customersTable.getTblModel().fireTableDataChanged();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		customersTable.sortAndFilter(customersTable.getTxtFilter().getText());
		customersTable.getTblModel().fireTableDataChanged();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		customersTable.sortAndFilter(customersTable.getTxtFilter().getText());
		customersTable.getTblModel().fireTableDataChanged();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			int row = customersTable.getTable().getSelectedRow();
			int customerId = Integer.valueOf(customersTable.getTable().getValueAt(row, 0).toString());
			Customers customer = new Customers();
			customer.setCustomerId(customerId);
			CustomersDao dao = new CustomersDao();
			if (dao.find(customer)) {
				new CustomerAdminDialog(adminFrameView, customer);
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
