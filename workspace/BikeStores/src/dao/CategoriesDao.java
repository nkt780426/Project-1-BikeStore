package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db_connection.JDBCUtil;
import model.Categories;

public class CategoriesDao {

	public int insert(Categories category) {
		int ketQua = 0;
		Connection connection = JDBCUtil.getConnection();
		try {
			String sql = "INSERT INTO production.categories(categoryName) " + "VALUES (?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, category.getCategoryName());
			ketQua = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(connection);
		return ketQua;

	}

	public int update(Categories category) {
		int ketQua = 0;
		Connection connection = JDBCUtil.getConnection();
		try {
			String sql = "UPDATE production.categories " + "SET categoryName = ? " + "WHERE categoryId = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, category.getCategoryName());
			preparedStatement.setInt(2, category.getCategoryId());
			ketQua = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(connection);
		return ketQua;
	}

	public int delete(Categories category) {
		int ketQua = 0;
		Connection connection = JDBCUtil.getConnection();
		try {
			String sql = "DELETE FROM production.categories " + "WHERE categoryId = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, category.getCategoryId());
			ketQua = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(connection);
		return ketQua;
	}

	public ArrayList<Categories> selectAll() {
		ArrayList<Categories> ketQua = new ArrayList<Categories>();
		Connection connection = JDBCUtil.getConnection();
		try {
			String sql = "SELECT*FROM production.categories";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Categories category = new Categories();
				category.setCategoryId(rs.getInt("categoryId"));
				category.setCategoryName(rs.getString("categoryName"));
				ketQua.add(category);
			}
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(connection);
		return ketQua;

	}

	public boolean findById(Categories category) {
		Connection connection = JDBCUtil.getConnection();
		try {
			String sql = "SELECT*FROM production.categories WHERE categoryId = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, category.getCategoryId());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				category.setCategoryName(rs.getString("categoryName"));
				return true;
			}
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(connection);
		return false;
	}

	public boolean findByName(Categories category) {
		Connection connection = JDBCUtil.getConnection();
		try {
			String sql = "SELECT*FROM production.categories WHERE categoryName = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, category.getCategoryName());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				category.setCategoryId(rs.getInt("categoryId"));
				return true;
			}
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(connection);
		return false;
	}

	public ArrayList<String> allName() {
		ArrayList<String> result = new ArrayList<String>();
		Connection connection = JDBCUtil.getConnection();
		try {
			String sql = "SELECT categoryName FROM production.categories";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				result.add(rs.getString("categoryName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.closeConnection(connection);
		return result;
	}

}
