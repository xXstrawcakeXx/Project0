package com.revature;

import java.util.List;
import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.service.AccountService;
import com.revature.service.UserService;

public class MainRun {

	static Scanner scan = new Scanner(System.in);

//*************************************************************************
	
	public static void main(String[] args) {

		AccountService as = new AccountService();
		as.deleteAccount(3);
		//run();
	}

//************************************************************************
	

	public static void run() {
			System.out.println("Welcome to our Bank!");
			System.out.println("Press 1: User Services");
			System.out.println("Press 2: Employee Services");
			int input = scan.nextInt();
			
			//User Services
			if(input == 1) {
				System.out.println("Press 1: Register New User");
				System.out.println("Press 2: Login");
				//User Services - Register new user
				if(scan.nextInt() == 1){
					System.out.println("Please enter a username");
					String username = scan.next();
				
					System.out.println("Please enter a secure password");
					String password = scan.next();
				
					User u = new User(username, password, Role.Customer, null);
				
					UserService us = new UserService();
				}
				//User Services - Login
				else if (scan.nextInt() == 2) {
					System.out.println("Enter username");
					String usName = scan.next();
					System.out.println("Enter password");
					String usPass = scan.next();
					
					//INSERT CODE: USER LOGIN ACCOUNT VERIFICATION
					//INSERT CODE: IF THEY SUCCESSFULLY LOGIN, GIVE ACCOUNT MENU
					
				}
				
				}
				
			if(input==2)

	{
		AccountService as = new AccountService();
		System.out.println("hey main input 2");
		int id = scan.nextInt();

		as.viewAllAccounts();

	}
}}

/*
 * 
 * System.out.println("Welcome to our bank! \n Press 1 for the Main Menu");
 * 
 * Scanner mainMenu = new Scanner(System.in); int menu = mainMenu.nextInt();
 * 
 * while(true) {
 * 
 * if(menu == 1) { System.out.println("========================");
 * System.out.println("Main Menu");
 * System.out.println("Press 1 for new member account registration \n" +
 * "Press 2 to Login \n" + "Press 3 to add an account to your account" +
 * "Press any other key to exit this application"); int menuOptions =
 * mainMenu.nextInt(); switch(menuOptions) { case 1: //invoke new user account
 * registration method break; case 2: //invoke login method break; case 3:
 * //invoke existing user, new-account registration method break; default:
 * System.out.
 * println("Unrecognized entry. Please press 1 to go back to the main menu"); }
 * }
 * 
 * 
 * 
 * }
 */
