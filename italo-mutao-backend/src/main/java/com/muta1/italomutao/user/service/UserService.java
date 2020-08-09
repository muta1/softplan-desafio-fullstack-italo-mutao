package com.muta1.italomutao.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muta1.italomutao.exception.CodeException;
import com.muta1.italomutao.exception.ExceptionValidation;
import com.muta1.italomutao.exception.ServiceException;
import com.muta1.italomutao.user.dto.UserDTO;
import com.muta1.italomutao.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<UserDTO> getAllUsers() {
		return this.userRepository.findAll();
	}

	public UserDTO createUser(UserDTO user) throws ExceptionValidation, ServiceException {
		if (user == null) {
			throw new ServiceException("Nao foi possivel criar o registro person pois o mesmo esta nulo",
					CodeException.GENERAL);
		}

		return this.userRepository.save(user);
	}

	public UserDTO updateUser(UserDTO user) {
		if (user == null) {
			throw new ServiceException("Update user fail, user must not be null.", CodeException.GENERAL);
		}

		if (user.getId() == null) {
			throw new ExceptionValidation("id", "Update user fail, id must not be null.");
		}

		UserDTO userFromDb = userRepository.getOne(user.getId());
		// crush the variables of the object found
		userFromDb.setName(user.getName());
		userFromDb.setPassword(user.getPassword());
		userFromDb.setRole(user.getRole());

		return userRepository.save(userFromDb);
	}

	public void removeUser(UserDTO user) {
		if (user == null) {
			throw new ServiceException("Delete user fail, user must not be null.", CodeException.GENERAL);
		}

		this.userRepository.delete(user);
	}

}
