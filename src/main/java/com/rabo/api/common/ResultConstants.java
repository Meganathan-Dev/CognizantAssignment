package com.rabo.api.common;

public enum ResultConstants {

	SUCCESSFUL("Successful"),
	DUPLICATE_REFERENCE("Duplicate_reference"),
	INCORRECT_END_BALANCE("Incorrect_End_Balance"),
	DUPLICATE_REFERENCE_INCORRECT_END_BALANCE("Duplicate_Reference_Incorrect_End_Balance"),
	BAD_REQUEST("BAD_REQUEST"),
	INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR");
	String value;
	ResultConstants(String value) {
		this.value=value;
	}
	public String getValue() {
		return value;
	}
	
	
}
