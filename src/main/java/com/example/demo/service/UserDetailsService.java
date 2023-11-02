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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
      public void createuser(UserDTO userdto){ 
    	  try {
    		  logger.debug("Inside createuser::"+userdto.toString());
    	  
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
        logger.debug("Completed createuser");
               }catch(Exception ex) {
   	    	   ex.printStackTrace();
   	    	logger.debug("Exception in createuser::"+ex.getMessage());
   	    	}      }
      	   
    	public  List<UserDTO> getAllUserdetail(){
    		try {
    			logger.info("GetAll User");
   		List<User> UserdetailList = userRepo.findAll();	
    		List<UserDTO> userdetailDTOList= modelMapper.map(UserdetailList,new TypeToken<List<UserDTO>>(){}.getType());    	     
    		for(UserDTO dto:userdetailDTOList) {
    			dto.getUserDetail().setTitle(UserdetailList.stream()
    					.filter(user->user.getUserDetail().getId()==dto.getUserDetail().getId())
    					.findAny().get().getUserDetail().getRoles().getTitle());
   		     } return userdetailDTOList;
   		     } catch(Exception ex) {
     	    	   ex.printStackTrace();
     	    	  logger.error(ex.getMessage());   				
    		   }return null;
    		   }
    	
    	public UserDetailDTO getUserdetailsById(Integer id) {
    		try {
    			logger.info("View User By Id");
    		 Optional<UserDetail> user=userDetailsRepo.findById(id);
    		     if(user.isPresent()) {
    		    	 UserDetailDTO userdto= modelMapper.map(user,UserDetailDTO.class);	 
    			  return userdto;
    		     } } catch(Exception ex) {
       	    	   ex.printStackTrace();
       	    	logger.error(ex.getMessage());
    		     } return null;
    	     }
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)			
   	public UserDTO updateuser( UserDTO userDTO) {
   		try {
   			logger.debug("Inside updateuser::"+userDTO.toString());
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
    		 logger.debug("Completed updateuser");
    		 userDTO=modelMapper.map(user, UserDTO.class);
    		 return userDTO; 
    	 }           }catch(Exception ex) {
    	   	    	ex.printStackTrace();
    	   	    	logger.debug("Exception in updateuser"+ex.getMessage());	 		
    	 } return null;		
   	}
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)	
    	 public String deleteUserById( Integer id) {
    		 try {
    	 logger.debug("Inside inactiveuser");
   		  Optional<UserDetail> user = userDetailsRepo.findById(id);		  
   	   if(user.isPresent()) {
   		   user.get().setStatus(Status.inactive);
   		userDetailsRepo.save(user.get());
   		logger.debug("Completed inactiveuser");
   	    	return "Successfully Deleted";	    	
   	  	  }	   
   	    }catch(Exception ex) {
   	    	ex.printStackTrace();
   	    	logger.debug("Exception in inactiveuser::"+ex.getMessage());
   	    }
    		 return "User could not be found";
     }
    	 
    	 
    	 public List<UserDTO> getAllActiveUsers() {
    		 try {
    			 logger.debug("Get All Active User");
    		    List<User> users = userRepo.findAll();
    		    return users.stream()
    		            .filter(user -> user.getUserDetail().getStatus() == Status.active)
    		            .map(user -> {
    		                UserDTO userDTO = modelMapper.map(user, UserDTO.class);
    		                userDTO.getUserDetail().setTitle(user.getUserDetail().getRoles().getTitle());
    		                return userDTO;
    		            })
    		            .collect(Collectors.toList());
    		    }catch(Exception ex) {
    		       	    	ex.printStackTrace();
    		       	    	logger.error(ex.getMessage());
    		       	    }
			return null;
    		 }

}
