package controller.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dao.StoresDao;
import model.Stores;
import view.admin.AdminFrameView;
import view.admin.StoreDialog;
import view.admin.StoresTable;

public class StoresTableController implements ActionListener, DocumentListener, MouseListener {
	private AdminFrameView adminFrameView;
	private StoresTable storesTable;

	public StoresTableController(StoresTable storesTable, AdminFrameView adminFrameView) {
		this.storesTable = storesTable;
		this.adminFrameView = adminFrameView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == storesTable.getBtnNew()) {
			new StoreDialog(adminFrameView, null);
		}
		if (e.getSource() == storesTable.getBtnDelete()) {
			int row = storesTable.getTable().getSelectedRow();
			int storeId = Integer.valueOf(storesTable.getTable().getValueAt(row, 0).toString());
			Stores store = new Stores();
			store.setStoreId(storeId);
			StoresDao dao = new StoresDao();
			if (dao.delete(store) > 0) {
				storesTable.loadDataToTable();
				JOptionPane.showMessageDialog(adminFrameView, "Success!");
			} else {
				JOptionPane.showMessageDialog(adminFrameView, "Faild!");
			}
		}
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		storesTable.sortAndFilter(storesTable.getTxtFilter().getText());
		storesTable.getTblModel().fireTableDataChanged();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		storesTable.sortAndFilter(storesTable.getTxtFilter().getText());
		storesTable.getTblModel().fireTableDataChanged();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		storesTable.sortAndFilter(storesTable.getTxtFilter().getText());
		storesTable.getTblModel().fireTableDataChanged();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			int row = storesTable.getTable().getSelectedRow();
			int storeId = Integer.valueOf(storesTable.getTable().getValueAt(row, 0).toString());
			Stores store = new Stores();
			store.setStoreId(storeId);
			StoresDao dao = new StoresDao();
			if (dao.findById(store)) {
				new StoreDialog(adminFrameView, store);
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
