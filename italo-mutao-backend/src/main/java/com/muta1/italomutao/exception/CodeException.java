package com.muta1.italomutao.exception;

public enum CodeException {
	GENERAL("SERVER ERROR"), 
	FORM_FAIL("Form Validation Failure"),
	VALIDATION_FAIL("Validation Failure"),
	BUSINESS_FAIL("Business Failure"),
	BAD_CREDENTIALS("Invalid Credentials");

	private final String description;

	private CodeException(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
