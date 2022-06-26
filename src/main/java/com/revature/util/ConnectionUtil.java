package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionUtil {
	private static Connection conn = null;
	
	private ConnectionUtil() {
	}
	
	public static Connection getConnection() {
		try {
			if(conn != null && !conn.isClosed()) {
				System.out.println("Using a previously made connection");
				return conn;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		

		//Most secure
		//w2d3 lecture 10am at -1:31:27
		String url = System.getenv("DB_URL");
		String username = System.getenv("DB_USERNAME");
		String password = System.getenv("DB_PWORD");
		
		try {
			conn = DriverManager.getConnection(url, username, password);
			conn.setSchema("project0-testschema");
			System.out.println("Established Connection to Database!");
		} catch (SQLException e) {
			System.out.println("Cannot establish connection");
			e.printStackTrace();
		}
		
		return conn;
		
	}
	
	
}
