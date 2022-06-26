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
	
}
