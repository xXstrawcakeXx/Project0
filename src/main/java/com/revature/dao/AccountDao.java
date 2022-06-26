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
	public List<Account> findById(int id) {
		List<Account> accList = new LinkedList<Account>();
		
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM accounts WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				int ids = rs.getInt("id");
				double balance = rs.getDouble("balance");
				int accOwnerId = rs.getInt("acc_owner");
				boolean isActive = rs.getBoolean("active");
				
				Account b = new Account(id, balance, accOwnerId, isActive);
					System.out.println("This is the account associated with id number " + ids + 
							": Balance \n" + balance + ", Account Owner " + accOwnerId + ", Account Status " + isActive);
					accList.add(b);
					return accList;
			}
		} catch (SQLException e) {
			System.out.println("Unable to find account");
		e.printStackTrace();
		}
		return null;
	}

//*****************************************************************************************	

	public List<Account> findByOwner(int accOwnerId) {
		List<Account> accList = new LinkedList<Account>();
		
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM accounts WHERE acc_owner = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, accOwnerId);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				double bal = rs.getDouble("balance");
				int accOw = rs.getInt("acc_owner");
				Boolean actv = rs.getBoolean("active");
				Account b = new Account(id, bal, accOw, actv);
				for(b: accList) {
				System.out.println("This is the account associated with id number " + accOw + ": Balance \n" 
						+ bal + ", Account Owner " + accOw + ", Account Status " + actv);
				accList.add(b);
				return accList;
				}
			}
		}catch (SQLException e) {
			System.out.println("Unable to find account");
			e.printStackTrace();
		}
		return null;
	}

//*****************************************************************************************	
	public boolean update(Account a) {
		
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "UPDATE accounts SET balance = ?, acc_owner = ?, active = ? WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, a.getBalance());
			stmt.setInt(2, a.getAccOwner());
			stmt.setBoolean(3, a.isActive());
			stmt.setInt(4, a.getId());
			
			ResultSet rs;
			if((rs = stmt.executeQuery()) != null) {
				rs.next();
				System.out.println("Account successfully updated");
			return true;
			}
		} catch (SQLException e) {
			System.out.println("Unable to update account - sql exception");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

//*****************************************************************************************	
	
	public boolean delete(Account a) {
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "DELETE FROM accounts WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, a.getId());
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			int id = rs.getInt(1);
			System.out.println("Account with id " + id + " has been successfully deleted");
			return true;
		}catch (SQLException e) {
			System.out.println("Unable to delete account - sql exception");
			e.printStackTrace();
		}
		return false;
	}
}
