package com.rabo.api.bean;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestBean implements Serializable{


	private static final long serialVersionUID = 1L;
	@JsonProperty("Reference")
	@NotNull(message = "Reference should not be NULL.")
	public Integer reference;
	@JsonProperty("AccountNumber")
	@NotNull(message = "AccountNumber should not be NULL.")
	public String accountNumber;
	@JsonProperty("Description")
	@NotNull(message = "Description should not be NULL.")
	public String descrip;
	@JsonProperty("Start Balance")
	@NotNull(message = "Start Balance should not be NULL.")
	public Double startBalance;
	@JsonProperty("Mutation")
	@NotNull(message = "Mutation should not be NULL.")
	public Double mutation;
	@JsonProperty("End Balance")
	@NotNull(message = "End Balance should not be NULL.")
	public Double endBalance;

	public RequestBean(Integer reference, String accountNumber, String descrip, Double startBalance, Double mutation,
			Double endBalance) {
		super();
		this.reference = reference;
		this.accountNumber = accountNumber;
		this.descrip = descrip;
		this.startBalance = startBalance;
		this.mutation = mutation;
		this.endBalance = endBalance;
	}

	public Integer getReference() {
		return reference;
	}

	public void setReference(Integer reference) {
		this.reference = reference;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public Double getStartBalance() {
		return startBalance;
	}

	public void setStartBalance(Double startBalance) {
		this.startBalance = startBalance;
	}

	public Double getMutation() {
		return mutation;
	}

	public void setMutation(Double mutation) {
		this.mutation = mutation;
	}

	public Double getEndBalance() {
		return endBalance;
	}

	public void setEndBalance(Double endBalance) {
		this.endBalance = endBalance;
	}


	@Override
	public String toString() {
		return "RequestBean [reference=" + reference + ", accountNumber=" + accountNumber + ", descrip=" + descrip
				+ ", startBalance=" + startBalance + ", mutation=" + mutation + ", endBalance=" + endBalance + "]";
	}

}
