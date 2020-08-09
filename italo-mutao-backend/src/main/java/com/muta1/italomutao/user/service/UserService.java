package com.muta1.italomutao.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.muta1.italomutao.user.dto.UserDTO;
import com.muta1.italomutao.user.repository.UserRepository;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    public List<UserDTO> getAllUsers(){
    	return this.userRepository.findAll();
    }

}
