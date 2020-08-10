package com.muta1.italomutao.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muta1.italomutao.exception.FormException;
import com.muta1.italomutao.exception.ValidationException;
import com.muta1.italomutao.user.entity.User;
import com.muta1.italomutao.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}

	public User getUser(Long id) {
		return this.userRepository.getOne(id);
	}

	public User createUser(User user) {
		if (user == null) {
			throw new ValidationException("Nao foi possivel criar o registro person pois o mesmo esta nulo");
		}
		// bcrypt password
		user.setPassword(User.PASSWORD_ENCODER.encode(user.getPassword()));
		return this.userRepository.save(user);
	}

	public User updateUser(User user) {
		if (user == null) {
			throw new ValidationException("Update user fail, user must not be null.");
		}

		if (user.getId() == null) {
			throw new FormException("id", "Update user fail, id must not be null.");
		}

		User userFromDb = userRepository.getOne(user.getId());
		// crush the variables of the object found
		userFromDb.setName(user.getName());
		userFromDb.setPassword(user.getPassword());
		userFromDb.setRole(user.getRole());

		return userRepository.save(userFromDb);
	}

	public void removeUser(Long id) {
		if (id == null) {
			throw new ValidationException("Delete user fail, id must not be null.");
		}

		this.userRepository.deleteById(id);
	}

}
