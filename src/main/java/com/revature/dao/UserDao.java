package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDao implements IUserDao {

	private String sql;
	private static Connection conn;
	
	
//*****************************************************************************************
	
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
			System.out.println(conn.getSchema());
			
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

	
//*****************************************************************************************
	//UNFINISHED SPAGHETTI CODE
	public User findById(int id) {	
		try {
			List<Account> accList = new LinkedList<Account>();
			conn = ConnectionUtil.getConnection();
			sql = "SELECT id FROM users WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int ids = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				Role u_role = (Role) rs.getObject(4);
				
				String sql2 = "SELECT acc_owner FROM user_accounts_jt WHERE acc_owner = ?";
				PreparedStatement stmt2 = conn.prepareStatement(sql2);
				stmt2.setInt(1, id);
				ResultSet rs2 = stmt2.executeQuery(sql2);
				
				while(rs.next())
				{
					
				}
				User u = new User(ids, username, password, u_role, );
			}			
						
		} catch (SQLException e) {
			System.out.println("Unable to find user - sql exception");
			e.printStackTrace();
		}
		return null;
	}

	
//*****************************************************************************************	
	
	public User findByUsername(String username) {
		
		try {
			conn = ConnectionUtil.getConnection();
			String sql = "SELECT username FROM users WHERE username = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String usern = rs.getString("username");
				String pwd = rs.getString("pwd");
				Role uRole = (Role) rs.getObject("user_role");
				
				User u = new User (id, usern, pwd, uRole, u.getAccounts() );
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return null;
	}

	
//*****************************************************************************************	
	
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

//*****************************************************************************************	
	
	public boolean update(User u) {

		return false;
	}

	
	
	
	public boolean delete(int id) {

		return false;
	}

}
