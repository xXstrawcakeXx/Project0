package com.revature.service;

import com.revature.dao.IUserDao;
import com.revature.dao.UserDao;
import com.revature.exceptions.RegisterUserFailedException;
import com.revature.models.User;

public class UserService {
	
	//Dependency Injection
	private IUserDao udao = new UserDao();
	
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
}
