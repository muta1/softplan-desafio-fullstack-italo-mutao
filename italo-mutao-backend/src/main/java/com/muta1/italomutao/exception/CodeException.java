package com.muta1.italomutao.exception;

public enum CodeException {
	GENERAL("SERVER ERROR", 1001), VALIDATION_FAIL("Validation Failure", 1002),
	BAD_CREDENTIALS("Invalid Credentials", 1003);

	private final String description;
	private final Integer code;

	private CodeException(String description, Integer code) {
		this.description = description;
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public Integer getCode() {
		return code;
	}
}
