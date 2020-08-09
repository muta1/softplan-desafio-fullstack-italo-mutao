package com.muta1.italomutao.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
}
