package controller.admin;

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

import dao.StaffsDao;
import help.DataValidator;
import help.ImageAccept;
import model.Staffs;
import view.admin.StaffDialog;

public class StaffsDialogController implements ActionListener, FocusListener {
	private StaffDialog staffDialog;

	public StaffsDialogController(StaffDialog staffDialog) {
		this.staffDialog = staffDialog;
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == staffDialog.getBtnSave()) {
			DataValidator.checkPhone(staffDialog, staffDialog.getTxtPhone());
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == staffDialog.getTxtFirstName()) {
			staffDialog.getTxtFirstName().setText(staffDialog.getTxtFirstName().getText().trim());
		}
		if (e.getSource() == staffDialog.getTxtLastName()) {
			staffDialog.getTxtLastName().setText(staffDialog.getTxtLastName().getText().trim());
		}
		if (e.getSource() == staffDialog.getTxtPhone()) {
			staffDialog.getTxtPhone().setText(staffDialog.getTxtPhone().getText().trim());
			if (staffDialog.getTxtPhone().getText().length() != 0) {
				DataValidator.checkPhone(staffDialog, staffDialog.getTxtPhone());
			} else {
				staffDialog.getTxtPhone().setBackground(Color.WHITE);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == staffDialog.getBtnOpen()) {
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
			if (fileChooser.showOpenDialog(staffDialog) == JFileChooser.CANCEL_OPTION) {
				return;
			}
			File file = fileChooser.getSelectedFile();
			ImageIcon icon = new ImageIcon(file.getPath());
			Image img = ImageAccept.resize(icon.getImage(), 100, 100);
			ImageIcon resizeIcon = new ImageIcon(img);
			staffDialog.getLbIcon().setIcon(resizeIcon);
			try {
				staffDialog.setStaffIcon(ImageAccept.toByteArray(img, "jpg"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource() == staffDialog.getBtnBack()) {
			staffDialog.dispose();
		}
		if (e.getSource() == staffDialog.getBtnSave()) {
			ArrayList<JComponent> check = new ArrayList<JComponent>();
			check.add(staffDialog.getTxtFirstName());
			check.add(staffDialog.getTxtLastName());
			check.add(staffDialog.getTxtPhone());
			check.add(staffDialog.getFtxtBirthDay());
			if (DataValidator.checkEmpty(staffDialog, check)) {
				Staffs staff = new Staffs();
				staff.setStoreId(Integer.valueOf(staffDialog.getCbbStoreId().getSelectedItem().toString()));
				staff.setActive(staffDialog.getCbActive().isSelected());
				if (staffDialog.getCbbManagerId().getSelectedItem() != null) {
					staff.setManagerId(Integer.valueOf(staffDialog.getCbbManagerId().getSelectedItem().toString()));
				} else {
					staff.setManagerId(null);
				}
				staff.setStaffIcon(staffDialog.getStaffIcon());
				staff.setFirstName(staffDialog.getTxtFirstName().getText());
				staff.setLastName(staffDialog.getTxtLastName().getText());
				staff.setSex(staffDialog.getRdbMale().isSelected());
				staff.setPhone(staffDialog.getTxtPhone().getText());
				if (staffDialog.getFtxtBirthDay().getText().length() != 0) {
					try {
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
						sdf.setLenient(false);
						java.util.Date date = sdf.parse(staffDialog.getFtxtBirthDay().getText());
						Date birthDay = new Date(date.getTime());
						staff.setBirthDay(birthDay);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
				StaffsDao dao = new StaffsDao();
				if (staffDialog.getStaff() != null) {
					staff.setStaffId(staffDialog.getStaff().getStaffId());
					if (dao.update(staff) > 0) {
						staffDialog.getAdminFrameView().getStaffs().loadDataToTable();
						JOptionPane.showMessageDialog(staffDialog, "Success!");
					} else {
						JOptionPane.showMessageDialog(staffDialog, "Falid!");
					}
				} else {
					if (dao.insert(staff) > 0) {
						staffDialog.getTxtStaffId().setText(String.valueOf(staff.getStaffId()));
						staffDialog.getAdminFrameView().getStaffs().loadDataToTable();
						JOptionPane.showMessageDialog(staffDialog, "Success!");
					} else {
						JOptionPane.showMessageDialog(staffDialog, "Falid!");
					}
				}
			} else {
				JOptionPane.showMessageDialog(staffDialog, "Text can't be left mark!");
			}
		}
	}

}
