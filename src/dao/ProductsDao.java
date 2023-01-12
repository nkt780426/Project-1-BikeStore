package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.rowset.serial.SerialBlob;

import db_connection.JDBCUtil;
import model.Products;

public class ProductsDao {

	public int insert(Products product) {
		int ketQua = 0;
		Connection connection = JDBCUtil.getConnection();
		try {
			String sql = "INSERT INTO production.products(productName, brandId, categoryId, modelYear, listPrice, productIcon) "
					+ "OUTPUT inserted.productId " + "VALUES (?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, product.getProductName());
			preparedStatement.setInt(2, product.getBrandId());
			preparedStatement.setInt(3, product.getCategoryId());
			preparedStatement.setInt(4, product.getModelYear());
			preparedStatement.setBigDecimal(5, product.getListPrice());
			if (product.getProductIcon() != null) {
				preparedStatement.setBlob(6, new SerialBlob(product.getProductIcon()));
			} else {
				preparedStatement.setBlob(6, (Blob) null);
			}
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				ketQua++;
				product.setProductId(rs.getInt("productId"));
			}
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(connection);
		return ketQua;

	}

	// co nen update categoryId, brandId ?
	public int update(Products product) {
		int ketQua = 0;
		Connection connection = JDBCUtil.getConnection();
		try {
			String sql = "UPDATE production.products "
					+ "SET productName = ?, brandId = ?, categoryId = ?, modelYear = ?, listPrice=?, productIcon=? "
					+ "WHERE productId = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, product.getProductName());
			preparedStatement.setInt(2, product.getBrandId());
			preparedStatement.setInt(3, product.getCategoryId());
			preparedStatement.setInt(4, product.getModelYear());
			preparedStatement.setBigDecimal(5, product.getListPrice());
			if (product.getProductIcon() != null) {
				preparedStatement.setBlob(6, new SerialBlob(product.getProductIcon()));
			} else {
				preparedStatement.setBlob(6, (Blob) null);
			}
			preparedStatement.setInt(7, product.getProductId());
			ketQua = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(connection);
		return ketQua;
	}

	public int delete(Products product) {
		int ketQua = 0;
		Connection connection = JDBCUtil.getConnection();
		try {
			String sql = "DELETE FROM production.products " + "WHERE productId = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, product.getProductId());
			ketQua = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(connection);
		return ketQua;

	}

	public ArrayList<Products> selectAll() {
		Connection connection = JDBCUtil.getConnection();
		ArrayList<Products> result = new ArrayList<Products>();
		try {
			String sql = "SELECT*FROM production.products";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Products product = new Products();
				product.setProductId(rs.getInt("productId"));
				product.setProductName(rs.getString("productName"));
				product.setBrandId(rs.getInt("brandId"));
				product.setCategoryId(rs.getInt("categoryId"));
				product.setModelYear(rs.getInt("modelYear"));
				product.setListPrice(rs.getBigDecimal("listPrice"));
				Blob productIcon = rs.getBlob("productIcon");
				if (productIcon != null) {
					product.setProductIcon(productIcon.getBytes(1, (int) productIcon.length()));
				}
				result.add(product);
			}
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(connection);
		return result;
	}

	public ArrayList<Integer> find(String productName, String startPrice, String endPrice, Integer year,
			Integer brandId, Integer categoryId) {
		Connection connection = JDBCUtil.getConnection();
		ArrayList<Integer> result = new ArrayList<Integer>();
		try {
			String sql = "SELECT productId FROM production.products "
					+ "WHERE productName like ? and (listPrice between ? and ?) "
					+ "and modelYear like ? and brandId like ? and categoryId like ? and productId in ("
					+ "SELECT DISTINCT productId FROM production.stocks)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + productName + "%");
			if (startPrice.length() == 0) {
				preparedStatement.setInt(2, 0);
			} else {
				int start = Integer.valueOf(startPrice);
				preparedStatement.setInt(2, start);
			}
			if (endPrice.length() == 0) {
				preparedStatement.setInt(3, 999999);
			} else {
				int end = Integer.valueOf(endPrice);
				preparedStatement.setInt(3, end);
			}
			if (year != null) {
				preparedStatement.setString(4, "%" + year + "%");
			} else {
				preparedStatement.setString(4, "%%");
			}
			if (brandId != null) {
				preparedStatement.setString(5, "%" + brandId + "%");
			} else {
				preparedStatement.setString(5, "%%");
			}
			if (categoryId != null) {
				preparedStatement.setString(6, "%" + categoryId + "%");
			} else {
				preparedStatement.setString(6, "%%");
			}
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				result.add(rs.getInt("productId"));
			}
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(connection);
		return result;
	}

	public boolean findById(Products product) {
		Connection connection = JDBCUtil.getConnection();
		try {
			String sql = "SELECT*FROM production.products WHERE productId = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, product.getProductId());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				product.setProductName(rs.getString("productName"));
				product.setBrandId(rs.getInt("brandId"));
				product.setCategoryId(rs.getInt("categoryId"));
				product.setModelYear(rs.getInt("modelYear"));
				product.setListPrice(rs.getBigDecimal("listPrice"));
				Blob productIcon = rs.getBlob("productIcon");
				if (productIcon != null) {
					product.setProductIcon(productIcon.getBytes(1, (int) productIcon.length()));
				}
				return true;
			}
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(connection);
		return false;
	}

	public boolean findByName(Products product) {
		Connection connection = JDBCUtil.getConnection();
		try {
			String sql = "SELECT*FROM production.products WHERE productName = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, product.getProductName());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				product.setProductId(rs.getInt("productId"));
				product.setBrandId(rs.getInt("brandId"));
				product.setCategoryId(rs.getInt("categoryId"));
				product.setModelYear(rs.getInt("modelYear"));
				product.setListPrice(rs.getBigDecimal("listPrice"));
				Blob productIcon = rs.getBlob("productIcon");
				if (productIcon != null) {
					product.setProductIcon(productIcon.getBytes(1, (int) productIcon.length()));
				}
				return true;
			}
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(connection);
		return false;
	}

	public ArrayList<Products> search(String target) {
		ArrayList<Products> result = new ArrayList<Products>();
		Connection connection = JDBCUtil.getConnection();
		try {
			String sql = "SELECT*FROM production.products WHERE productID LIKE ? OR productName LIKE ? OR brandId LIKE ? OR categoryID LIKE ? OR listPrice LIKE ? OR modelYear LIKE ? ORDER BY productId";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + target + "%");
			preparedStatement.setString(2, "%" + target + "%");
			preparedStatement.setString(3, "%" + target + "%");
			preparedStatement.setString(4, "%" + target + "%");
			preparedStatement.setString(5, "%" + target + "%");
			preparedStatement.setString(6, "%" + target + "%");
			ResultSet resultSet = preparedStatement.executeQuery();
			Products product = new Products();
			while (resultSet.next()) {
				product.setProductId(resultSet.getInt("productId"));
				product.setProductName(resultSet.getString("productName"));
				product.setBrandId(resultSet.getInt("brandId"));
				product.setCategoryId(resultSet.getInt("categoryId"));
				product.setModelYear(resultSet.getInt("modelYear"));
				product.setListPrice(resultSet.getBigDecimal("listPrice"));
				Blob productIcon = resultSet.getBlob("productIcon");
				if (productIcon != null) {
					product.setProductIcon(productIcon.getBytes(1, (int) productIcon.length()));
				}
				result.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(connection);
		return result;
	}
}
