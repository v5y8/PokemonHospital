package data_access_objects;

//We need to import the java.sql package to use JDBC
import java.sql.*;

//static method for getting DB connection.
public class DB_connector {

	private static Connection con;
	private final static String USERNAME = "ora_v5y8";
	private final static String PASSWORD = "a23002090";
	private final static String CONNECTURL = "jdbc:oracle:thin:@localhost:1522:ug";

	public static Connection getConnection() throws SQLException {

		if (con == null) {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			con = DriverManager.getConnection(CONNECTURL, USERNAME, PASSWORD);
		}

		return con;
	}

	public void closeConnection() throws SQLException {
		con.close();
	}
}