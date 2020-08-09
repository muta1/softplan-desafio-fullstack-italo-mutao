package com.muta1.italomutao.exception;

public enum CodeException {
	GENERAL("UNKNOW ERROR", 1001), VALIDATION_FAIL("Falha na validacao", 1002),
	BAD_CREDENTIALS("Credenciais incorretas", 1003),
	PAGE_VALUE_NOT_ACCEPT("Falha na paginacao : page informado menor que zero", 1004),
	PAGE_SIZE_VALUE_NOT_ACCEPT("Falha na paginacao : pageSize informado menor que zero", 1005),
	GENDER_TYPE_NOT_FOUND("Genero nao encontrado", 2001);

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
