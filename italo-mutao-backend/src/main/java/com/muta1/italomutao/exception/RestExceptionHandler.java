package com.muta1.italomutao.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muta1.italomutao.api.ApiResponse;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<ApiResponse<String>> handleServiceException(ServiceException serviceException) {

		ApiResponse<String> response = buildResponse(serviceException.getErrors(), serviceException.getCode());

		return new ResponseEntity<ApiResponse<String>>(response, HttpStatus.OK);
	}

	@ExceptionHandler(FormException.class)
	public ResponseEntity<ApiResponse<String>> handleFormException(FormException formException) {

		ApiResponse<String> response = buildResponse(formException.getErrors(), formException.getCode());

		return new ResponseEntity<ApiResponse<String>>(response, HttpStatus.OK);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ApiResponse<String>> handleValidationException(ValidationException validationException) {

		ApiResponse<String> response = buildResponse(validationException.getErrors(), validationException.getCode());

		return new ResponseEntity<ApiResponse<String>>(response, HttpStatus.OK);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ApiResponse<String>> handleBadCredentialsException(
			BadCredentialsException badCredentialsException) {
		Map<String, String> errors = new HashMap<>();
		errors.put("error", "Credenciais inválidas.");
		ApiResponse<String> response = buildResponse(errors, CodeException.BAD_CREDENTIALS);

		return new ResponseEntity<ApiResponse<String>>(response, HttpStatus.OK);
	}

	@ExceptionHandler(Throwable.class)
	protected ResponseEntity<ApiResponse<String>> handleAnyOtherThrowable(Throwable throwable) {

		Map<String, String> errors = new HashMap<String, String>();
		errors.put("error",
				"Houve um problema ao executar sua solicitação. Tente novamente mais tarde ou entre em contato com o suporte");
		;

		ApiResponse<String> response = buildResponse(errors, CodeException.GENERAL);

		return new ResponseEntity<ApiResponse<String>>(response, HttpStatus.OK);
	}

	private ApiResponse<String> buildResponse(Map<String, String> errors, CodeException code) {

		String errorsJson = null;

		errorsJson = errorsToJson(errors);

		ApiResponse<String> response = new ApiResponse<>();
		response.setErrorTag(code.getDescription());
		response.setErrorMessage(errorsJson);
		response.setSuccess(false);

		return response;
	}

	/**
	 * Tranform a {@link Map} to json as String
	 * 
	 * @param errors - {@link Map} with erros
	 * @return {@link String} representing a json with all erros from Map argument
	 */
	private String errorsToJson(Map<String, String> errors) {
		String errorsJson = null;

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			errorsJson = objectMapper.writeValueAsString(errors);

			return errorsJson;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return errors.toString();
		}
	}
}
