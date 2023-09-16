package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.Rolesdto;
import com.example.demo.entity.Roles;
import com.example.demo.repository.RoleRepo;

@Service
public class RolesService {

@Autowired
RoleRepo roleRepo;

@Autowired	
ModelMapper modelMapper;
	
public void createrole(Rolesdto roledto){
	Roles role= modelMapper.map(roledto,Roles.class);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		  LocalDateTime now = LocalDateTime.now();  
		  role.setCreated_at(dtf.format(now)); 
		  role.setUpdate_at(dtf.format(now));
		 roleRepo.save(role);
	       }
   public  List<Rolesdto> getAllRoles(){
	Iterable<Roles>roleist = roleRepo.findAll();	
	List<Rolesdto> roledtoList= modelMapper.map(roleist,new TypeToken<List<Rolesdto>>() {}.getType() );
     return roledtoList;
     } 
	public Rolesdto getRolesById(Integer id) {
		 Optional<Roles> user=roleRepo.findById(id);
		     if(user.isPresent()) {
		    	 Rolesdto userdto= modelMapper.map(user,Rolesdto.class);	 
			  return userdto;
		     }
		 return null;
	     }
	public String updaterole( Roles roles) {				  		
		Optional<Roles> update =roleRepo.findById(roles.getRoleid());
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		  LocalDateTime now = LocalDateTime.now();
		  roles.setUpdate_at(dtf.format(now));
		 if(!update.isEmpty()&&update.isPresent()) {
			update.get().setTitle(roles.getTitle());
			update.get().setDescription(roles.getDescription());
			update.get().setActive(roles.getActive());	  
			roleRepo.save(update.get());		
			 return "Update"; 			 
		 }		 		
		 return "Not Updated";		
	     }
	public int deleteRolesById( Integer roleid) {
	    Optional<Roles> roles = roleRepo.findById(roleid);
	    if(roles.isPresent()) {
	    	roleRepo.deleteById(roleid);
	  	  return 0;
	    }else
	  	  return 1;
	    }   
	
	
	
}
