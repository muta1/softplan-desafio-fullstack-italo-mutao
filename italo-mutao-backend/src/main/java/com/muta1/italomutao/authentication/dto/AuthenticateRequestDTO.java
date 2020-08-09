package com.muta1.italomutao.authentication.dto;

import lombok.Data;

@Data
public class AuthenticateRequestDTO {
	private String username;
	private String password;
}
