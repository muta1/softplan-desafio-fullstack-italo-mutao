package com.muta1.italomutao.exception;

import java.util.Map;

public class FormException extends ApiException {
	private static final long serialVersionUID = -4535033917963500257L;

	public FormException() {
		this.setCode(CodeException.FORM_FAIL);
	}
	
	public FormException( String key, String message ) {
		this.getErrors().put(key, message);
		this.setCode(CodeException.FORM_FAIL);
	}
	
	public FormException( Map<String, String>  errors) {
		this.setErrors(errors);
		this.setCode(CodeException.FORM_FAIL);
	}

}
