package com.muta1.italomutao.authentication.dto;

import lombok.Data;

@Data
public class AuthenticateResponseDTO {
	private Long id;
	private Boolean logged;
	private String username;
	private String token;
	private String sessionCookieKey;
	private String sessionCookieValue;
	private String role;

	public AuthenticateResponseDTO() {
		super();
	}

	public AuthenticateResponseDTO(Boolean logged) {
		super();
		this.logged = logged;
	}
}
