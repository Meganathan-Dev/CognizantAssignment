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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result + ((descrip == null) ? 0 : descrip.hashCode());
		result = prime * result + ((endBalance == null) ? 0 : endBalance.hashCode());
		result = prime * result + ((mutation == null) ? 0 : mutation.hashCode());
		result = prime * result + ((startBalance == null) ? 0 : startBalance.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestBean other = (RequestBean) obj;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (descrip == null) {
			if (other.descrip != null)
				return false;
		} else if (!descrip.equals(other.descrip))
			return false;
		if (endBalance == null) {
			if (other.endBalance != null)
				return false;
		} else if (!endBalance.equals(other.endBalance))
			return false;
		if (mutation == null) {
			if (other.mutation != null)
				return false;
		} else if (!mutation.equals(other.mutation))
			return false;
		if (startBalance == null) {
			if (other.startBalance != null)
				return false;
		} else if (!startBalance.equals(other.startBalance))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RequestBean [reference=" + reference + ", accountNumber=" + accountNumber + ", descrip=" + descrip
				+ ", startBalance=" + startBalance + ", mutation=" + mutation + ", endBalance=" + endBalance + "]";
	}

}
