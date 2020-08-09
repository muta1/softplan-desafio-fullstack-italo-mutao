package com.muta1.italomutao.exception;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ValidationException;

public class ExceptionValidation extends ValidationException {

	private static final long serialVersionUID = -4535033917963500257L;
	private Map<String, String> errors;

	public ExceptionValidation() {
	}

	public ExceptionValidation(String key, String message) {

		this.getErrors().put(key, message);
	}

	public Map<String, String> getErrors() {
		if (errors == null) {
			this.errors = new HashMap<>();
		}
		return errors;
	}
}
