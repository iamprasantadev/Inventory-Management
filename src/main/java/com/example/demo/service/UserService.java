package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	 
//	 public String resetPassword(Long userId, String answer, String newPassword) {
//	        User user = userRepo.findById(userId).orElse(null);
////	        if (user != null) {
////	            if (user.getAnswer().equals(answer)) {
////	                // Hash the new password before saving it (use a proper password hashing library)
////	                user.setPassword(newPassword);
////	                userRepo.save(user);
////	                return "Password reset successfully.";
////	            } else {
////	                return "Invalid security answer.";
////	            }
////	        } else {
////	            return "User not found.";
////	        }
//	    }
	
}

	 
	 

