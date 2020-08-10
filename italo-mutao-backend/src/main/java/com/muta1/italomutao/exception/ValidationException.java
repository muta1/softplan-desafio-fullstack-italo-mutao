package com.muta1.italomutao.exception;

import java.util.Map;

public class ValidationException extends ApiException {
	private static final long serialVersionUID = -4535033917963500257L;

	public ValidationException(String message) {
			this("validation", message);
	}
	
	public ValidationException( String key, String message ) {
		this.getErrors().put(key, message);
		this.setCode(CodeException.VALIDATION_FAIL);
	}
	
	public ValidationException( Map<String, String>  errors) {
		this.setErrors(errors);
		this.setCode(CodeException.VALIDATION_FAIL);
	}

}
