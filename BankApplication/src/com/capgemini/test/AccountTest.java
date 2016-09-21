package com.capgemini.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.exceptions.InsufficientInitialBalanceException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountServiceImpl;

public class AccountTest {

	@Mock
	AccountRepository accountRepository;

	AccountService accountService;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		accountService = new AccountServiceImpl(accountRepository);
	}
	
	// 1. Amnt is less than 500 throw exception
	// 2. when valid info is passed acc sghould be created.
	
	@Test(expected=InsufficientInitialBalanceException.class)
	public void amtLessThan500() throws InsufficientInitialBalanceException{
		accountService.createAccount(12345, 400);
	}
	
	@Test
	public void validInfo() throws com.capgemini.exceptions.InsufficientInitialBalanceException{
		Account account = new Account();
		account.setAccountNumber(12345);
		account.setAmount(5000);		
		when(accountRepository.save(account)).thenReturn(true);
		assertEquals(account, accountService.createAccount(12345, 5000));
		System.out.println();
	}

}
