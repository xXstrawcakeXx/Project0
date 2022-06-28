package com.revature.dao;

import java.util.List;

import com.revature.models.Account;

public interface IAccountDao {
	
	int insert(Account a);
	
	List<Account> findAll();
	
	List<Account> findById(int id); //returns account based off Id
	
	List<Account> findByOwner(int accOwnerId);
	
	boolean update(Account a);
	
	boolean delete(Account a);

	boolean depositFunds(Account a, double deposit);

	boolean withdrawFunds(Account a, double withdrawal);
}
