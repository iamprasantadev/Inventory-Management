package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UserRepo;
@Service
public class UserService {
	@Autowired
	UserRepo userRepo;
	
	public boolean existsByUsername(String username) {
		return userRepo.existsByUsername(username);
	}

}
