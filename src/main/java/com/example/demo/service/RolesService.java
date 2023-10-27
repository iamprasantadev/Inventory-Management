package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public static final Logger LOGGER = LoggerFactory.getLogger(RolesService.class);
//PropertyMap<RolesDTO,Roles>skipModifiedFieldsMap=new PropertyMap<RolesDTO,Roles>(){
//	protected void configure() {
//		skip().setPermissions(null);
//	}
//};

public void createrole(RolesDTO rolesDTO){
	try {
		LOGGER.info("Create Role");
	Roles newRole = new Roles();
    newRole.setTitle(rolesDTO.getTitle());
    newRole.setDescription(rolesDTO.getDescription());
    newRole.setStatus(Status.active);
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();
	newRole.setCreated_at(dtf.format(now));
	newRole.setUpdate_at(dtf.format(now));
    Set<Permissions> permissions = rolesDTO.getPermissions()
        .stream()
        .map(permissionDTO -> permissionsRepo.findById(permissionDTO.getId())
            .orElse(null))
        .filter(Objects::nonNull)
        .collect(Collectors.toSet());
    newRole.setPermissions(permissions); 
    roleRepo.save(newRole); 
    }catch(Exception ex) {
    	   ex.printStackTrace();
	    	    LOGGER.error(ex.getMessage());  
 }  }
	 
  public  List<RolesDTO> getAllRoles(Integer id){
  Optional<Permissions> permissionsoptional = permissionsRepo.findById(id);
	 if(permissionsoptional.isPresent()) {	
		 Permissions permissions = permissionsoptional.get();
		 Set<Roles>roleList =permissions.getRoles(); 
		 List<RolesDTO> roledtoList= modelMapper.map(roleList,new TypeToken<List<RolesDTO>>() {}.getType() );
		 return roledtoList;
	 }
	
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
			
	public String updateRole(RolesDTO rolesDTO) {
	    Optional<Roles> roleOptional = roleRepo.findById(rolesDTO.getId());
	    
	    if (!roleOptional.isPresent()) {	        
	    }	    
	    Roles role = roleOptional.get();	    
	    role.setTitle(rolesDTO.getTitle());
	    role.setDescription(rolesDTO.getDescription());
	    role.setStatus(Status.active);
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		role.setUpdate_at(dtf.format(now));
	    Set<Permissions> permissions = rolesDTO.getPermissions()
	            .stream()
	            .map(permissionDTO -> permissionsRepo.findById(permissionDTO.getId())
	                .orElse(null))
	            .filter(Objects::nonNull)
	            .collect(Collectors.toSet());
	        role.setPermissions(permissions); 
	    roleRepo.save(role);
	    
	    return "Role updated successfully";
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
		
	

