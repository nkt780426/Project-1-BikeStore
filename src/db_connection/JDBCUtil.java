package db_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

public class JDBCUtil {
	public static Connection getConnection() {
		Connection connection = null;

		try {
			// dang ky sql server voi DriverManager
			DriverManager.registerDriver(new SQLServerDriver());

			// cac thong so
			String DB_URL = "jdbc:sqlserver://DESKTOP-4KKHQ36:1433;" + "databaseName=BikeStores;"
					+ "integratedSecurity=true;" + "encrypt=false;";
			String user_name = "sa";
			String password = "MH34180426";

			// ket noi
			connection = DriverManager.getConnection(DB_URL, user_name, password);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

	public static void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
