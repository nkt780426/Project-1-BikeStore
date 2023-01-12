package controller.login;

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
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;

import dao.AccountsDao;
import dao.StaffsDao;
import help.DataValidator;
import help.ImageAccept;
import model.Accounts;
import model.Staffs;
import view.login.RegisterView;

public class RegisterController implements ActionListener, FocusListener {
	private RegisterView registerView;

	public RegisterController(RegisterView registerView) {
		this.registerView = registerView;
	}

	@Override
	public void focusGained(FocusEvent e) {
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == registerView.txtUsername) {
			registerView.txtUsername.setText(registerView.txtUsername.getText().trim());
			if (registerView.txtUsername.getText().length() != 0) {
				DataValidator.checkUserName(registerView, registerView.txtUsername);
			} else {
				registerView.txtUsername.setBackground(Color.WHITE);
			}
		}
		if (e.getSource() == registerView.password) {
			if (registerView.password.getPassword().length != 0) {
				DataValidator.checkPassword(registerView, registerView.password);
			} else {
				registerView.password.setBackground(Color.WHITE);
			}
		}
		if (e.getSource() == registerView.rePassword) {
			if (registerView.rePassword.getPassword().length != 0) {
				if (!Arrays.equals(registerView.password.getPassword(), registerView.rePassword.getPassword())) {
					JOptionPane.showMessageDialog(registerView, "Password dosen't match", null,
							JOptionPane.ERROR_MESSAGE);
					registerView.rePassword.setBackground(Color.RED);
					registerView.password.requestFocus();
				} else {
					registerView.rePassword.setBackground(Color.WHITE);
				}
			} else {
				registerView.rePassword.setBackground(Color.WHITE);
			}
		}
		if (e.getSource() == registerView.txtFirstName) {
			registerView.txtFirstName.setText(registerView.txtFirstName.getText().trim());
		}
		if (e.getSource() == registerView.txtLastName) {
			registerView.txtLastName.setText(registerView.txtLastName.getText().trim());
		}
		if (e.getSource() == registerView.txtPhone) {
			registerView.txtPhone.setText(registerView.txtPhone.getText().trim());
			if (registerView.txtPhone.getText().length() != 0) {
				DataValidator.checkPhone(registerView, registerView.txtPhone);
			} else {
				registerView.txtPhone.setBackground(Color.WHITE);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == registerView.btnBack) {
			registerView.dispose();
		}
		if (e.getSource() == registerView.btnOpen) {
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
			if (fileChooser.showOpenDialog(registerView) == JFileChooser.CANCEL_OPTION) {
				return;
			}
			File file = fileChooser.getSelectedFile();
			ImageIcon icon = new ImageIcon(file.getPath());
			Image img = ImageAccept.resize(icon.getImage(), 100, 100);
			ImageIcon resizeIcon = new ImageIcon(img);
			registerView.lbIcon.setIcon(resizeIcon);
			try {
				registerView.staffIcon = ImageAccept.toByteArray(img, "jpg");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource() == registerView.btnSignUp) {
			if (e.getSource() == registerView.btnSignUp) {
				ArrayList<JComponent> list = new ArrayList<JComponent>();
				list.add(registerView.txtUsername);
				list.add(registerView.password);
				list.add(registerView.rePassword);
				list.add(registerView.txtFirstName);
				list.add(registerView.txtLastName);
				list.add(registerView.txtPhone);
				list.add(registerView.ftxtBirthDay);
				if (!DataValidator.checkEmpty(registerView, list)) {
					JOptionPane.showMessageDialog(registerView, "Texts which be marked with *, cannot be left blank!");
				} else {
					try {
						// tao customer
						Staffs staff = new Staffs();
						staff.setFirstName(registerView.txtFirstName.getText());
						staff.setLastName(registerView.txtLastName.getText());
						staff.setSex(registerView.rdbMale.isSelected());
						try {
							SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
							sdf.setLenient(false);
							java.util.Date date = sdf.parse(registerView.ftxtBirthDay.getText());
							Date birthDay = new Date(date.getTime());
							staff.setBirthDay(birthDay);
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						staff.setPhone(registerView.txtPhone.getText());
						staff.setStaffIcon(registerView.staffIcon);
						staff.setActive(false);
						StaffsDao staffsDao = new StaffsDao();
						// tao account va xoa customer neu khong tao duoc
						if (staffsDao.insert(staff) != 0) {
							Accounts account = new Accounts(registerView.txtUsername.getText(),
									new String(registerView.password.getPassword()), false, staff.getStaffId());
							AccountsDao accountsDao = new AccountsDao();
							if (accountsDao.insert(account) == 0) {
								staffsDao.delete(staff);
								JOptionPane.showMessageDialog(registerView, "Faild");
							} else {
								JOptionPane.showMessageDialog(registerView, "Success!");
								registerView.dispose();
							}
						} else {
							JOptionPane.showMessageDialog(registerView, "Faild");
						}

					} catch (Exception e2) {
						JOptionPane.showMessageDialog(registerView, e2.getMessage());
					}
				}

			}
		}
	}

}
