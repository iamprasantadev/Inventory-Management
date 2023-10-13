package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.RolesDTO;
import com.example.demo.entity.Permissions;
import com.example.demo.entity.Roles;
import com.example.demo.entity.Status;
import com.example.demo.repository.PermissionsRepo;
import com.example.demo.repository.RoleRepo;

@Service
public class RolesService {

@Autowired
RoleRepo roleRepo;

@Autowired	
ModelMapper modelMapper;
@Autowired
PermissionsRepo permissionsRepo;

PropertyMap<RolesDTO,Roles>skipModifiedFieldsMap=new PropertyMap<RolesDTO,Roles>(){
	protected void configure() {
		skip().setPermissions(null);
	}
};

public void createrole(RolesDTO roledto){
	//modelMapper.addMappings(skipModifiedFieldsMap);
	Roles role= modelMapper.map(roledto,Roles.class);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		  LocalDateTime now = LocalDateTime.now();  
		  role.setCreated_at(dtf.format(now)); 
		  role.setUpdate_at(dtf.format(now));
		  role.setStatus(Status.active);
	  //Set<Permissions> permissions = new HashSet<>();
		/*
		 * if(roledto.getPermissions()!=null); Set<Permissions> permissions = new
		 * HashSet<>(); roledto.getPermissions().stream().forEach(per ->{ Optional
		 * <Permissions> permissionsList = permissionsRepo.findById(per.getId());
		 * if(permissionsList.isPresent()) {
		 * role.getPermissions().add(permissionsList.get()); } });
		 */	 
		 roleRepo.save(role);
       }
   public  List<RolesDTO> getAllRoles(Integer id){
	Optional<Permissions> permissionsoptional = permissionsRepo.findById(id);
	 if(permissionsoptional.isPresent()) {	
		 Permissions permissions = permissionsoptional.get();
		 Set<Roles>roleList =permissions.getRoles(); 
		 List<RolesDTO> roledtoList= modelMapper.map(roleList,new TypeToken<List<RolesDTO>>() {}.getType() );
		 return roledtoList;
	 }
	//List<Roles>roleList = roleRepo.findAll();	
	//List<RolesDTO> roledtoList= modelMapper.map(roleList,new TypeToken<List<RolesDTO>>() {}.getType() );
    // return roledtoList;
	return null;
     } 
   public List<RolesDTO>getAllRoles(){
	List<Roles>roleList = roleRepo.findAll();	
	List<RolesDTO> roledtoList= modelMapper.map(roleList,new TypeToken<List<RolesDTO>>() {}.getType() );
	return roledtoList; 
   }
   
	public RolesDTO getRolesById(Integer id) {
		 Optional<Roles> roles=roleRepo.findById(id);
		     if(roles.isPresent()) {
		    	 RolesDTO rolesdto= modelMapper.map(roles,RolesDTO.class);	 
			  return rolesdto;
		     }
		 return null;
	     }
			
	public String updaterole(RolesDTO rolesDTO) {
	    Optional<Roles> roleOptional = roleRepo.findById(rolesDTO.getId());

	    if (roleOptional.isPresent()) {
	        Roles role = roleOptional.get();
	        modelMapper.map(rolesDTO, role);
           DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			role.setUpdate_at(dtf.format(now));		
			roleRepo.save(role);
			return "Role Update Succesfully";
	}	
	    return "Role not updated ";
	    
	    }
	  
			  
			  	     
	public String inactiveRolesById( Integer id) {
	    Optional<Roles> roles = roleRepo.findById(id);
	    if(roles.isPresent()) {
	    	roles.get().setStatus(Status.inactive);
	    	roleRepo.save(roles.get());
	  	  return "Successfully Inactive";
	    }else
	  	  return "User already marked as a inactive";
	    }   
	
	
	
}
