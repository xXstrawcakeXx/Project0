package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;
import com.revature.dao.IUserDao;

public class UserDao implements IUserDao {

	private String sql;
	private static Connection conn;
	
	
//*****************************************************************************************
	
	public int insert(User u) {
		
		try (Connection conn = ConnectionUtil.getConnection()){
			sql = "INSERT INTO users (id, username, pwd, user_role) values ((SELECT pg_catalog.setval(pg_get_serial_sequence('users', 'id'), MAX(id+1)) FROM users),?, ?, ?) RETURNING users.id";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1 , u.getUsername());
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
	//UNFINISHED SPAGHETTI CODE - STILL PRINTS 1 ACCOUNT FOR PPL WHO HAVE MULTIPLE
	public User findById(int id) {	
		
		User u = new User();
		
		try {conn = ConnectionUtil.getConnection();
			List<Account> accList = new LinkedList<Account>();
			
			sql = "SELECT * FROM users LEFT JOIN accounts ON users.id = accounts.acc_owner WHERE users.id = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				
				int userId = rs.getInt(1);
				String username = rs.getString("username");
				String password = rs.getString("pwd");
				Role role = Role.valueOf(rs.getString("user_role"));
				int accId = rs.getInt(5);
				double bal = rs.getDouble("balance");
				int acc_own = rs.getInt("acc_owner");
				boolean activityStatus = rs.getBoolean("active");
				
				Account a = new Account(accId, bal, acc_own, activityStatus);
				accList.add(a);
				System.out.println(accId);
				
				u.setId(userId);
				u.setUsername(username);
				u.setPassword(password);
				u.setRole(role);
				u.setAccounts(accList);
				
				System.out.println("User found!");
			}
			
			
		} catch (SQLException e) {
			System.out.println("Unable to find user by Id - sql exception");
			e.printStackTrace();
		}
		return u;
	}

	
//*****************************************************************************************	
	//UNFINISHED SPAGHETTI CODE - STILL PRINTS 1 ACCOUNT FOR PPL WHO HAVE MULTIPLE
	public User findByUsername(String username) {
		
		User u = new User();
		Account a = new Account();
		try {
			List<Account> accList = new LinkedList<Account>();
			conn = ConnectionUtil.getConnection();
			sql = "SELECT users.*, accounts.id, accounts.balance, accounts.active FROM users LEFT JOIN accounts ON users.id = accounts.acc_owner WHERE users.username = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);

			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int userId = rs.getInt(1);
				String usernamez = rs.getString("username");
				String password = rs.getString("pwd");
				
				Role role = Role.valueOf(rs.getString("user_role"));
				
				int accId = rs.getInt(5);
				double bal = rs.getDouble("balance");
				Boolean activ = rs.getBoolean("active");
				
				a = new Account(accId, bal, userId, activ); 
				accList.add(a);
				
				u.setId(userId);
				u.setUsername(usernamez);
				u.setPassword(password);
				u.setRole(role);
				u.setAccounts(accList);
				
				System.out.println("Fetching user...");
				
			
			}	
		} catch (SQLException e) {
			System.out.println("Unable to find user by username - sql exception");
			e.printStackTrace();
		}
		return u;
	}

	
//*****************************************************************************************	
	//ACCOUNT LIST = null :(
	public List<User> findAll() {
		List<User> uList = new LinkedList<User>();
		
		try(Connection conn = ConnectionUtil.getConnection()){
			Statement stmt = conn.createStatement();
			
			String sql = "SELECT * FROM users";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String pwd = rs.getString("pwd");
				Role role = Role.valueOf(rs.getString("user_role"));
				
				User u = new User(id, username, pwd, role, null );
				uList.add(u);
			}
		}catch (SQLException e) {
			System.out.println("Unable to retrieve users due to SQL Exception");
			e.printStackTrace();
			// TODO: handle exception
		}
		return uList;
	}
//*****************************************************************************************	
	
	public boolean update(User u) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE users SET username = ?, pwd = ? WHERE id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, u.getUsername());
			stmt.setString(2, u.getPassword());
			stmt.setInt(3, u.getId());
			
			int rowsAff;
			if((rowsAff = stmt.executeUpdate()) ==1) {
				System.out.println("The user with id# " + u.getId() + " has been updated");
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println("Unable to update user - sql exception");
			e.printStackTrace();
		}
		return false;
	}

//*****************************************************************************************	
//*****************************************************************************************	
	
	
	public boolean delete(int id) {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM users WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			
			int rowsAff;
			if(((rowsAff = stmt.executeUpdate()) == 1)) {
				System.out.println("User with id " + id + " has been successfully deleted");
				return true;
			}
		}catch (SQLException e) {
			System.out.println("Unable to delete account - sql exception");
			e.printStackTrace();
		}
		return false;
	}

	
	
	
	
	
}
