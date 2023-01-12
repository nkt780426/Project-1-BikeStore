package controller.staff;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dao.OrdersDao;
import model.Orders;
import view.staff.HistoryPanel;
import view.staff.OrderDialogCustomer;

public class HistoryPanelController implements DocumentListener, MouseListener {
	private HistoryPanel historyPanel;

	public HistoryPanelController(HistoryPanel historyPanel) {
		this.historyPanel = historyPanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			int row = historyPanel.getTable().getSelectedRow();
			int orderId = Integer.valueOf(historyPanel.getTable().getValueAt(row, 0).toString());
			Orders order = new Orders();
			order.setOrderId(orderId);
			OrdersDao dao = new OrdersDao();
			if (dao.find(order)) {
				new OrderDialogCustomer(historyPanel.getStaffFrameView(), order);
			} else {
				JOptionPane.showMessageDialog(historyPanel.getStaffFrameView(), "Error!");
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

	@Override
	public void insertUpdate(DocumentEvent e) {
		historyPanel.sortAndFilter(historyPanel.getTxtFilter().getText());
		historyPanel.getTblModel().fireTableDataChanged();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		historyPanel.sortAndFilter(historyPanel.getTxtFilter().getText());
		historyPanel.getTblModel().fireTableDataChanged();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		historyPanel.sortAndFilter(historyPanel.getTxtFilter().getText());
		historyPanel.getTblModel().fireTableDataChanged();
	}

}
