package com.qa.service.repository;

import com.qa.persistence.domain.Account;

import java.util.List;


public interface ManagerInterface
{
	public String createAccount(Account account);

	public List<Account> findAllAccounts();
	
	public Account findAccount(int accountNumber);
	
	public String updateAccount(int accountNumber, String forenameUpdate, String surnameUpdate); 
	
	public String deleteAccount(int accountNumber);

}
