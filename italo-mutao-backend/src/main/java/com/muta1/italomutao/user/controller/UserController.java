package com.muta1.italomutao.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.muta1.italomutao.user.dto.UserDTO;
import com.muta1.italomutao.user.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping()
	public List<UserDTO> getAllUsers() {
		return this.userService.getAllUsers();
	}

	@PostMapping()
	public UserDTO postUser(@RequestBody UserDTO user) {
		return this.userService.createUser(user);
	}

	@PutMapping()
	public UserDTO putUser(@RequestBody UserDTO user) {
		return this.userService.updateUser(user);
	}

	@DeleteMapping()
	public void deleteUser(@RequestBody UserDTO user) {
		this.userService.removeUser(user);
	}
}
