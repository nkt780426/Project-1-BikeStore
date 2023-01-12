package controller.staff;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.staff.ProductPanel;

public class ProductPanelController implements MouseListener {
	private ProductPanel productPanel;

	public ProductPanelController(ProductPanel productPanel) {
		this.productPanel = productPanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		productPanel.getStaffFrameView().getShopPanel().getCardLayout()
				.show(productPanel.getStaffFrameView().getShopPanel(), "2");
		productPanel.getStaffFrameView().getShopPanel().getInformationPanel()
				.initInformation(productPanel.getProduct());
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}
