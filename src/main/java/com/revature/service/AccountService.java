package com.revature.service;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.AccountDao;
import com.revature.dao.IAccountDao;
import com.revature.models.Account;

public class AccountService {
	
	private static IAccountDao adao = new AccountDao();
	private static Scanner scan = new Scanner(System.in);
	
//*******************************************************************
	
	public void viewAllAccounts() {
		System.out.println("Fetching accounts...");
		//Calls on Dao to get all our accounts
		List<Account> accList = adao.findAll();
		
		for (Account a: accList) {
			System.out.println(a);
		}
	}
	
//*******************************************************************	
	
	public void viewAccountsById(int id) {
		System.out.println("Please enter an id");
		List<Account> accList = adao.findById(id);
		
		for(Account a: accList) {
			System.out.println(a);
	}
}

//*******************************************************************	
	
	public void viewAccountsByOwner(int acc_owner) {
		System.out.println("Please enter the account owner's number");
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
		
		boolean updat = adao.update(a);
		
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
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	

