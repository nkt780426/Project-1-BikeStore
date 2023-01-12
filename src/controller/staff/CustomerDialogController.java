package controller.staff;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;

import dao.CustomersDao;
import help.DataValidator;
import help.ImageAccept;
import model.Customers;
import view.staff.CustomerDialog;
import view.staff.StaffFrameView;

public class CustomerDialogController implements ActionListener, FocusListener {
	private CustomerDialog customerDialog;

	public CustomerDialogController(CustomerDialog customerDialog) {
		this.customerDialog = customerDialog;
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == customerDialog.getBtnTransport()) {
			ArrayList<JComponent> list = new ArrayList<JComponent>();
			list.add(customerDialog.getTxtFirstName());
			list.add(customerDialog.getTxtLastName());
			list.add(customerDialog.getTxtPhone());
			list.add(customerDialog.getTxtAddress());
			if (!DataValidator.checkEmpty(customerDialog, list)) {
				JOptionPane.showMessageDialog(customerDialog, "Texts which be marked with *, cannot be left blank!");
			}
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == customerDialog.getTxtFirstName()) {
			customerDialog.getTxtFirstName().setText(customerDialog.getTxtFirstName().getText().trim());
		}
		if (e.getSource() == customerDialog.getTxtLastName()) {
			customerDialog.getTxtLastName().setText(customerDialog.getTxtLastName().getText().trim());
		}
		if (e.getSource() == customerDialog.getTxtPhone()) {
			customerDialog.getTxtPhone().setText(customerDialog.getTxtPhone().getText().trim());
			if (customerDialog.getTxtPhone().getText().length() != 0) {
				DataValidator.checkPhone(customerDialog, customerDialog.getTxtPhone());
			} else {
				customerDialog.getTxtPhone().setBackground(Color.WHITE);
			}
		}
		if (e.getSource() == customerDialog.getTxtEmail()) {
			customerDialog.getTxtEmail().setText(customerDialog.getTxtEmail().getText().trim());
			if (customerDialog.getTxtEmail().getText().length() != 0) {
				DataValidator.checkEmail(customerDialog, customerDialog.getTxtEmail());
			} else {
				customerDialog.getTxtEmail().setBackground(Color.WHITE);
			}
		}
		if (e.getSource() == customerDialog.getTxtAddress()) {
			customerDialog.getTxtAddress().setText(customerDialog.getTxtAddress().getText().trim());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == customerDialog.getBtnBack()) {
			StaffFrameView staffFrameView = customerDialog.getStaffFrameView();
			staffFrameView.getCardLayout().previous(staffFrameView.getPanelCenter());
		}
		if (e.getSource() == customerDialog.getBtnOpen()) {
			JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView());
			fileChooser.setFileFilter(new FileFilter() {

				@Override
				public String getDescription() {
					return "Image File (*.jpg)";
				}

				@Override
				public boolean accept(File f) {
					if (f.isDirectory()) {
						return true;
					} else {
						return f.getName().toLowerCase().endsWith(".jpg");
					}
				}
			});
			if (fileChooser.showOpenDialog(customerDialog) == JFileChooser.CANCEL_OPTION) {
				return;
			}
			File file = fileChooser.getSelectedFile();
			ImageIcon icon = new ImageIcon(file.getPath());
			Image img = ImageAccept.resize(icon.getImage(), 100, 100);
			ImageIcon resizeIcon = new ImageIcon(img);
			customerDialog.getLbIcon().setIcon(resizeIcon);
			try {
				customerDialog.setCustomerIcon(ImageAccept.toByteArray(img, "jpg"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource() == customerDialog.getBtnTransport()) {
			try {
				// tao customer
				Customers customer = new Customers();
				customer.setFirstName(customerDialog.txtFirstName.getText());
				customer.setLastName(customerDialog.txtLastName.getText());
				customer.setSex(customerDialog.rdbMale.isSelected());
				if (customerDialog.ftxtBirthDay.getText().length() != 0) {
					try {
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
						sdf.setLenient(false);
						java.util.Date date = sdf.parse(customerDialog.ftxtBirthDay.getText());
						Date birthDay = new Date(date.getTime());
						customer.setBirthDay(birthDay);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
				customer.setPhone(customerDialog.txtPhone.getText());
				if (customerDialog.txtEmail.getText().length() != 0) {
					customer.setEmail(customerDialog.txtEmail.getText());
				}
				if (customerDialog.txtAddress.getText().length() != 0) {
					customer.setAddress(customerDialog.txtAddress.getText());
				}
				customer.setCustomerIcon(customerDialog.customerIcon);
				CustomersDao customersDao = new CustomersDao();
				// tao account va xoa customer neu khong tao duoc
				if (customersDao.insert(customer) != 0) {
					customerDialog.getStaffFrameView().getTransportPannel().setCustomer(customer);
					JOptionPane.showMessageDialog(customerDialog, "Success!");
					customerDialog.dispose();
				} else {
					JOptionPane.showMessageDialog(customerDialog, "Faild");
				}

			} catch (Exception e2) {
				JOptionPane.showMessageDialog(customerDialog, e2.getMessage());
			}

		}
	}

}
