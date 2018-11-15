package com.qa.service.repository;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.qa.persistence.domain.Account;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.List;

@Transactional(SUPPORTS) @Default
public class AccountManager implements ManagerInterface
{
	@PersistenceContext(unitName = "primary")
	@Inject
	private EntityManager accountManager;
	
	@Transactional(REQUIRED)
	public String createAccount(Account account) {
		accountManager.persist(account);
		return "{\"message\": \"movie sucessfully added\"}";
	}

	public List<Account> findAllAccounts() {
        TypedQuery<Account> query = accountManager.createQuery("SELECT a FROM Account a ORDER BY a.forename DESC", Account.class);
        return query.getResultList();
    }
	
	public Account findAccount(int accountNumber)
	{
		TypedQuery<Account> query = accountManager.createQuery("SELECT a FROM Account WHERE accountNumber = " + accountNumber, Account.class);
		return query.getSingleResult();
	}
	
	@Transactional(REQUIRED)
	public boolean updateAccount(int accountNumber, String newForename, String newSurname)
	{
		Account foundAccount = findAccount(accountNumber);
		foundAccount.setForename(newForename);
		foundAccount.setSurname(newSurname);
		
		accountManager.merge(foundAccount);
		
		return true;
	}
	
	@Transactional(REQUIRED)
	public boolean deleteAccount(int accountNumber)
	{
		Account foundAccount = findAccount(accountNumber);
		accountManager.remove(foundAccount);
		
		return true;
	}

}
