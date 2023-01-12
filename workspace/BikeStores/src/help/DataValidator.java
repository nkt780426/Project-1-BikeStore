package help;

import java.awt.Color;
import java.awt.Component;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.AccountsDao;
import model.Accounts;

public class DataValidator {
	public static boolean showMessage(Component parent, JTextField textField, String message, boolean expresion) {
		if (!expresion) {
			JOptionPane.showMessageDialog(parent, message, "Error", JOptionPane.ERROR_MESSAGE);
			textField.setBackground(Color.RED);
			textField.requestFocus();
			return false;
		} else {
			textField.setBackground(Color.WHITE);
			return true;
		}
	}

	// ton tai trong = false, ko trong true
	public static boolean checkEmpty(Component parent, ArrayList<JComponent> checkComponents) {
		for (JComponent component : checkComponents) {
			if (component instanceof JTextField) {
				if (((JTextField) component).getText().length() == 0) {
					return false;
				}
			}
			if (component instanceof JPasswordField) {
				if (((JPasswordField) component).getPassword().length == 0) {
					return false;
				}
			}
			if (component instanceof JComboBox) {
				@SuppressWarnings("rawtypes")
				JComboBox a = (JComboBox) component;
				if (a.getSelectedItem() == null) {
					return false;
				}
			}
			if (component instanceof JFormattedTextField) {
				if (((JFormattedTextField) component).getText().length() == 0) {
					return false;
				}
			}

		}
		return true;
	}

	// khong duoc chua dau cach=>\w = 0-9,a-z,A-Z, varchar(50)
	public static void checkUserName(Component parent, JTextField txtUserName) {
		Pattern pattern = Pattern.compile("^\\w{4,50}$");
		Matcher matcher = pattern.matcher(txtUserName.getText());
		if (showMessage(parent, txtUserName, "UserName isn't correct", matcher.matches())) {
			Accounts account = new Accounts();
			account.setUsername(txtUserName.getText());
			AccountsDao dao = new AccountsDao();
			if (dao.checkUsername(account)) {
				JOptionPane.showMessageDialog(parent, "Username already exits!", "Error", JOptionPane.ERROR_MESSAGE);
				txtUserName.setBackground(Color.RED);
			} else {
				txtUserName.setBackground(Color.WHITE);
			}

		}
	}

	// co 1 so rang buoc: 1 hoa,1 thuong, 1 so , varvhar(50)
	public static void checkPassword(Component parent, JPasswordField password) {
		Pattern pattern = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,50})");
		Matcher matcher = pattern.matcher(new String(password.getPassword()));
		showMessage(parent, password, "Password isn't correct", matcher.matches());
	}

	// ky tu nhap vao phai la so
	public static void checkPhone(Component parent, JTextField txtPhone) {
		Pattern pattern = Pattern.compile("^\\d{7,15}$");
		Matcher matcher = pattern.matcher(txtPhone.getText());
		showMessage(parent, txtPhone, "Phone number isn't correct", matcher.matches());
	}

	// chuoi nhap vao phai la email
	public static void checkEmail(Component parent, JTextField txtEmail) {
		Pattern pattern = Pattern.compile("^^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6})*$");
		Matcher matcher = pattern.matcher(txtEmail.getText());
		showMessage(parent, txtEmail, "Your input is not an Email!", matcher.matches());
	}

	// chuoi nhap vao phai la date
	public static void checkDate(Component parent, JFormattedTextField ftxtDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		sdf.setLenient(false);
		try {
			sdf.parse(ftxtDate.getText());
		} catch (ParseException e) {
			showMessage(parent, (JTextField) ftxtDate, "Your input is not a date!", false);
		}
	}

	public static void checkPrice(Component parent, JTextField textField) {
		String listPrice = textField.getText();
		boolean check;
		try {
			if (BigDecimal.valueOf(Double.valueOf(listPrice)).signum() == -1) {
				throw new Exception();
			} else {
				check = true;
			}
		} catch (Exception e) {
			check = false;
		}
		showMessage(parent, textField, "Invalid price!", check);
	}

	public static void checkYear(Component parent, JTextField textField) {
		String modelYear = textField.getText();
		boolean check;
		try {
			int a = Integer.valueOf(modelYear);
			if (a < 2000 || a > 2100)
				throw new Exception();
			else
				check = true;
		} catch (Exception e) {
			check = false;
		}
		showMessage(parent, textField, "Invalid model year!", check);
	}
}
