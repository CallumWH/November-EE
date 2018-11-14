package com.qa.persistence.domain;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.List;

@Transactional(SUPPORTS)
public class AccountManager
{
	@PersistenceContext(unitName = "primary")
	private EntityManager accountManager;
	
	@Transactional(REQUIRED)
	public String createAccount(Account account) {
		accountManager.persist(account);
		return "{\"message\": \"movie sucessfully added\"}";
	}

	public List<Account> findAllMovies() {
        TypedQuery<Account> query = accountManager.createQuery("SELECT a FROM Account a ORDER BY a.forename DESC", Account.class);
        return query.getResultList();
    }

}
