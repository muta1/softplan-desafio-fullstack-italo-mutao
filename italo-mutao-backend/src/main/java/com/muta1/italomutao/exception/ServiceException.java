package com.muta1.italomutao.exception;

import java.util.Map;

public class ServiceException extends ApiException {
	private static final long serialVersionUID = -4535033917963500257L;

	public ServiceException(String message) {
		this("error", message);
	}
	
	public ServiceException( String key, String message ) {
		this.getErrors().put(key, message);
		this.setCode(CodeException.BUSINESS_FAIL);
	}
	
	public ServiceException( Map<String, String>  errors) {
		this.setErrors(errors);
		this.setCode(CodeException.BUSINESS_FAIL);
	}

}
