package com.revature;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.dao.UserDao;
import com.revature.exceptions.RegisterUserFailedException;
import com.revature.models.Account;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.service.UserService;


public class UserServiceTest {
	
	private UserService us;
	private UserDao mockDao;
	private User dummyUser;
	
//********************************************************************************
	
	@Before
	public void setup() {
		us = new UserService();
		
		mockDao = mock(UserDao.class);
		us.udao = mockDao;
		
		dummyUser = new User();
		dummyUser.setAccounts(new LinkedList<Account>());
		dummyUser.setId(0);
	}
	
//***************************************************************************************	
	
	@After
	public void teardown() {
		us = null;
		dummyUser = null;
		mockDao = null;
	}
	
//***************************************************************************************
	
	@Test
	public void testRegisterUserReturnsNewPKId() {
		dummyUser = new User(0, "Dude", "pass", Role.Admin, new LinkedList<Account>());
		
		Random r = new Random();
		int fakePK = r.nextInt(100);
		
		when(mockDao.insert(dummyUser)).thenReturn(fakePK);
		
		User registeredUser = us.register(dummyUser);
		
		assertEquals(registeredUser.getId(), fakePK);
	}
	
//***************************************************************************************
	
	//with pass test if it throws an exception
	@Test(expected = RegisterUserFailedException.class)
	public void testRegisterUserWithNonZeroId() {
		dummyUser.setId(1);
		
		us.register(dummyUser);
	}
	
//***************************************************************************************
	
	@Test(expected = RegisterUserFailedException.class)
	public void testInsertedUserReturnedNegativeOne() {
		
		dummyUser = new User(0, "Dude", "pass", Role.Admin, new LinkedList<Account>());
		
		int fakePK = -1;
		
		when(mockDao.insert(dummyUser)).thenReturn(fakePK);
		
		User registeredUser = us.register(dummyUser);
		
		assertEquals(registeredUser.getId(), fakePK);
	}
	
//***************************************************************************************
	
	@Test
	public void testLoginReturnsUser() {
		dummyUser = new User(0, "PewPew", "pass", Role.Admin, new LinkedList<Account>());
		
		String username = "PewPew";
		String pword = "pass";
		
		when(mockDao.findByUsername(username)).thenReturn(dummyUser);
		
		User loggedIn = us.login(username, pword);
		assertEquals(loggedIn.getUsername(), username);
	}
	
//***************************************************************************************

	
	@Test
	public void testFindUserById() {
		
//		String id = "";
//		
//		dummyUser = new User(0, username, "pass", Role.Admin, new LinkedList<Account>());
//		
//		
//		when(mockDao.findById(
//				
		
		
		
		
		
		
	}
	
	
	
}
