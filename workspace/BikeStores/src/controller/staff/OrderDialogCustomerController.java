package controller.staff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dao.OrdersDao;
import dao.OrdersItemsDao;
import dao.StocksDao;
import model.OrderItems;
import model.Orders;
import model.Stocks;
import view.staff.OrderDialogCustomer;

public class OrderDialogCustomerController implements ActionListener {
	private OrderDialogCustomer orderDialogCustomer;

	public OrderDialogCustomerController(OrderDialogCustomer orderDialogCustomer) {
		this.orderDialogCustomer = orderDialogCustomer;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == orderDialogCustomer.getBtnBack()) {
			orderDialogCustomer.dispose();
		}
		if (e.getSource() == orderDialogCustomer.getBtnCacel()) {
			OrdersItemsDao dao = new OrdersItemsDao();
			Orders order = orderDialogCustomer.getOrder();
			ArrayList<OrderItems> orderItems = dao.findAllOrderItemOfOrder(order);
			Stocks stock = new Stocks();
			stock.setStoreId(order.getStoreId());
			StocksDao stocksDao = new StocksDao();
			for (OrderItems orderItem : orderItems) {
				stock.setProducId(orderItem.getProductId());
				if (stocksDao.find(stock)) {
					stock.setQuantity(stock.getQuantity() + orderItem.getQuantity());
					if (!stocksDao.update(stock)) {
						JOptionPane.showMessageDialog(orderDialogCustomer, "Faild1");
						return;
					}
				} else {
					stock.setQuantity(orderItem.getQuantity());
					if (!stocksDao.insert(stock)) {
						JOptionPane.showMessageDialog(orderDialogCustomer, "Faild2");
						return;
					}
				}
			}
			order.setOrderStatus(3);
			OrdersDao ordersDao = new OrdersDao();
			if (ordersDao.update(order)) {
				JOptionPane.showMessageDialog(orderDialogCustomer, "Order is Rejected!");
				orderDialogCustomer.getCbbOrderStatus().setSelectedIndex(2);
				orderDialogCustomer.getBtnCacel().setVisible(false);
				orderDialogCustomer.getStaffFrameView().getHistoryPanel().loadDataToTable();
			} else {
				JOptionPane.showMessageDialog(orderDialogCustomer, "Faild3");
			}
		}
	}
}
