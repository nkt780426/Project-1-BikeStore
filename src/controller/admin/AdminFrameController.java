package controller.admin;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.login.ShareDataBetweenFrame;
import view.admin.AdminFrameView;
import view.login.LoginFrameView;

public class AdminFrameController implements ActionListener {
	private AdminFrameView adminFrameView;

	public AdminFrameController(AdminFrameView adminFrameView) {
		this.adminFrameView = adminFrameView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CardLayout c = adminFrameView.getCardLayout();
		if (e.getSource() == adminFrameView.getBtnProducts()) {
			c.show(adminFrameView.getPanelCenter(), "1");
			adminFrameView.getProducts().loadDataToTable();
		}
		if (e.getSource() == adminFrameView.getBtnStaffs()) {
			c.show(adminFrameView.getPanelCenter(), "2");
			adminFrameView.getStaffs().loadDataToTable();
		}
		if (e.getSource() == adminFrameView.getBtnCustomers()) {
			c.show(adminFrameView.getPanelCenter(), "3");
			adminFrameView.getCustomers().loadDataToTable();
		}
		if (e.getSource() == adminFrameView.getBtnOrders()) {
			c.show(adminFrameView.getPanelCenter(), "4");
			adminFrameView.getOrders().loadDataToTable();
		}
		if (e.getSource() == adminFrameView.getBtnStores()) {
			c.show(adminFrameView.getPanelCenter(), "5");
			adminFrameView.getStores().loadDataToTable();
		}
		if (e.getSource() == adminFrameView.getBtnBrands()) {
			c.show(adminFrameView.getPanelCenter(), "6");
			adminFrameView.getBrands().loadDataToTable();
		}
		if (e.getSource() == adminFrameView.getBtnCategories()) {
			c.show(adminFrameView.getPanelCenter(), "7");
		}
		if (e.getSource() == adminFrameView.getBtnSignOut()) {
			ShareDataBetweenFrame.account = null;
			adminFrameView.dispose();
			new LoginFrameView();
		}
	}

}
