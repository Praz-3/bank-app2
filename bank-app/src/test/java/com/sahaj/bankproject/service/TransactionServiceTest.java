package com.sahaj.bankproject.service;

import org.junit.jupiter.api.Test;

import com.sahaj.bankproject.exceptions.InvalidTransactionException;
import com.sahaj.bankproject.model.Account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;


public class TransactionServiceTest {


	@Test
	public void testDeposit() {
		AccountService as = new AccountService();
		TransactionService ts= new TransactionService();
		
		Account acc = as.createAccount("Amit Dugal");
		try {
			double bal = ts.deposit(acc, 500);
			assertEquals(bal, 500);
		}
		catch(InvalidTransactionException e) {
			fail(e);
		}
	}
	
	@Test
	public void testMinDepositAmt() {
		AccountService as = new AccountService();
		TransactionService ts= new TransactionService();
		
		Account acc = as.createAccount("Amit Dugal");
		InvalidTransactionException e = assertThrows(
				InvalidTransactionException.class,
				() -> ts.deposit(acc, 100));
		assertTrue(e.getMessage().contains("Minimum deposit amount is"));
		
	}

	@Test
	public void testMaxDepositAmt() {
		AccountService as = new AccountService();
		TransactionService ts= new TransactionService();
		
		Account acc = as.createAccount("Amit Dugal");
		InvalidTransactionException e = assertThrows(
				InvalidTransactionException.class,
				() -> ts.deposit(acc, 60000));
		assertTrue(e.getMessage().contains("Maximum deposit amount is"));
		
	}

	@Test
	public void testMaxDepositCount() {
		AccountService as = new AccountService();
		TransactionService ts= new TransactionService();
		
		Account acc = as.createAccount("Amit Dugal");
		try {
			ts.deposit(acc, 500);
			ts.deposit(acc, 500);
			ts.deposit(acc, 500);
		} catch (InvalidTransactionException e1) {
			fail(e1);
		}
		InvalidTransactionException e = assertThrows(
				InvalidTransactionException.class,
				() -> ts.deposit(acc, 500));
		assertTrue(e.getMessage().contains("deposits are allowed in a day"));
		
	}

	@Test
	public void testWithdraw() {
		AccountService as = new AccountService();
		TransactionService ts= new TransactionService();
		
		Account acc = as.createAccount("Amit Dugal");
		try {
			ts.deposit(acc,11500);
			double bal = ts.withdraw(acc, 1000);
			assertEquals(bal, 10500);
		}
		catch(InvalidTransactionException e) {
			fail(e);
		}
	}
	
	@Test
	public void testMinWithdrawalAmt() {
		AccountService as = new AccountService();
		TransactionService ts= new TransactionService();
		
		Account acc = as.createAccount("Amit Dugal");
		try {
			ts.deposit(acc,11500);
		}
		catch(InvalidTransactionException e) {
			fail(e);
		}
		InvalidTransactionException e = assertThrows(
				InvalidTransactionException.class,
				() -> ts.withdraw(acc, 500));
		assertTrue(e.getMessage().contains("Minimum withdrawal amount is"));
		
	}

	@Test
	public void testInsufficientBalWithdrawal() {
		AccountService as = new AccountService();
		TransactionService ts= new TransactionService();
		
		Account acc = as.createAccount("Amit Dugal");
		try {
			ts.deposit(acc,11500);
		}
		catch(InvalidTransactionException e) {
			fail(e);
		}
		InvalidTransactionException e = assertThrows(
				InvalidTransactionException.class,
				() -> ts.withdraw(acc, 20000));
		assertTrue(e.getMessage().contains("Insufficient balance"));
		
	}

	@Test
	public void testMaxWithdrawalCount() {
		AccountService as = new AccountService();
		TransactionService ts= new TransactionService();
		
		Account acc = as.createAccount("Amit Dugal");
		try {
			ts.deposit(acc, 10000);	
			ts.withdraw(acc, 1000);
			ts.withdraw(acc, 1000);
			ts.withdraw(acc, 1000);
		} catch (InvalidTransactionException e1) {
			fail(e1);
		}
		InvalidTransactionException e = assertThrows(
				InvalidTransactionException.class,
				() -> ts.withdraw(acc, 1000));
		assertTrue(e.getMessage().contains("withdrawal are allowed in a day"));
		
		
	}
	
	@Test
	public void testTransfer() {
		AccountService as = new AccountService();
		TransactionService ts= new TransactionService();
		
		Account acc1 = as.createAccount("Amit Dugal");
		Account acc2 = as.createAccount("Gauri Kalla");
		boolean success = false;
		try {
			ts.deposit(acc1, 10000);	
			ts.deposit(acc2, 10000);	
			success = ts.transfer(acc1, acc2, 5000);
		} catch (InvalidTransactionException e1) {
			fail(e1);
		}
		assertTrue(success);
		
	}

	@Test
	public void testMinWithdrawalAmtTransfer() {
		AccountService as = new AccountService();
		TransactionService ts= new TransactionService();
		
		Account acc1 = as.createAccount("Amit Dugal");
		Account acc2 = as.createAccount("Gauri Kalla");
		try {
			ts.deposit(acc1, 10000);	
			ts.deposit(acc2, 10000);	
		}
		catch(InvalidTransactionException e) {
			fail(e);
		}
		InvalidTransactionException e = assertThrows(
				InvalidTransactionException.class,
				() -> ts.transfer(acc1, acc2, 500));
		assertTrue(e.getMessage().contains("Minimum withdrawal amount is"));
		
	}

	@Test
	public void testMaxWithdrawalAmtTransfer() {
		AccountService as = new AccountService();
		TransactionService ts= new TransactionService();
		
		Account acc1 = as.createAccount("Amit Dugal");
		Account acc2 = as.createAccount("Gauri Kalla");
		try {
			ts.deposit(acc1, 10000);	
			ts.deposit(acc2, 10000);	
		}
		catch(InvalidTransactionException e) {
			fail(e);
		}
		InvalidTransactionException e = assertThrows(
				InvalidTransactionException.class,
				() -> ts.transfer(acc1, acc2, 30000));
		assertTrue(e.getMessage().contains("Maximum withdrawal amount is"));
		
	}

}
