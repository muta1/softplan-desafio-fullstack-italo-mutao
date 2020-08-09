package com.muta1.italomutao.api;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muta1.italomutao.exception.CodeException;

import lombok.Data;

@Data
public class ApiResponse<T> {
	private T response;
	private String errorMessage;
	private String errorCode;
	private String errorTag;

	public void setError(String errorMessage, CodeException exceptionCode) {
		this.errorMessage = errorMessage;
		this.errorCode = exceptionCode.getCode().toString();
		this.errorTag = exceptionCode.name();
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

	@Override
	public String toString() {

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String data = "";
			if (this.response != null) {
				data = objectMapper.writeValueAsString(this.response);
			}

			ApiResponseDTO ret = new ApiResponseDTO(data, errorMessage, errorCode, errorTag);

			return objectMapper.writeValueAsString(ret);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Data
	class ApiResponseDTO {

		private String data;

		private String errorMessage;

		private String errorCode;

		private String errorTag;

		public ApiResponseDTO(String data, String errorMessage, String errorCode, String errorTag) {
			this.data = data;
			this.errorMessage = errorMessage;
			this.errorCode = errorCode;
			this.errorTag = errorTag;
		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

		public String getErrorMessage() {
			return errorMessage;
		}

		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}

		public String getErrorCode() {
			return errorCode;
		}

		public void setErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}

		public String getErrorTag() {
			return errorTag;
		}

		public void setErrorTag(String errorTag) {
			this.errorTag = errorTag;
		}
	}

}
