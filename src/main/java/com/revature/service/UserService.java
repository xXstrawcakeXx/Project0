package com.revature.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.revature.dao.IUserDao;
import com.revature.dao.UserDao;
import com.revature.exceptions.LoginUserFailedException;
import com.revature.exceptions.RegisterUserFailedException;
import com.revature.models.Account;
import com.revature.models.User;

public class UserService {
	
	//Dependency Injection
	public IUserDao udao = new UserDao();
	private static Scanner scan = new Scanner(System.in);
	
//***************************************************************************************************
	
	public User register(User u) {
		System.out.println("Registering user...");
		//System.out.println(u.getId());
		//Make sure new user has id of 0 before trying to register
		
		if(u.getId() != 0) {
			throw new RegisterUserFailedException("User not valid to register because Id was not 0");
			
		}
		//if id is 0, call dao to create new object
		
		int generatedId = udao.insert(u);
		//System.out.println(generatedId);
		
		if(generatedId != -1 && generatedId != u.getId()) {   //u.getId() should be 0 or it will throw exception
			u.setId(generatedId);
		} else {
			throw new RegisterUserFailedException("User's Id was either -1 or did not change after insertion");
		}
		System.out.println("Successfully registered used with Id of " + u.getId());
		
		return u;
	}
	
//***************************************************************************************************

	public void findUserById(int id) {
		User u = udao.findById(id);
		
		System.out.println("The user with id: " + id);
		System.out.println("Username: " + u.getUsername());
		System.out.println("Password: " + u.getPassword());
		System.out.println("Role: " + u.getRole());
		System.out.println("Accounts: " + u.getAccounts());
		
		
	}

//***************************************************************************************************	
	
	public void findUserbyUsername(String username) {
		User u = udao.findByUsername(username);
		
		System.out.println("The user with sername: " + u.getUsername());
		System.out.println("Id: " + u.getId());
		System.out.println("Password: " + u.getPassword());
		System.out.println("Role: " + u.getRole());
		
		for(Account a: u.getAccounts()) {
			System.out.println("Accounts: " + a);
		}
		
	}
	
	
	
//***************************************************************************************************
	
	public void viewAllUsers() {
		System.out.println("Fetching users...");
		List<User> uList = udao.findAll();
		
		for(User u: uList) {
			System.out.println(u);
		}
	}
	
//***************************************************************************************************	
	
	public void updateUser(int id) {
		User u = new User();
		u.setId(id);
		
		System.out.println("Please enter a new username: ");
		String newUsername = scan.next();
		u.setUsername(newUsername);
		
		System.out.println("Please enter a new password: ");
		String newPass = scan.next();
		u.setPassword(newPass);
		
		boolean updateUser = udao.update(u);
		
		System.out.println("The new username is: " + u.getId());
		System.out.println("The new password is: " + u.getPassword());
	}
	
//***************************************************************************************************
	
	public void deleteUser(int id) {
		User u = new User();
		u.setId(id);
		
		boolean delete = udao.delete(id);
		
	}
	
	
//***************************************************************************************************
	public User login(String username, String password) {
		User returnedUser = udao.findByUsername(username);
		
		if(returnedUser.getPassword().equals(password)) {
			System.out.println("Successfully Logged in!");
			return returnedUser;
		}
		else {
			throw new LoginUserFailedException("Wrong username or password. TRY AGAIN.");
		}
	}
	
//***************************************************************************************************
	
	
	
	
}













