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

import com.muta1.italomutao.api.ApiResponse;
import com.muta1.italomutao.user.dto.UserDTO;
import com.muta1.italomutao.user.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping()
	public ApiResponse<List<UserDTO>> getAllUsers() {
		ApiResponse<List<UserDTO>> ret = new ApiResponse<>();
		ret.setResponse(UserDTO.toDTOList(this.userService.getAllUsers()));
		return ret;
	}

	@GetMapping(path = "/read")
	public ApiResponse<UserDTO> getUser(@RequestParam(name = "id") Long userId) {
		ApiResponse<UserDTO> ret = new ApiResponse<>();
		ret.setResponse(UserDTO.toDTO(this.userService.getUser(userId)));
		return ret;
	}

	@PostMapping(path = "/create")
	public ApiResponse<UserDTO> postUser(@RequestBody UserDTO user) {
		System.out.println("[USERRR]");
		System.out.println(user);
		ApiResponse<UserDTO> ret = new ApiResponse<>();
		ret.setResponse(UserDTO.toDTO(this.userService.createUser(UserDTO.toEntity(user))));
		return ret;
	}

	@PutMapping(path = "/update")
	public ApiResponse<UserDTO> putUser(@RequestBody UserDTO user) {
		ApiResponse<UserDTO> ret = new ApiResponse<>();
		ret.setResponse(UserDTO.toDTO(this.userService.updateUser(UserDTO.toEntity(user))));
		return ret;
	}

	@DeleteMapping(path = "/delete")
	public ApiResponse<Boolean> deleteUser(@RequestParam(name = "id") Long userId) {
		ApiResponse<Boolean> ret = new ApiResponse<Boolean>();	
		return ret;
	}
}
