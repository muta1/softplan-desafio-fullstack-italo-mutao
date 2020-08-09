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
import org.springframework.web.bind.annotation.RequestParam;
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
		return UserDTO.toDTOList(this.userService.getAllUsers());
	}

	@GetMapping(path = "/read")
	public UserDTO getUser(@RequestParam(name = "id") Long userId) {
		return UserDTO.toDTO(this.userService.getUser(userId));
	}

	@PostMapping(path = "/create")
	public UserDTO postUser(@RequestBody UserDTO user) {
		System.out.println("[USERRR]");
		System.out.println(user);
		return UserDTO.toDTO(this.userService.createUser(UserDTO.toEntity(user)));
	}

	@PutMapping(path = "/update")
	public UserDTO putUser(@RequestBody UserDTO user) {
		return UserDTO.toDTO(this.userService.updateUser(UserDTO.toEntity(user)));
	}

	@DeleteMapping(path = "/delete")
	public void deleteUser(@RequestParam(name = "id") Long userId) {
		this.userService.removeUser(userId);
	}
}
