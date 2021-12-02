package com.sahaj.bankproject.service;

import org.junit.jupiter.api.Test;

import com.sahaj.bankproject.exceptions.InvalidTransactionException;
import com.sahaj.bankproject.model.Account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AccountServiceTest {

	@Test
	public void testCreateAccount() {
		
		AccountService as = new AccountService();
		
		int lastAccNo = as.getLastAccNo();
		Account acc = as.createAccount("Amit Dugal");
		assertEquals(lastAccNo+2, acc.getAccNo());
		
	}
	
	@Test
	public void testBalance() {
		AccountService as = new AccountService();
		TransactionService ts= new TransactionService();
		
		Account acc = as.createAccount("Amit Dugal");
		try {
			ts.deposit(acc, 500);
			ts.deposit(acc, 1000);
			ts.deposit(acc, 10000);
			double expectedBal = 11500;
			//double bal = as.currentBalance(acc);
			assertEquals(expectedBal, acc.getBalance());
		}
		catch(InvalidTransactionException e) {
			fail(e);
		}
		
	}

}