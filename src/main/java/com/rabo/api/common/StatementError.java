package com.rabo.api.common;

public class StatementError {

	public Integer reference;
	public String accountNumber;
	public StatementError(Integer reference, String accountNumber) {
		super();
		this.reference = reference;
		this.accountNumber = accountNumber;
	}
	
	
	
}
