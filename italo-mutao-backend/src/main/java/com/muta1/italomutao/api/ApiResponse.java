package com.muta1.italomutao.api;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muta1.italomutao.exception.CodeException;

import lombok.Data;

@Data
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class ApiResponse<T> {
	private T response;
	private boolean success = true;
	private String errorMessage;
	private String errorTag;

	public void setError(String errorMessage, CodeException exceptionCode) {
		this.errorMessage = errorMessage;
		this.errorTag = exceptionCode.name();
		this.success = false;
	}

	public void setError(Map<String, String> errors, CodeException exceptionCode) {
		ObjectMapper objectMapper = new ObjectMapper();
		String errorsString = null;
		try {
			errorsString = objectMapper.writeValueAsString(errors);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			errorsString = errors.toString();
		}

		setError(errorsString, exceptionCode);
	}
}
