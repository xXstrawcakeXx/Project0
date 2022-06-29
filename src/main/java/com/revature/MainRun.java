package com.revature;

import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.service.AccountService;
import com.revature.service.UserService;

public class MainRun {

	static Scanner scan = new Scanner(System.in);

//*************************************************************************
	// MAIN
	public static void main(String[] args) {

		AccountService as = new AccountService();
		UserService us = new UserService();
		
		
		menuRun();
	}

//************************************************************************

	public static void menuRun() {
		AccountService as = new AccountService();
		UserService us = new UserService();
		User u = new User();
		
		
		
		
		System.out.println("Welcome to our Bank!");
		System.out.println("Press 1: Register New User");
		System.out.println("Press 2: Login");
		int input = scan.nextInt();
		
		
		switch(input) {
			case 1:// REGISTER NEW USER
				System.out.println("Please enter a username");
				String username = scan.next();
				
				System.out.println("Please enter a secure password");
				String password = scan.next();
			
				u = new User(username, password, Role.Customer);
				
				us.register(u);
				break;
				
				
				//LOGIN
			case 2:  
				UserService usLogin = new UserService();
		
				System.out.print("Please enter your username: ");
				String loginUsername = scan.next();
			
				System.out.println("Please enter your password: ");
				String loginPass = scan.next();
				
				User existing = usLogin.login(loginUsername, loginPass);
				
				
				System.out.println("Press 1: View existing accounts.");
				System.out.println("Press 2: Make a deposit");
				System.out.println("Press 3: Make a withdrawal");
				System.out.println("Press 2: Register for a new account");
				
				
				int existingAccountChoices = scan.nextInt();
				
				//VIEW EXISTING ACCOUNTS
				if(existingAccountChoices == 1) {
					System.out.println("These are your account/s");
					int existingId = existing.getId();
					
					as.viewAccountsByOwner(existingId);
					
				}//REGISTER NEW ACCOUNT
				else if(existingAccountChoices == 3) {
					Account newAcc = new Account();
					newAcc.setAccOwner(existing.getId());
					as.register(newAcc);
					
					
				}
					
				

				
			}
		}
				
//		if(input == 1) { // REGISTER NEW USER
//			System.out.println("Please enter a username");
//			String username = scan.next();
//			
//			System.out.println("Please enter a secure password");
//			String password = scan.next();
//			
//			User u = new User(username, password, Role.Customer);
//			UserService us = new UserService();
//			us.register(u);
//			
//			}
//		else if(input == 2) { //LOGIN
//			UserService usLogin = new UserService();
//			System.out.print("Please enter your username ");
//			String loginUsername = scan.next();
//			
//			System.out.println("Please enter your password ");
//			String loginPass = scan.next();
//				
//			usLogin.login(loginUsername, loginPass);
//			System.out.println("Press 1: View existing accounts.");
//			System.out.println("Press 2: Register for a new account");
//			
//			int existingAccountChoices = scan.nextInt();
//			if(existingAccountChoices == 1) {//
//				User u;
//				
//			}
//		}
						

	
}

