package com.rabo.api.common;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StatementError implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer reference;
	private String accountNumber;

}
