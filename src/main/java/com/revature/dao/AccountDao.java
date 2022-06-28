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
import com.revature.util.ConnectionUtil;

//*****************************************************************************************	

public class AccountDao implements IAccountDao {
	
	private String sql;

//*****************************************************************************************	
	public int insert(Account a) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			sql = "INSERT INTO accounts (balance, acc_owner, active) values (?, ?, ?) RETURNING accounts.id";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, a.getBalance());
			stmt.setInt(2, a.getAccOwner());
			stmt.setObject(3, true);
			
			ResultSet rs;
			if ((rs = stmt.executeQuery()) != null) {
				rs.next();
				int id = rs.getInt(1);
				System.out.println("We returned an account with id #" + id);
				return id;
			}
		} catch (SQLException e) {
			System.out.println("Unable to insert account - sql exception");
			e.printStackTrace();
		}
		return -1;
	}

//*****************************************************************************************		
	
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
					
			}
		} catch (SQLException e) {
			System.out.println("Unable to find account");
		e.printStackTrace();
		}
		return accList;
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
				accList.add(b);
				System.out.println("This is the account associated with id number " + id + 
						": Balance \n" + bal + ", Account Owner " + accOw + ", Account Status " + actv);
				}
		}catch (SQLException e) {
			System.out.println("Unable to find account/s");
			e.printStackTrace();
		}
		return accList;
	}

//*****************************************************************************************	
	//ADMIN TYPE OF UPDATE (UPDATES BALANCE)
	public boolean update(Account a) {
		
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "UPDATE accounts SET balance = ?, acc_owner = ?, active = ? WHERE id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, a.getBalance());
			stmt.setInt(2, a.getAccOwner());
			stmt.setBoolean(3, a.isActive());
			stmt.setInt(4, a.getId());
			
			int rowsAff;
			if(((rowsAff = stmt.executeUpdate()) ==1)) {
				System.out.println("The account with id# " + a.getId() + " has been successfully updated.");
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Unable to update account - sql exception");
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
			
			int rowsAff;
			if(((rowsAff = stmt.executeUpdate()) ==1)) {
				System.out.println("Account with id " + a.getId() + " has been successfully deleted");
				return true;
			}
		}catch (SQLException e) {
			System.out.println("Unable to delete account - sql exception");
			e.printStackTrace();
		}
		return false;
	}
	
//*****************************************************************************************	
	
	public boolean updateAccountFunds(int id, int money) {
		try (Connection conn = ConnectionUtil.getConnection()){
			Account a = new Account();
			a.setId(id);
			String sql = "UPDATE accounts SET balance = ? WHERE id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, a.getBalance() + money);
			stmt.setInt(2, a.getId());
			
			int rowsAff;
			if(((rowsAff = stmt.executeUpdate()) ==1)) {
				
				System.out.println("The account with id# " + a.getId() + " has been updated.");
				System.out.println("The new balance is: " + (a.getBalance() + money) );
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Unable to update account balance - sql exception");
			e.printStackTrace();
		}
		return false;
		}

//*****************************************************************************************	
	
	public boolean depositFunds(Account a, double deposit) {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE accounts SET balance = ? WHERE id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, a.getBalance() + deposit);
			stmt.setInt(2, a.getId());
			
			int rowsAff;
			if(((rowsAff = stmt.executeUpdate()) ==1)) {
				System.out.println("The account with id# " + a.getId() + " has been updated.");
				
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Unable to update account balance - sql exception");
			e.printStackTrace();
		}
		return false;
		}

//*****************************************************************************************
	
	public boolean withdrawAccountFunds(Account a, double withdrawal) {
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE accounts SET balance = ? WHERE id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, a.getBalance() - withdrawal);
			stmt.setInt(2, a.getId());
			
			int rowsAff;
			if(((rowsAff = stmt.executeUpdate()) ==1)) {
				System.out.println("The account with id# " + a.getId() + " has been updated.");
				System.out.println("The new balance is: " + (a.getBalance() - withdrawal) );
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Unable to update account balance - sql exception");
			e.printStackTrace();
		}
		return false;
		}

	@Override
	public boolean withdrawFunds(Account a, double withdrawal) {
		// TODO Auto-generated method stub
		return false;
	}
	
}






































