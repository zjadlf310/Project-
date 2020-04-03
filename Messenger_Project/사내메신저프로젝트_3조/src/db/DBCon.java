package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon {
	Connection con;
	public Connection DBConnection() {
		return con;
	}

	public DBCon() throws SQLException, ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.58.26:1521:xe","hr","hr");
	}
	

}
