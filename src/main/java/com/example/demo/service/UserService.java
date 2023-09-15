package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;
@Service
public class UserService {
	@Autowired
	UserRepo userRepo;
	//@Autowired
	//RoleRepo roleRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;
	
	public Boolean existsByUsername(String username) {
		return userRepo.existsByUsername(username);
	}
	
	 public User saveUser(User user) {
	       user.setUsername(user.getUsername());
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	        //user.setLastlogin(user.getLastlogin());
	        return userRepo.save(user);
	    }
	 
	 
	 
	 
}
