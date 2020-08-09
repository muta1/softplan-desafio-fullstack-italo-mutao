package com.muta1.italomutao.user.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class UserDTO {
	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "password")
	private String password;
	@Column(name = "role")
	private String role;
}
