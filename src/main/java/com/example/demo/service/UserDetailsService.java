package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserDetailDTO;
import com.example.demo.entity.Roles;
import com.example.demo.entity.Status;
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
    public static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsService.class);
    
      public void createuser(UserDTO userdto){    	  
   	  User user = modelMapper.map(userdto, User.class);   	       
      	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    LocalDateTime now = LocalDateTime.now();  
	    user.setCreated_at(dtf.format(now)); 
	    user.setUpdate_at(dtf.format(now));		
    	user.getUserDetail().setStatus(Status.active);
    	//userdetail.setStatus(Status.inactive);
    	Roles role = rolesRepo.findByTitle(userdto.getUserDetails().getTitle());
    	user.getUserDetail().setRoles(role); 
    	user.getUserDetail().setCreated_at(dtf.format(now));
    	user.getUserDetail().setUpdate_at(dtf.format(now));
        userRepo.save(user);
    	  }
      	   
    	public  List<UserDetailDTO> getAllUserdetail(){    			    		
   		List<UserDetail> UserdetailList=userDetailsRepo.findAll();	
    		List<UserDetailDTO> userdetailDTOList= modelMapper.map(UserdetailList,new TypeToken<List<UserDetailDTO>>() {}.getType() );    	     
    		for(UserDetailDTO dto:userdetailDTOList) {
    			dto.setTitle(UserdetailList.stream()
    					.filter(user->user.getId()==dto.getId())
    					.findAny().get().getRoles().getTitle());
   		 }        
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
   		try {
   			LOGGER.info("Update BY Id");   		
    	Optional<UserDetail> userOptional =userDetailsRepo.findById(userDetailsDTO.getId());
    	 if(userOptional.isPresent()) {
    		 UserDetail user=userOptional.get();
    		 user.setFirstname(userDetailsDTO.getFirstname());
    		 user.setLastname(userDetailsDTO.getLastname());
    		 user.setMobile(userDetailsDTO.getMobile());
    		 user.setEmail(userDetailsDTO.getEmail());
    		 user.setStatus(userDetailsDTO.getStatus());
    		 userDetailsRepo.save(user);    			
    		 return "Update"; 
    	 }           }catch(Exception ex) {
    	   	    	ex.printStackTrace();
    	   	    	LOGGER.error(ex.getMessage());	 		
    	 } return "Not Updated";		
   	}
   			
    	 public String deleteUserById( Integer id) {
    		 try {
    	 LOGGER.info("Inactive User By Id");
   		  Optional<UserDetail> user = userDetailsRepo.findById(id);		  
   	   if(user.isPresent()) {
   		   user.get().setStatus(Status.inactive);
   		userDetailsRepo.save(user.get());	    	
   	    	return "Successfully Deleted";	    	
   	  	  }	   
   	    }catch(Exception ex) {
   	    	ex.printStackTrace();
   	    	LOGGER.error(ex.getMessage());
   	    }
    		 return "User could not be found";
     }
}


