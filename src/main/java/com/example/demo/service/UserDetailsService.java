package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.UserDetaildto;
import com.example.demo.entity.UserDetails;
import com.example.demo.repository.UserDetailsRepo;

@Service
public class UserDetailsService {
	@Autowired
	UserDetailsRepo userDetailsRepo;
	@Autowired
	ModelMapper modelMapper;
////	public String forgotPassword(String email) {
////		 
////		 Optional<UserDetails> userOptional=Optional.ofNullable(userDetailsRepo.findByEmail(email));
////		if(!userOptional.isPresent()) {
////			
////			UserDetails user=userOptional.get();
////			//user.setResetpasswordtoken(generateToken());
////			
////		}
////		return email;
////	 }
//      public void updateResetPassword(String token,String email) {
//    	  UserDetails userDetails=userDetailsRepo.findByEmail(email);
//    	  if(userDetails!=null) {
//    		  userDetails.setResetpasswordtoken(token);
//    		  userDetailsRepo.save(userDetails);
//    	    }
//    	  }
      public void createuser(UserDetaildto userdto){
    	  UserDetails user= modelMapper.map(userdto,UserDetails.class);
    		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    		  LocalDateTime now = LocalDateTime.now();  
    		 user.setCreated_at(dtf.format(now)); 
    		 userDetailsRepo.save(user);
    	       }
    	public  List<UserDetaildto> getAllUserdetail(){
    		List<UserDetails> UserdetailList=userDetailsRepo.findAll();	
    		List<UserDetaildto> userdetailDTOList= modelMapper.map(UserdetailList,new TypeToken<List<UserDetaildto>>() {}.getType() );
    	     return userdetailDTOList;
    	     } 
    	public UserDetaildto getUserdetailsById(Integer id) {
    		 Optional<UserDetails> user=userDetailsRepo.findById(id);
    		     if(user.isPresent()) {
    		    	 UserDetaildto userdto= modelMapper.map(user,UserDetaildto.class);	 
    			  return userdto;
    		     }
    		 return null;
    	     }
    	public String updateuser( UserDetails user) {				  		
    		Optional<UserDetails> update =userDetailsRepo.findById(user.getId());
    		 if(!update.isEmpty()&&update.isPresent()) {
    			update.get().setFirstname(user.getFirstname());
    			update.get().setLastname(user.getLastname());
    			update.get().setEmail(user.getEmail());
    			update.get().setMobile(user.getMobile());
    			userDetailsRepo.save(update.get());
    			 return "Update"; 			 
    		 }		 		
    		 return "Not Updated";		
    	     }
    	public int deleteUserById( Integer id) {
    	    Optional<UserDetails> user = userDetailsRepo.findById(id);
    	    if(user.isPresent()) {
    	    	userDetailsRepo.deleteById(id);
    	  	  return 0;
    	    }else
    	  	  return 1;
    	    }   
      
      }

