package com.muta1.italomutao.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muta1.italomutao.user.dto.UserDTO;

public interface UserRepository extends JpaRepository<UserDTO, Long>  {
	UserDTO findByName(String name);
}
