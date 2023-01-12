package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db_connection.JDBCUtil;
import model.OrderItems;
import model.Orders;

public class OrdersItemsDao {

	public int insert(OrderItems orderItem) {
		int ketQua = 0;
		Connection connection = JDBCUtil.getConnection();
		try {
			String sql = "INSERT INTO sales.orderItems (orderId ,itemId, productId, quantity, listPrice) "
					+ "VALUES (?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, orderItem.getOrderId());
			preparedStatement.setInt(2, orderItem.getItemId());
			preparedStatement.setInt(3, orderItem.getProductId());
			preparedStatement.setInt(4, orderItem.getQuantity());
			preparedStatement.setBigDecimal(5, orderItem.getListPrice());
			ketQua = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(connection);
		return ketQua;
	}

	// co nen dung ham update orderId, xay dung tam
	public int update(OrderItems orderItem) {
		int ketQua = 0;
		Connection connection = JDBCUtil.getConnection();
		try {
			String sql = "UPDATE sales.orderItems " + "SET  productId =?, quantity = ?, listPrice = ?"
					+ " WHERE orderId= ? AND orderId= ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, orderItem.getProductId());
			preparedStatement.setInt(2, orderItem.getQuantity());
			preparedStatement.setBigDecimal(3, orderItem.getListPrice());
			preparedStatement.setInt(4, orderItem.getOrderId());
			preparedStatement.setInt(5, orderItem.getItemId());
			ketQua = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(connection);
		return ketQua;
	}

	public int delete(OrderItems orderItem) {
		int ketQua = 0;
		Connection connection = JDBCUtil.getConnection();
		try {
			String sql = "DELETE FROM sales.orderItems WHERE orderID= ? AND itemId= ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, orderItem.getOrderId());
			preparedStatement.setInt(2, orderItem.getItemId());
			ketQua = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(connection);
		return ketQua;
	}


	// neu duoc doi ten thanh findItem
	public boolean find(OrderItems orderItem) {
		Connection connection = JDBCUtil.getConnection();
		try {
			String sql = "SELECT*FROM sales.orderItems WHERE orderId = ? AND itemId = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, orderItem.getOrderId());
			preparedStatement.setInt(2, orderItem.getItemId());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				orderItem.setOrderId(rs.getInt("orderId"));
				orderItem.setItemId(rs.getInt("itemId"));
				orderItem.setProductId(rs.getInt("productId"));
				orderItem.setQuantity(rs.getInt("quantity"));
				orderItem.setListPrice(rs.getBigDecimal("listPrice"));
				return true;
			}
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(connection);
		return false;
	}


	public ArrayList<OrderItems> findAllOrderItemOfOrder(Orders order) {
		ArrayList<OrderItems> ketQua = new ArrayList<OrderItems>();
		Connection connection = JDBCUtil.getConnection();
		try {
			String sql = "SELECT*FROM sales.orderItems WHERE orderId = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, order.getOrderId());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int orderId = rs.getInt("orderId");
				int itemId = rs.getInt("itemId");
				int productId = rs.getInt("productId");
				int quantity = rs.getInt("quantity");
				BigDecimal listPrice = rs.getBigDecimal("listPrice");
				OrderItems orderItem = new OrderItems(orderId, itemId, productId, quantity, listPrice);
				ketQua.add(orderItem);
			}
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(connection);
		return ketQua;
	}
}
