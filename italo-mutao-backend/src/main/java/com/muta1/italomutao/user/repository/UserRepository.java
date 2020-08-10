package com.muta1.italomutao.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muta1.italomutao.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long>  {
	User findByName(String name);	
}
