package data_access_objects;

//We need to import the java.sql package to use JDBC
import java.sql.*;

//static method for getting DB connection.
public class DB_connector {

	private static Connection con;
	private static String username = "ora_v5y8";
	private static String password = "a23002090";
	private static String connectURL = "jdbc:oracle:thin:@localhost:1522:ug";

	public static Connection getConnection() throws SQLException {

		if (con == null) {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			con = DriverManager.getConnection(connectURL, username, password);
		}

		if (!con.isValid(10)) {
			con = DriverManager.getConnection(connectURL, username, password);
		}
		return con;
	}

	public void closeConnection() throws SQLException {
		con.close();
	}
}