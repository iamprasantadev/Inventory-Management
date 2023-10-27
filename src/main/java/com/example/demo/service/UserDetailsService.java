package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
    	  try {
    		  LOGGER.info("Create User");
    	  
   	  User user = modelMapper.map(userdto, User.class);   	       
      	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    LocalDateTime now = LocalDateTime.now();  
	    user.setCreated_at(dtf.format(now)); 
	    user.setUpdate_at(dtf.format(now));		
    	user.getUserDetail().setStatus(Status.active);	
    	Roles role = rolesRepo.findById(userdto.getUserDetail().getRoles().getId()).get();
    	user.getUserDetail().setRoles(role);
    	//user.getUserDetail().setStatus(Status.active);
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
    	user.getUserDetail().setCreated_at(dtf.format(now));
    	user.getUserDetail().setUpdate_at(dtf.format(now));
        userRepo.save(user);
               }catch(Exception ex) {
   	    	   ex.printStackTrace();
   	    	    LOGGER.error(ex.getMessage());
   	    	}      }
      	   
    	public  List<UserDTO> getAllUserdetail(){
    		try {
    			LOGGER.info("GetAll User");
   		List<User> UserdetailList = userRepo.findAll();	
    		List<UserDTO> userdetailDTOList= modelMapper.map(UserdetailList,new TypeToken<List<UserDTO>>(){}.getType());    	     
    		for(UserDTO dto:userdetailDTOList) {
    			dto.getUserDetail().setTitle(UserdetailList.stream()
    					.filter(user->user.getUserDetail().getId()==dto.getUserDetail().getId())
    					.findAny().get().getUserDetail().getRoles().getTitle());
   		     } return userdetailDTOList;
   		     } catch(Exception ex) {
     	    	   ex.printStackTrace();
      	    	    LOGGER.error(ex.getMessage());   				
    		   }return null;
    		   }
    	
    	public UserDetailDTO getUserdetailsById(Integer id) {
    		try {
    			LOGGER.info("View User By Id");
    		 Optional<UserDetail> user=userDetailsRepo.findById(id);
    		     if(user.isPresent()) {
    		    	 UserDetailDTO userdto= modelMapper.map(user,UserDetailDTO.class);	 
    			  return userdto;
    		     } } catch(Exception ex) {
       	    	   ex.printStackTrace();
     	    	    LOGGER.error(ex.getMessage());
    		     } return null;
    	     }
    			
   	public UserDTO updateuser( UserDTO userDTO) {
   		try {
   			LOGGER.info("Update BY Id");
   		User userEntity = modelMapper.map(userDTO,User.class);
    	Optional<User> userOptional = userRepo.findById(userDTO.getUserid());
    	 if(userOptional.isPresent()) {
    		//User userEntity = modelMapper.map(userDTO,User.class);
    		 modelMapper.map(userEntity, userOptional.get());
    		 User user = userOptional.get();
    		 Roles role = rolesRepo.findById(userDTO.getUserDetail().getRoles().getId()).get();
    	     user.getUserDetail().setRoles(role);
    	     DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    		 LocalDateTime now = LocalDateTime.now();
    		 user.getUserDetail().setCreated_at(dtf.format(now));
    		 user.getUserDetail().setUpdate_at(dtf.format(now));
    		 user.setPassword(passwordEncoder.encode(user.getPassword()));
    		 user.getUserDetail().setStatus(Status.active);
    		 user=userRepo.save(user); 
    		 userDTO=modelMapper.map(user, UserDTO.class);
    		 return userDTO; 
    	 }           }catch(Exception ex) {
    	   	    	ex.printStackTrace();
    	   	    	LOGGER.error(ex.getMessage());	 		
    	 } return null;		
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
    	 
    	 
    	 public List<UserDTO> getAllActiveUsers() {
    		 try {
    			 LOGGER.info("Get All Active User");
    		    List<User> users = userRepo.findAll();
    		    return users.stream()
    		            .filter(user -> user.getUserDetail().getStatus() == Status.active)
    		            .map(user -> {
    		                UserDTO userDTO = modelMapper.map(user, UserDTO.class);
    		                userDTO.getUserDetail().setTitle(user.getUserDetail().getRoles().getTitle());
    		                return userDTO;
    		            })
    		            .collect(Collectors.toList());}catch(Exception ex) {
    		       	    	ex.printStackTrace();
    		       	    	LOGGER.error(ex.getMessage());
    		       	    }
			return null;
    		 }

}
