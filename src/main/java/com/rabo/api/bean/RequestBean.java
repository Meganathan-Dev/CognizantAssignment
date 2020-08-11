package com.rabo.api.bean;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class RequestBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@JsonProperty("Reference")
	@NotNull(message = "Reference should not be NULL.")
	private Integer reference;
	@JsonProperty("AccountNumber")
	@NotNull(message = "AccountNumber should not be NULL.")
	private String accountNumber;
	@JsonProperty("Description")
	@NotNull(message = "Description should not be NULL.")
	private String descrip;
	@JsonProperty("Start Balance")
	@NotNull(message = "Start Balance should not be NULL.")
	private Double startBalance;
	@JsonProperty("Mutation")
	@NotNull(message = "Mutation should not be NULL.")
	private Double mutation;
	@JsonProperty("End Balance")
	@NotNull(message = "End Balance should not be NULL.")
	private Double endBalance;

}
