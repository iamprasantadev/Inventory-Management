package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Roles;
import com.example.demo.entity.Status;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepo;
import com.example.demo.repository.UserRepo;
@Service
public class UserService {
	@Autowired
	UserRepo userRepo;
	@Autowired
	RoleRepo roleRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	ModelMapper modelMapper;
	
	private static final long EXPIRE_TOKEN_AFTER_MINUTES = 60;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	public Boolean existsByUsername(String username) {
		return userRepo.existsByUsername(username);
	}
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)	
	 public User saveUser(UserDTO userdto) {
		try {
			logger.debug("Inside userregistration::"+userdto.toString());
			  User user= modelMapper.map(userdto, User.class);
			  user.getUserDetail().setStatus(Status.active); 
			  Roles role=roleRepo.findByTitle("Admin"); 
              user.getUserDetail().setRoles(role);
			  user.setPassword(passwordEncoder.encode(user.getPassword()));
			  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
     		  LocalDateTime now = LocalDateTime.now();  
     		  user.setCreated_at(dtf.format(now));
     		  user.setUpdate_at(dtf.format(now));
     		  user.getUserDetail().setCreated_at(dtf.format(now));
     		  user.getUserDetail().setUpdate_at(dtf.format(now));
	        return  userRepo.save(user);
		}catch(Exception ex) {
			logger.debug("Exception in registrionuser::"+ex.getMessage());
		}
		return null;
	    }
	 
		/*
		 * public void lastLogin(UserDTO userDTO) { //User
		 * user=userRepo.findByUserid(userDTO.getUserid()); User user=null;
		 * if(user!=null) { DateTimeFormatter dtf =
		 * DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); LocalDateTime now =
		 * LocalDateTime.now(); user.setLastlogin(dtf.format(now)); userRepo.save(user);
		 * }
		 */

    	
	 //}
	 


	 
	 
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

		/*
		 * public String deleteUserById( Integer id) { Optional<User> user =
		 * userRepo.findById(id); if(user.isPresent()) {
		 * user.get().getUserDetail().setStatus(Status.inactive);
		 * userRepo.save(user.get()); return "Successfully Deleted"; } return
		 * "User could not be found"; }
		 */
      }

	 
	 

