package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.rowset.serial.SerialBlob;

import db_connection.JDBCUtil;
import model.Customers;

public class CustomersDao {

	public int insert(Customers customer) {
		int ketQua = 0;
		Connection connection = JDBCUtil.getConnection();
		try {
			String sql = "INSERT INTO sales.customers (firstName, lastName, sex, birthDay, phone, email, address, customerIcon) "
					+ "OUTPUT inserted.customerId " + "VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customer.getFirstName());
			preparedStatement.setString(2, customer.getLastName());
			preparedStatement.setBoolean(3, customer.getSex());
			preparedStatement.setDate(4, customer.getBirthDay());
			preparedStatement.setString(5, customer.getPhone());
			preparedStatement.setString(6, customer.getEmail());
			preparedStatement.setString(7, customer.getAddress());
			if (customer.getCustomerIcon() != null) {
				preparedStatement.setBlob(8, new SerialBlob(customer.getCustomerIcon()));
			} else {
				Blob customerIcon = null;
				preparedStatement.setBlob(8, customerIcon);
			}
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				ketQua++;
				customer.setCustomerId(rs.getInt("customerId"));
			}
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(connection);
		return ketQua;
	}

	public int update(Customers customer) {
		int ketQua = 0;
		Connection connection = JDBCUtil.getConnection();
		try {
			String sql = "UPDATE sales.customers "
					+ "SET firstName=?, lastName=?, sex=?, birthDay=?, phone=?, email=?, address=?, customerIcon=? "
					+ "WHERE customerId= ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customer.getFirstName());
			preparedStatement.setString(2, customer.getLastName());
			preparedStatement.setBoolean(3, customer.getSex());
			preparedStatement.setDate(4, customer.getBirthDay());
			preparedStatement.setString(5, customer.getPhone());
			preparedStatement.setString(6, customer.getEmail());
			preparedStatement.setString(7, customer.getAddress());
			if (customer.getCustomerIcon() != null) {
				preparedStatement.setBlob(8, new SerialBlob(customer.getCustomerIcon()));
			} else {
				Blob customerIcon = null;
				preparedStatement.setBlob(8, customerIcon);
			}
			preparedStatement.setInt(9, customer.getCustomerId());
			ketQua = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(connection);
		return ketQua;
	}

	public int delete(Customers customer) {
		int ketQua = 0;
		Connection connection = JDBCUtil.getConnection();
		try {
			String sql = "DELETE FROM sales.customers WHERE customerId= ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customer.getCustomerId());
			ketQua = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(connection);
		return ketQua;
	}

	public ArrayList<Customers> selectAll() {
		ArrayList<Customers> ketQua = new ArrayList<Customers>();
		Connection connection = JDBCUtil.getConnection();
		try {
			String sql = "SELECT*FROM sales.customers";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Customers customer = new Customers();
				customer.setCustomerId(rs.getInt("customerId"));
				customer.setFirstName(rs.getString("firstName"));
				customer.setLastName(rs.getString("lastName"));
				customer.setSex(rs.getBoolean("sex"));
				customer.setBirthDay(rs.getDate("birthDay"));
				customer.setPhone(rs.getString("phone"));
				customer.setPhone(rs.getString("email"));
				customer.setAddress(rs.getString("address"));
				Blob customerIcon = rs.getBlob("customerIcon");
				if (customerIcon != null) {
					customer.setCustomerIcon(customerIcon.getBytes(1, (int) customerIcon.length()));
				}
				ketQua.add(customer);
			}
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(connection);
		return ketQua;
	}

	public boolean find(Customers customer) {
		Connection connection = JDBCUtil.getConnection();
		try {
			String sql = "SELECT*FROM sales.customers WHERE customerId=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customer.getCustomerId());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				customer.setCustomerId(rs.getInt("customerId"));
				customer.setFirstName(rs.getString("firstName"));
				customer.setLastName(rs.getString("lastName"));
				customer.setSex(rs.getBoolean("sex"));
				customer.setBirthDay(rs.getDate("birthDay"));
				customer.setPhone(rs.getString("phone"));
				customer.setEmail(rs.getString("email"));
				customer.setAddress(rs.getString("address"));
				Blob customerIcon = rs.getBlob("customerIcon");
				if (customerIcon != null) {
					customer.setCustomerIcon(customerIcon.getBytes(1, (int) customerIcon.length()));
				}
			}
			preparedStatement.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(connection);
		return false;
	}
}
