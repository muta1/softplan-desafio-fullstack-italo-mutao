package com.muta1.italomutao.exception;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public abstract class ApiException extends RuntimeException {
	private static final long serialVersionUID = -4535033917963500257L;
	private CodeException code;
	private Map<String, String> errors;
	
	
	
	public  Map<String, String> getErrors(){
		if(this.errors == null) {
			this.errors = new HashMap<String, String>();
		}
		
		return this.errors;
	}
}
