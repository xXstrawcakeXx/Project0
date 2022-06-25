package com.revature.dao;

import java.util.List;

import com.revature.models.User;

public interface IUserDao {

	//Insert or create a User object
	int insert(User u); //Returns the new primary key of the new instance of User that was inserted
	
	//Read: return 1 or return all
	User findById(int id); //Returns User object associated with this id in DB
	User findByUsername(String username);  //Returns User object associated with this username
	List<User> findAll(); //Returns list of all User objects in the DB
	
	//Update
	boolean update(User u); //Updates a user in the DB and return true if successful and false if not
	
	//Delete
	boolean delete(int id); //Delete the user associated with the ID;
}
