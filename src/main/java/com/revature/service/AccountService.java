package com.revature.service;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.AccountDao;
import com.revature.dao.IAccountDao;
import com.revature.exceptions.RegisterAccountFailedException;
import com.revature.models.Account;

public class AccountService {
	
	public IAccountDao adao = new AccountDao();
	private static Scanner scan = new Scanner(System.in);
	
//*******************************************************************
	
	public Account register(Account a) {
		System.out.println("Registering account");
		if(a.getId() != 0) {
			throw new RegisterAccountFailedException("Account not valid to register because Id was not 0");
		}
		int generatedId = adao.insert(a);
		if(generatedId != -1 && generatedId != a.getId()) {
			a.setId(generatedId);
		}else {
			throw new RegisterAccountFailedException("User's Id was either -1 or did not change after insertion");
		}
		System.out.println("Successfully registered account with Id of " + a.getId());
		return a;
	}
	
//*******************************************************************
	
	public void viewAllAccounts() {
		System.out.println("Fetching accounts...");
		List<Account> accList = adao.findAll();
		
		for (Account a: accList) {
			System.out.println(a);
		}
	}
	
//*******************************************************************	
	
	public void viewAccountsById(int id) {
		System.out.println("Fetching account...");
		List<Account> accList = adao.findById(id);
		
		for(Account a: accList) {
			System.out.println(a);
	}
		
}

//*******************************************************************	
	
	public void viewAccountsByOwner(int acc_owner) {
		System.out.println("Fetching accounts...");
		List<Account> accList = adao.findByOwner(acc_owner);
		
		for(Account a: accList) {
			System.out.println(a);
		}
	}

//*******************************************************************
	
	public void updateAccount(int id) {
		Account a = new Account();
		a.setId(id);
	
		System.out.print("Please enter a new account balance:");
		double balance = scan.nextDouble();
		a.setBalance(balance);
		
		System.out.println("Please enter a new account owner: ");
		int acc_owner = scan.nextInt();
		a.setAccOwner(acc_owner);
		
		System.out.println("Please enter the new account status: ");
		boolean actOrNot = scan.nextBoolean();
		a.setActive(actOrNot);
		
		boolean updateAccount = adao.update(a);
		
		System.out.println("The new balance is: " + a.getBalance());
		System.out.println("The new account owner is: " + a.getAccOwner());
		System.out.println("The new status of the account is " + a.isActive());
		
		
		//System.out.println("This account's new balance is " + bal + ". The updated account owner is " + accOw + ". The current active status is: " + actOrNot + ".");
	}
	

	
//*******************************************************************
	
	public void deleteAccount(int id) {
		Account a = new Account();
		a.setId(id);
		boolean delete = adao.delete(a);
	}

//*******************************************************************	
	
public void depositAccountFunds(int id, double deposit) {
		Account a = new Account();
		a.setId(id);
		
		boolean isDeposited = adao.depositFunds(a, deposit);
		System.out.println("You have successfully deposited: " + deposit);
		System.out.println("Your new account balance is: " + a.getBalance());
	}
	
	
	public void withdrawAccountFunds(int id, double withdrawal) {
		Account a = new Account();
		a.setId(id);
		
		if((a.getBalance() - withdrawal) < 0) {
			System.out.println("You cannot withdraw more than you have!");
		}
		else {
			boolean isWithdrawn = adao.withdrawFunds(a, withdrawal);
			System.out.println("The new balance is: " + (a.getBalance() -  withdrawal) );
		}
	}

	
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	

