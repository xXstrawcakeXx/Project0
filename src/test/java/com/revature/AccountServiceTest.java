package com.revature;

import static org.junit.Assert.assertEquals;
import org.junit.Assert; 
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.dao.AccountDao;
import com.revature.exceptions.RegisterAccountFailedException;
import com.revature.models.Account;
import com.revature.service.AccountService;

public class AccountServiceTest {
	
	private AccountService as;
	private AccountDao mockDao;
	private Account dummyAccount;
	private List<Account> accList;
	private Random r;
	
//********************************************************************************	
	
	@Before
	public void setup() {
		as = new AccountService();
		
		mockDao = mock(AccountDao.class);
		as.adao = mockDao;
		
		dummyAccount = new Account();
		dummyAccount.setId(0);
	}
	
//***************************************************************************************
	
	@After
	public void teardown() {
		as = null;
		dummyAccount = null;
		mockDao = null;
	}

//***************************************************************************************	
	
	@Test
	public void testRegisterAccountReturnsNewPkId() {
		dummyAccount = new Account(0, 1_000.00, 1, false);
		Random r = new Random();
		int fakePk = r.nextInt(100);
		
		when(mockDao.insert(dummyAccount)).thenReturn(fakePk);
		
		Account registeredAccount = as.register(dummyAccount);
		assertEquals(registeredAccount.getId(), fakePk);
	}

//***************************************************************************************	
	
	@Test(expected = RegisterAccountFailedException.class)
	public void testRegisterAccountWithNonZeroId() {
		dummyAccount.setId(1);
		as.register(dummyAccount);
	}

//***************************************************************************************
	
	@Test(expected = RegisterAccountFailedException.class)
	public void testRegisterAccountReturnedNegativeOne() {
		
		dummyAccount.setId(1);
		dummyAccount.setBalance(0);
		dummyAccount.setAccOwner(1);
		dummyAccount.setActive(false);
		
		int fakePk = -1;
		when(mockDao.insert(dummyAccount)).thenReturn(fakePk);
		
		AccountService as = new AccountService();
		
		Account registeredAccount = as.register(dummyAccount);
		assertEquals(dummyAccount.getId(), fakePk);
	}
		
//***************************************************************************************		
	
	@Test
	public void testFindAccountById() {
		
		accList = new LinkedList<Account>();
		for(int i=0; i<=5; i++) {
			int fakeId = r.nextInt(100);
			double fakeBal = r.nextDouble()*100;
			int fakeAccOwn = r.nextInt(100);
			
			Account dummyAccount = new Account(fakeId, fakeBal, fakeAccOwn, false);
			accList.add(dummyAccount);
			when(mockDao.findAll()).thenReturn(accList);
			
		}
	}
		
//***************************************************************************************
		
		
	@Test
	public void testDepositAccountFunds() {
	
		
		
		Random r = new Random();
		int fakerId = r.nextInt(100);
		double fakeBal = r.nextDouble()*100;
		int fakeAccOwn = r.nextInt(100);
		double fakeDeposit = r.nextDouble()*100;
		boolean rBool = r.nextBoolean();
		
		dummyAccount.setId(fakerId);
		dummyAccount.setBalance(fakeBal);
		dummyAccount.setAccOwner(fakeAccOwn);
		dummyAccount.setActive(false);
		
		
		
		when(mockDao.depositFunds(dummyAccount, fakeDeposit)).thenReturn(rBool);
		
		as.depositAccountFunds(dummyAccount, fakeDeposit);
		double sum = fakeBal + fakeDeposit;
		dummyAccount.setBalance(sum);
		
		assertEquals(dummyAccount.getBalance(), sum, 1);
		
		
	}
	

			
			
		
				
	
}
	
	




























