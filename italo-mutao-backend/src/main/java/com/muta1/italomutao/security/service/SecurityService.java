package com.muta1.italomutao.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.muta1.italomutao.user.dto.UserDTO;
import com.muta1.italomutao.user.repository.UserRepository;

@Component
public class SecurityService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    	UserDTO user = this.userRepository.findByName(username);
    	
        if (user == null){
            throw new UsernameNotFoundException(username + " não encontrado!");
        }
        org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRole())
        );
		return userDetails;
    }
    
    
    public UserDTO getLoggedUser() {
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	UserDTO ret = this.userRepository.findByName(userDetails.getUsername());
		return ret ;
    }
}