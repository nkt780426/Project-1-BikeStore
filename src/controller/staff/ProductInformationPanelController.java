package controller.staff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import view.staff.ProductInformationPanel;

public class ProductInformationPanelController implements ActionListener {
	private ProductInformationPanel productInformationPanel;

	public ProductInformationPanelController(ProductInformationPanel productInformationPanel) {
		this.productInformationPanel = productInformationPanel;
	};

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == productInformationPanel.getBtnBack()) {
			productInformationPanel.getStaffFrameView().getShopPanel().getCardLayout()
					.show(productInformationPanel.getStaffFrameView().getShopPanel(), "1");
		}

		if (e.getSource() == productInformationPanel.getBtnAddToTrunk()) {
			try {
				int buy = Integer.valueOf(productInformationPanel.getTxtQuantity().getText());
				if (buy <= 0)
					throw new Exception();
				else {
					// lay so luong trong productInformationPanel
					int row = productInformationPanel.getTable().getRowCount();
					int storeId = Integer.valueOf(productInformationPanel.getCbbStoreId().getSelectedItem().toString());
					int stock = 0;
					for (int i = 0; i < row; i++) {
						if (productInformationPanel.getTable().getValueAt(i, 0) == productInformationPanel
								.getCbbStoreId().getSelectedItem()) {
							stock = (int) productInformationPanel.getTable().getValueAt(i, 1);
							break;
						}
					}
					// kiem tra table co ton tai chua
					boolean check = true;
					int quantityOnTrunk;
					JTable table = productInformationPanel.getStaffFrameView().getTransportPannel().getTable();
					for (int i = 0; i < table.getRowCount(); i++) {
						if (storeId == (int) table.getValueAt(i, 1) && productInformationPanel.getProduct()
								.getProductId() == (int) table.getValueAt(i, 2)) {
							quantityOnTrunk = (int) table.getValueAt(i, 4);
							if ((buy + quantityOnTrunk) <= stock) {
								table.setValueAt(buy + quantityOnTrunk, i, 4);
								JOptionPane.showMessageDialog(productInformationPanel,
										"Products has been added to your Trunk!\nCheck your trunk!");
							} else {
								table.setValueAt(stock, i, 4);
								JOptionPane.showMessageDialog(productInformationPanel,
										"You can only buy " + stock + " product\nCheck your trunk!");
							}
							check = false;
							break;
						}
					}
					if (check) {
						if (buy <= stock) {
							productInformationPanel.getStaffFrameView().getTransportPannel().getTblModel()
									.addRow(new Object[] { true, storeId,
											productInformationPanel.getProduct().getProductId(),
											productInformationPanel.getProduct().getProductName(), buy,
											productInformationPanel.getProduct().getListPrice() });
							JOptionPane.showMessageDialog(productInformationPanel,
									"Products has been added to your trunk!\nCheck your trunk!");
						} else {
							productInformationPanel.getStaffFrameView().getTransportPannel().getTblModel()
									.addRow(new Object[] { true, storeId,
											productInformationPanel.getProduct().getProductId(),
											productInformationPanel.getProduct().getProductName(), stock,
											productInformationPanel.getProduct().getListPrice() });
							JOptionPane.showMessageDialog(productInformationPanel,
									"You can only buy " + stock + "product\nCheck your trunk!");
						}

					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(productInformationPanel, "ERROR");
			}
		}
	}
}
