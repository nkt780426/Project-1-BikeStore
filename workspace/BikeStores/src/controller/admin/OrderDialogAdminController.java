package controller.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dao.OrdersDao;
import dao.OrdersItemsDao;
import dao.StocksDao;
import model.OrderItems;
import model.Orders;
import model.Stocks;
import view.admin.OrderDialogAdmin;

public class OrderDialogAdminController implements ActionListener {
	private OrderDialogAdmin orderDialogAdmin;

	public OrderDialogAdminController(OrderDialogAdmin orderDialogAdmin) {
		this.orderDialogAdmin = orderDialogAdmin;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == orderDialogAdmin.getBtnBack()) {
			orderDialogAdmin.dispose();
		}
		if (e.getSource() == orderDialogAdmin.getBtnSave()) {
			Orders order = orderDialogAdmin.getOrder();
			order.setStaffId(Integer.valueOf(orderDialogAdmin.getCbbStaffId().getSelectedItem().toString()));
			int status = orderDialogAdmin.getCbbOrderStatus().getSelectedIndex() + 1;
			order.setOrderStatus(status);
			OrdersDao dao = new OrdersDao();
			// TH1+2 như nhau, TH3 phải + các stock vào khi hủy, TH4 phải getDate thời điểm
			// hiện tại
			switch (status) {
			case 1:
				if (dao.update(order)) {
					orderDialogAdmin.getAdminFrameView().getOrders().loadDataToTable();
					JOptionPane.showMessageDialog(orderDialogAdmin, "Order is pending!");
				} else {
					JOptionPane.showMessageDialog(orderDialogAdmin, "Falid!");
				}
				break;
			case 2:
				if (dao.update(order)) {
					orderDialogAdmin.getAdminFrameView().getOrders().loadDataToTable();
					JOptionPane.showMessageDialog(orderDialogAdmin, "Order is processing!");
				} else {
					JOptionPane.showMessageDialog(orderDialogAdmin, "Falid!");
				}
				break;
			case 3:
				//cap nhat lai cac stock
				OrdersItemsDao ordersItemsDao = new OrdersItemsDao();
				ArrayList<OrderItems> orderItems = ordersItemsDao.findAllOrderItemOfOrder(order);
				Stocks stock = new Stocks();
				stock.setStoreId(order.getStoreId());
				StocksDao stocksDao = new StocksDao();
				for (OrderItems orderItem : orderItems) {
					stock.setProducId(orderItem.getProductId());
					if (stocksDao.find(stock)) {
						stock.setQuantity(stock.getQuantity() + orderItem.getQuantity());
						if (!stocksDao.update(stock)) {
							JOptionPane.showMessageDialog(orderDialogAdmin, "Faild1");
							return;
						}
					} else {
						stock.setQuantity(orderItem.getQuantity());
						if (!stocksDao.insert(stock)) {
							JOptionPane.showMessageDialog(orderDialogAdmin, "Faild2");
							return;
						}
					}
				}
				//cap nhat cac thong tin moi cua order
				if (dao.update(order)) {
					orderDialogAdmin.getAdminFrameView().getOrders().loadDataToTable();
					JOptionPane.showMessageDialog(orderDialogAdmin, "Order is Rejected!");
					orderDialogAdmin.getCbbOrderStatus().setEnabled(false);
					orderDialogAdmin.getCbbStaffId().setEnabled(false);
					orderDialogAdmin.getBtnSave().setVisible(false);
				} else {
					JOptionPane.showMessageDialog(orderDialogAdmin, "Falid!");
				}
				break;
			case 4:
				long millis = System.currentTimeMillis();
				Date shippedDate = new Date(millis);
				order.setShippedDate(shippedDate);
				if (dao.update(order)) {
					orderDialogAdmin.getAdminFrameView().getOrders().loadDataToTable();
					JOptionPane.showMessageDialog(orderDialogAdmin, "Order is complete!");
					orderDialogAdmin.getCbbOrderStatus().setEnabled(false);
					orderDialogAdmin.getCbbStaffId().setEnabled(false);
					orderDialogAdmin.getBtnSave().setVisible(false);
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					String dateFormat = sdf.format(order.getShippedDate());
					orderDialogAdmin.getFtxtShippedDate().setText(dateFormat);
				} else {
					JOptionPane.showMessageDialog(orderDialogAdmin, "Falid!");
				}
				break;

			}
		}
	}

}
