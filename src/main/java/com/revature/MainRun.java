package com.revature;

import java.sql.SQLDataException;
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

		
		try {
			menuRun();
		} catch (SQLDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int zeroQuit = scan.nextInt();
		System.out.println("Press 0 to exit the program");
		if(zeroQuit == 0) {
			System.exit(0);
		}
			
	}

//************************************************************************

	public static void menuRun() throws SQLDataException {
		AccountService as = new AccountService();
		UserService us = new UserService();
		int input;
		double depos;
		String inputStr;
		String inputStrDepos;
		User u;
		
		
		System.out.println("Welcome to our Bank!");
		System.out.println("Press 1: Register New User");
		System.out.println("Press 2: Login");
//		
//		while(!scan.hasNextInt()) {
//			System.out.println("Invalid input. Please try again.");
//			System.out.println("Press 1: Register New User");
//			System.out.println("Press 2: Login");
//			
			//inputStr =scan.next();
		input = scan.nextInt();
		//}
		
//**************************************************************************************
		User existing;
		boolean isValid = false;
		//REGISTER ACCOUNT
		//while(!isValid) {
			if(input == 1) {
		//if((input = Integer.parseInt(inputStr)) == 1) {
				System.out.println("Please enter a username");
				String username = scan.next();

				System.out.println("Please enter a secure password");
				String password = scan.next();

				u = new User(username, password, Role.Customer);
				us.register(u); 
				scan.close();
			}
			
			//EXISTING USERS - LOGIN
			if(input == 2) {
			//else if((input = Integer.parseInt(inputStr)) == 2){
				UserService usLogin = new UserService();
				
				System.out.print("Please enter your username: ");
				String loginUsername = scan.next();
				
				System.out.println("Please enter your password: ");
				String loginPass = scan.next();
				
				existing = usLogin.login(loginUsername, loginPass);
			
			
//**********************************************************************************************			
				System.out.println("Invalid input. Please try again.");
				System.out.println("Press 1: Register for a new account");
				System.out.println("Press 2: View existing accounts.");
				System.out.println("Press 3: Make a deposit");
				System.out.println("Press 4: Make a withdrawal");
				System.out.println("Press 5: Transfer balance between accounts");
			
			
				//inputStr =scan.next();
				
				//inputStr = scan.next();
				input = scan.nextInt();
			
				//REGISTER FOR A NEW ACCOUNT
				if(input == 1) {
				//if((input = Integer.parseInt(inputStr)) == 1) {
					System.out.println("Registering for a new account...");
					Account newAcc = new Account();
					newAcc.setAccOwner(existing.getId());
					as.register(newAcc);
				}
				//VIEW USER'S EXISTING ACCOUNTS
				else if(input == 2) {
				//else if((input = Integer.parseInt(inputStr)) == 2) {
					System.out.println("These are your account/s");
					int existingId = existing.getId();
					as.viewAccountsByOwner(existingId);
				
				} // MAKE A DEPOSIT
				else if(input == 3) {
					System.out.println("In which account would you like to make your deposit?");
					as.viewAccountsByOwner(existing.getId());
					System.out.println("Please select an account by Account id#.");
					int accId = scan.nextInt();
					
					System.out.println("Please enter the amount that you would like to deposit: ");
					double dep = scan.nextDouble();
					//}
					
					int size = (existing.getAccounts()).size();
					for(Account filler : existing.getAccounts()) {
						if(accId == filler.getId()){
							as.depositAccountFunds(filler, dep);
						}	
					}	
					
				}//MAKE A WITHDRAWAL
				else if(input == 4) {
					System.out.println("In which account would you like to make a withdrawal?");
					as.viewAccountsByOwner(existing.getId());
					System.out.println("Please select an account by Account id#.");
					int accId = scan.nextInt();
					
					System.out.println("Please enter the amount that you would like to withdraw: ");
					double dep = scan.nextDouble();
					//}
					
					int size = (existing.getAccounts()).size();
					for(Account filler : existing.getAccounts()) {
						if(accId == filler.getId()){
							as.withdrawAccountFunds(filler, dep);
						}	
					}
				}//TRANSFER BALANCE BETWEEN ACCOUNTS
				else if(input == 5) {
					System.out.println("Enter the account id of the account that you would like to withdraw from: ");
					int accIdOut = scan.nextInt();
					
					System.out.println("Enter the account id of the account that you would like to transfer funds to: ");
					int accIdIn = scan.nextInt();
					
					System.out.print("How much would you like to transfer?");
					double transfMon = scan.nextDouble();
					
					for(Account filler : existing.getAccounts()) {
						if(accIdOut == filler.getId()) {
							as.withdrawAccountFunds(filler, transfMon);
						}
							else if(accIdIn == filler.getId()) {
								as.depositAccountFunds(filler, transfMon);
						}
						
								
				
							
					}
				}
			}
		}
	}
		
	//}
		
//		
//		isValid = true;
//	}
		
		//else if(input == 2) {
		
		// LOGIN
		
//			UserService usLogin = new UserService();
//
//			System.out.print("Please enter your username: ");
//			String loginUsername = scan.next();
//
//			System.out.println("Please enter your password: ");
//			String loginPass = scan.next();
//
//			User existing = usLogin.login(loginUsername, loginPass);
//
//			System.out.println("Press 1: View existing accounts.");
//			System.out.println("Press 2: Make a deposit");
//			System.out.println("Press 3: Make a withdrawal");
//			System.out.println("Press 2: Register for a new account");
//
//			int existingAccountChoices = scan.nextInt();
//
//			// VIEW EXISTING ACCOUNTS
//			if (existingAccountChoices == 1) {
//				System.out.println("These are your account/s");
//				int existingId = existing.getId();
//
//				as.viewAccountsByOwner(existingId);
//
//			} // REGISTER NEW ACCOUNT
//			else if (existingAccountChoices == 3) {
//				Account newAcc = new Account();
//				newAcc.setAccOwner(existing.getId());
//				as.register(newAcc);
//
//			}
		//default
		//}


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
