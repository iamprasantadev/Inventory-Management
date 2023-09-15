package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserDetails;
import com.example.demo.repository.UserDetailsRepo;

@Service
public class UserDetailsService {
	@Autowired
	UserDetailsRepo userDetailsRepo;
	
//	public String forgotPassword(String email) {
//		 
//		 Optional<UserDetails> userOptional=Optional.ofNullable(userDetailsRepo.findByEmail(email));
//		if(!userOptional.isPresent()) {
//			
//			UserDetails user=userOptional.get();
//			//user.setResetpasswordtoken(generateToken());
//			
//		}
//		return email;
//	 }
      public void updateResetPassword(String token,String email) {
    	  UserDetails userDetails=userDetailsRepo.findByEmail(email);
    	  if(userDetails!=null) {
    		  userDetails.setResetpasswordtoken(token);
    		  userDetailsRepo.save(userDetails);
    	  }
    	  }
    	  
      }

