package com.example.demo.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;
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
	 


	 
	 
	 public UUID forgotpassword(UserDTO userDTO) {
		 Optional<User> userOptional=Optional.ofNullable(userRepo.findByUsername(userDTO.getUsername()));
		 if(userOptional.isPresent()) {
			 User user=userOptional.get();
			 UUID uuid = UUID.randomUUID();
			 user.setCode(uuid);
			 
			 userRepo.save(user);
			 return uuid;
		 }else {
		        throw new IllegalArgumentException("User not found."); 
		    }
	 }
	 
	 public  void updatePassword(UserDTO userDTO) {
		 User user=userRepo.findByUsername(userDTO.getUsername());
		 if(user!=null) {
			 UUID previousUUID=user.getCode();
			 String currentUUID=userDTO.getCode();
			 
			 if(previousUUID != null && previousUUID.toString().equals(currentUUID.toString())) {
				 user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
				 userRepo.save(user);
			 }
		 }
	 }

	
	    }

	


	 
	 

