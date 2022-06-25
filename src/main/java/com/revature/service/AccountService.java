package com.revature.service;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.AccountDao;
import com.revature.dao.IAccountDao;
import com.revature.models.Account;

public class AccountService {
	
	private IAccountDao adao = new AccountDao();
	private static Scanner scan = new Scanner(System.in);
	
	public void viewAllAccounts() {
		System.out.println("Fetching accounts...");
		//Calls on Dao to get all our accounts
		List<Account> accList = adao.findAll();
		
		for (Account a: accList) {
			System.out.println(a);
		}
	}
	
}
