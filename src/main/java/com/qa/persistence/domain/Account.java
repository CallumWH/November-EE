package com.qa.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account
{
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(length = 20)
	private String forename;
	@Column(length = 20)
	private String surname;
	@Column(length = 10)
	private int accountNumber;

	public String getForename()
	{
		return forename;
	}

	public void setForename(String forename)
	{
		this.forename = forename;
	}

	public String getSurname()
	{
		return surname;
	}

	public void setSurname(String surname)
	{
		this.surname = surname;
	}

	public int getAccountNumber()
	{
		return accountNumber;
	}
	
	public Account(String forename, String surname, int accountNumber)
	{
		this.forename = forename;
		this.surname = surname;
		this.accountNumber = accountNumber;
	}
}