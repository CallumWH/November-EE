package com.qa.service.repository;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.qa.persistence.domain.Account;
import com.qa.utils.JSONUtil;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Alternative
public class AccountManagerAlternative implements ManagerInterface
{
	private HashMap<Integer, Account> hmap = new HashMap<Integer, Account>();
	private JSONUtil jsonUtil = new JSONUtil();
	
	public String createAccount(Account account) {
		hmap.put(account.getAccountNumber(), account);
		return "Account " + account.getAccountNumber() + " added";
	}

	public String findAllAccounts() {
		
        return jsonUtil.getJSONForObject(new ArrayList<Account>(hmap.values()));
    }
	
	public Account findAccount(int accountNumber)
	{
		Account foundAccount = hmap.get(accountNumber);
		return foundAccount;
	}
	
	public String updateAccount(int accountNumber, String newForename, String newSurname)
	{
		Account foundAccount = findAccount(accountNumber);
		foundAccount.setForename(newForename);
		foundAccount.setSurname(newSurname);
		
		hmap.put(accountNumber, foundAccount);
		
		return new String(accountNumber + " has been updated");
	}
	
	public String deleteAccount(int accountNumber)
	{
		hmap.remove(accountNumber);
		
		return new String(accountNumber + " has been deleted");
	}

}
