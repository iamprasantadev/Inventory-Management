package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDetailDTO;
import com.example.demo.entity.Roles;
import com.example.demo.entity.User;
import com.example.demo.entity.UserDetail;
import com.example.demo.repository.RoleRepo;
import com.example.demo.repository.UserDetailsRepo;
import com.example.demo.repository.UserRepo;

@Service
public class UserDetailsService {
	@Autowired
	UserDetailsRepo userDetailsRepo;
	@Autowired
	UserRepo userRepo;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepo rolesRepo;
      public void createuser(UserDetailDTO userdto){
    	  Optional<User> userOptional = userRepo.findById(userdto.getUserid());
    	  Optional<Roles> rolesOptional= rolesRepo.findById(userdto.getRoleid());
    	  if(userOptional.isPresent()&& rolesOptional.isPresent() ) {
    		  User userList = userOptional.get();
    		  Roles rolesList = rolesOptional.get();
    		  UserDetail userDetailList = new UserDetail();
    		  userDetailList.setFirstname(userdto.getFirstname());
    		  userDetailList.setLastname(userdto.getLastname());
    		  userDetailList.setEmail(userdto.getEmail());
    		  userDetailList.setMobile(userdto.getMobile());
    		  userDetailList.setStatus(userdto.getStatus());
    		  userDetailList.setPassword(passwordEncoder.encode(userdto.getPassword()));
    		  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    		  LocalDateTime now = LocalDateTime.now();  
    		  userDetailList.setCreated_at(dtf.format(now));
    		  userDetailList.setUser(userList);
    		  userDetailList.setRoles(rolesList);
    		  userDetailsRepo.save(userDetailList);
    	  }
    	    		
    }
    	public  List<UserDetailDTO> getAllUserdetail(){
    		List<UserDetail> UserdetailList=userDetailsRepo.findAll();	
    		List<UserDetailDTO> userdetailDTOList= modelMapper.map(UserdetailList,new TypeToken<List<UserDetailDTO>>() {}.getType() );
    	     return userdetailDTOList;
    	     } 
    	public UserDetailDTO getUserdetailsById(Integer id) {
    		 Optional<UserDetail> user=userDetailsRepo.findById(id);
    		     if(user.isPresent()) {
    		    	 UserDetailDTO userdto= modelMapper.map(user,UserDetailDTO.class);	 
    			  return userdto;
    		     }
    		 return null;
    	     }
    	public String updateuser( UserDetailDTO userDetailsDTO) {				  		
    		Optional<UserDetail> userOptional =userDetailsRepo.findById(userDetailsDTO.getId());
    		 if(userOptional.isPresent()) {
    			 UserDetail user=userOptional.get();
    			 user.setFirstname(userDetailsDTO.getFirstname());
    			 user.setLastname(userDetailsDTO.getLastname());
    			 user.setMobile(userDetailsDTO.getMobile());
    			 user.setEmail(userDetailsDTO.getEmail());
    			 userDetailsRepo.save(user);
    			
    			 return "Update"; 			 
    		 }		 		
    		 return "Not Updated";		
    	     }
    	public int deleteUserById( Integer id) {
    		  Optional<UserDetail> user = userDetailsRepo.findById(id);
    	   if(user.isPresent()) {
    	    	userDetailsRepo.deleteById(id);
    	  	  return 0;
    	    }else
    	  	  return 1;
    	    }   
    	
    

}


