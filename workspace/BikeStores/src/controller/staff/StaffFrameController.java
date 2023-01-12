package controller.staff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import controller.login.ShareDataBetweenFrame;
import view.login.LoginFrameView;
import view.staff.AboutUsView;
import view.staff.StaffFrameView;

public class StaffFrameController implements ActionListener, ItemListener {
	private StaffFrameView staffFrameView;

	public StaffFrameController(StaffFrameView customerFrameView) {
		this.staffFrameView = customerFrameView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == staffFrameView.getMnuiShop() || e.getSource() == staffFrameView.getBtnShop()) {
			staffFrameView.getCardLayout().show(staffFrameView.getPanelCenter(), "1");
		}
		if (e.getSource() == staffFrameView.getBtnTransport() || e.getSource() == staffFrameView.getMnuiTransport()) {
			staffFrameView.getCardLayout().show(staffFrameView.getPanelCenter(), "2");

		}
		if (e.getSource() == staffFrameView.getBtnPersonal()
				|| e.getSource() == staffFrameView.getMnuiPersonal()) {
			staffFrameView.getCardLayout().show(staffFrameView.getPanelCenter(), "3");
		}
		if (e.getSource() == staffFrameView.getBtnHistory() || e.getSource() == staffFrameView.getMnuiHistory()) {
			staffFrameView.getCardLayout().show(staffFrameView.getPanelCenter(), "4");
		}
		if (e.getSource() == staffFrameView.getBtnSignOut() || e.getSource() == staffFrameView.getMnuiSignOut()) {
			ShareDataBetweenFrame.account = null;
			ShareDataBetweenFrame.staff = null;
			staffFrameView.dispose();
			new LoginFrameView();
		}
		if (e.getSource() == staffFrameView.getMnuiAboutUs()) {
			new AboutUsView(staffFrameView);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == staffFrameView.getMnucbToolBar()) {
			if (staffFrameView.getMnucbToolBar().isSelected()) {
				staffFrameView.getToolBar().setVisible(true);
			} else {
				staffFrameView.getToolBar().setVisible(false);
			}
		}
	}

}
