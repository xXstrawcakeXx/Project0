package com.revature;

import java.util.Scanner;

public class MainRun {
	public static void main(String[] args) {
		
		System.out.println("Welcome to our bank! \n Press 1 for the Main Menu");
		
		Scanner mainMenu = new Scanner(System.in);
		int menu = mainMenu.nextInt();
		
		while(true) {
			
			if(menu == 1) {
				System.out.println("========================");
				System.out.println("Main Menu");
				System.out.println("Press 1 for new member account registration \n"
						+ "Press 2 to Login \n"
						+ "Press 3 to add an account to your account"
						+ "Press any other key to exit this application");
				int menuOptions = mainMenu.nextInt(); 
				switch(menuOptions) {
					case 1:
						//invoke new user account registration method
						break;
					case 2:
						//invoke login method
						break;
					case 3:
						//invoke existing user, new-account registration method
						break;
				default:
				System.out.println("Unrecognized entry. Please press 1 to go back to the main menu");
			}
		}
				
			
				
		}
	}
}
			
			