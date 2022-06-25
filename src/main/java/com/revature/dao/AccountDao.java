package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.revature.models.Account;
import com.revature.util.ConnectionUtil;

//*****************************************************************************************	

public class AccountDao implements IAccountDao {

//*****************************************************************************************	
	public int insert(Account a) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Account> findAll() {
		List<Account> accList = new LinkedList<Account>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			Statement stmt = conn.createStatement();

			String sql = "SELECT * FROM accounts";
			ResultSet rs = stmt.executeQuery(sql);

			// next() returns false if there is not next value present
			while (rs.next()) {
				int id = rs.getInt("id");
				double balance = rs.getDouble("balance");
				int accOwnerId = rs.getInt("acc_owner");
				boolean isActive = rs.getBoolean("active");

				Account a = new Account(id, balance, accOwnerId, isActive);
				accList.add(a);
			}
		} catch (SQLException e) {
			System.out.println("Unable to retrieve accounts due to SQL Exception");
			e.printStackTrace();
		}
		return accList;
	}
	
	
//*****************************************************************************************		
	//FINISHED BUT NEEDS TESTING
	//WHAT DO I RETURNNNNN????
	public Account findById(int id) {
		Connection conn = ConnectionUtil.getConnection();
		String sql = "SELECT * FROM accounts WHEN id = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				if (rs.getInt(1) == id) {
					double bal = rs.getDouble(2);
					int accOw = rs.getInt(3);
					Boolean actv = rs.getBoolean(4);
					
					Account b = new Account(id, bal, accOw, actv);
					System.out.println("This is the account associated with id number " + id + ": Balance \n" 
					+ bal + ", Account Owner " + accOw + ", Account Status " + actv);
					return b;
				}
			}
		} catch (SQLException e) {
			System.out.println("Unable to find account");
		e.printStackTrace();
		}
		return null;
	}

//*****************************************************************************************	

	
	
	public List<Account> findByOwner(int accOwnerId) {
		Connection conn = ConnectionUtil.getConnection();
		String sql = "SELECT * FROM accounts WHEN acc_owner = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, accOwnerId);
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				if (rs.getInt(3) == accOwnerId) {
					double bal = rs.getDouble(2);
					int accOw = rs.getInt(3);
					Boolean actv = rs.getBoolean(4);
					
					Account b = new Account(id, bal, accOw, actv);
					System.out.println("This is the account associated with id number " + id + ": Balance \n" 
					+ bal + ", Account Owner " + accOw + ", Account Status " + actv);
					return b;
				}
			}
		} catch (SQLException e) {
			System.out.println("Unable to find account");
		e.printStackTrace();
		}
		return null;
	}

//*****************************************************************************************	
	public boolean update(Account a) {
		// TODO Auto-generated method stub
		return false;
	}

//*****************************************************************************************	
	public boolean delete(Account a) {
		// TODO Auto-generated method stub
		return false;
	}

}
