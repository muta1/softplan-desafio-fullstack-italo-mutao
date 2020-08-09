package com.muta1.italomutao.exception;

import javax.validation.ValidationException;

public class ServiceException extends ValidationException {
	private static final long serialVersionUID = -4535033917963500257L;
	private final CodeException code;

	public ServiceException(String message, CodeException code) {
		super(message);
		this.code = code;
	}

	public CodeException getCode() {
		return code;
	}
}
