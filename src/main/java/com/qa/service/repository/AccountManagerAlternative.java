package com.qa.service.repository;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.qa.persistence.domain.Account;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Transactional(SUPPORTS) @Alternative
public class AccountManagerAlternative implements ManagerInterface
{
	@PersistenceContext(unitName = "primary")
	@Inject
	private HashMap<Integer, Account> hmap;
	
	@Transactional(REQUIRED)
	public String createAccount(Account account) {
		hmap.put(account.getAccountNumber(), account);
		return "Account " + account.getAccountNumber() + " added";
	}

	public List<Account> findAllAccounts() {
		
        return new ArrayList<Account>(hmap.values());
    }
	
	public Account findAccount(int accountNumber)
	{
		Account foundAccount = hmap.get(accountNumber);
		return foundAccount;
	}
	
	@Transactional(REQUIRED)
	public String updateAccount(int accountNumber, String newForename, String newSurname)
	{
		Account foundAccount = findAccount(accountNumber);
		foundAccount.setForename(newForename);
		foundAccount.setSurname(newSurname);
		
		hmap.put(accountNumber, foundAccount);
		
		return new String(accountNumber + " has been updated");
	}
	
	@Transactional(REQUIRED)
	public String deleteAccount(int accountNumber)
	{
		hmap.remove(accountNumber);
		
		return new String(accountNumber + " has been deleted");
	}

}
