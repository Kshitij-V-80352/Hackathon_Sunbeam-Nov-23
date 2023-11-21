package com.sunbeam.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/bookMyShow";
	private static final String DB_USER = "D3_80352_Kshitij";
	private static final String DB_PASS = "80352";

	static {
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		} catch (SQLException ex) {
			System.out.println("Problem with authentication.. Exiting");
			System.exit(1);
		}
		return null;
	}
}
