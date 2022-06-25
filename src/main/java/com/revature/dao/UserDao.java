package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDao implements IUserDao {

	private String sql;
	private static Connection conn;
	
	
	
	public int insert(User u) {
		// Step 1 - Capture DB connection using the connection util
		conn = ConnectionUtil.getConnection();
		
		// Step 2 - Generate a sql statement like "INSERT INTO..."
		sql = "INSERT INTO users (username, pwd, user_role) values (?, ?, ?) RETURNING users.id";

		// Step 2b - Use a Prepared statement to avoid SQL injection
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, u.getUsername());
			stmt.setString(2, u.getPassword());
			stmt.setObject(3, u.getRole(), Types.OTHER);
			System.out.println(u.getUsername() + u.getPassword() + u.getRole() + u.getId());
			
			ResultSet rs;
			if ((rs = stmt.executeQuery()) != null) {
				rs.next();
				int id = rs.getInt(1);
				System.out.println("We returned a user with id #" + id);
				return id;
			}
		} catch (SQLException e) {
			System.out.println("Unable to insert user - sql exception");
			e.printStackTrace();
		}
		return -1;
	}

	
	
	//UNFINISHED SPAGHETTI CODE
	public User findById(int id) {
		conn = ConnectionUtil.getConnection();
		sql = "SELECT id FROM users WHERE id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		
		ResultSet rs;
		if((rs= stmt.executeQuery()) != null) {
			rs.next();
			if(rs.getInt(1) == id) {
				return User u(id, 
			}
		}
		return ;
	}

	
	
	
	public User findByUsername(String username) {

		return null;
	}

	
	
	
	public List<User> findAll() {
		List<User> uList = new LinkedList<User>();
		
		conn = ConnectionUtil.getConnection();
			Statement stmt = conn.createStatement();
			
			String sql = "SELECT * FROM users";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String pwd = rs.getString("pwd");
				Role rowl = rs.getObject("user_role", Role.class);
				String sql2 = "SELECT 
				
				User u = new User(id, username, pwd, rowl, );
				uList.add(u);
			
		//}catch (SQLException e) {
			System.out.println("Unable to retrieve users due to SQL Exception");
		//	e.printStackTrace();
			// TODO: handle exception
		}
		
		
		return null;
		
	}

	
	
	
	public boolean update(User u) {

		return false;
	}

	
	
	
	public boolean delete(int id) {

		return false;
	}

}
