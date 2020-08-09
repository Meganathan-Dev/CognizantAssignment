package com.rabo.api.bean;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rabo.api.common.StatementError;

public class ResponseBean implements Serializable {


	private static final long serialVersionUID = 1L;

	@JsonInclude(Include.NON_NULL)
	private String result;

	@JsonInclude(Include.NON_NULL)
	private List<StatementError> errorRecords;

	public ResponseBean(String result, List<StatementError> errorRecords) {
		super();
		this.result = result;
		this.errorRecords = errorRecords;
	}

	public ResponseBean() {
		super();
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;

	}

	public List<StatementError> getErrorRecords() {
		return errorRecords;
	}

	public void setErrorRecords(List<StatementError> errorRecords) {
		this.errorRecords = errorRecords;
	}

}
