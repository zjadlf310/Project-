package com.hairshop.dbcon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBcon {
	Connection con;
	
	public Connection DBConnection() {
		return con;
	}
	
	public DBcon() throws SQLException, ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.58.18:1521:xe","hr","hr");
	}

}
