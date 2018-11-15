package com.qa.interprability;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.JSONObject;

import com.qa.persistence.domain.Account;
import com.qa.service.repository.AccountManager;

@Path("/account")
public class AccountEndpoint {

	@Inject
	private AccountManager accountManager;

	@Path("/json")
	@GET
	@Produces({ "application/json" })
	public String getAllAccounts() {
		return new JSONObject(accountManager.findAllAccounts()).toString();
	}

	@Path("/json")
	@POST
	@Produces({ "application/json" })
	public String addAccount(String forename, String surname, int accountNumber) {
		return accountManager.createAccount(new Account(forename, surname, accountNumber));
	}

	@Path("/json/{id}")
	@PUT
	@Produces({ "application/json" })
	public String updateAccount(@PathParam("id") int id, String forename, String surname) {
		return accountManager.updateAccount(id, forename, surname);
	}

	@Path("/json/{id}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteAccount(@PathParam("id") int id) {
		return accountManager.deleteAccount(id);

	}

	public void setService(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

}
