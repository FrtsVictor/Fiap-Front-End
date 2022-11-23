package br.com.fiap.fintech.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.fiap.fintech.exception.DataBaseConnectionException;

public class DatabaseConnection {

	private static final Connection con;

	static {
		final String url = "jdbc:oracle:thin:@localhost:1521:xe";
		final String user = "fintech_admin";
		final String pass = "123321";
		final String driver = "oracle.jdbc.driver.OracleDriver";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			String message = e.getMessage();

			switch (e.getClass().getSimpleName()) {
			case "ClassNotFoundException":
				message = "Error creating driver for " + driver;
			case "SQLException":
				message = "Error connecting to Sql Driver " + e.getMessage();
			default:
				throw new DataBaseConnectionException(message);
			}

		}
	}

	public static Connection getConnection() {
		return con;
	}

	public static void freeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
