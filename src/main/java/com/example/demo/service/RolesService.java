package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PermissionsDTO;
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

//PropertyMap<RolesDTO,Roles>skipModifiedFieldsMap=new PropertyMap<RolesDTO,Roles>(){
//	protected void configure() {
//		skip().setPermissions(null);
//	}
//};

public void createrole(RolesDTO rolesDTO){	
//	Optional<Permissions>permissions =permissionsRepo.findById(permissionsdto.getId());
//	if(permissions.isPresent()) {
//		List<Roles>role = modelMapper.map(permissionsdto.getRoles(),new TypeToken<List<Roles>>() {}.getType());
//		role.stream().forEach(rol->{
//			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//			LocalDateTime now = LocalDateTime.now();	
//			rol.setCreated_at(dtf.format(now));
//			rol.setUpdate_at(dtf.format(now));
//			rol.setStatus(Status.active);
//		});
//		permissions.get().getRoles().addAll(role);
//		permissionsRepo.save(permissions.get());
//	}
	
	Roles newRole = new Roles();
    newRole.setTitle(rolesDTO.getTitle());
    newRole.setDescription(rolesDTO.getDescription());

    // Create a set of Permissions by fetching them using their IDs
    //Set<Permissions> permissions = rolesDTO.getPermissions()
        .stream()
        .map(permissionDTO -> permissionsRepo.findById(permissionDTO.getId())
            .orElse(null))
        .filter(Objects::nonNull)
        .collect(Collectors.toSet());

    newRole.setPermissions(permissions);

    // Save the new role
    roleRepo.save(newRole);

    //return "Role created";
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
	//return null;
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
//	public void updaterole(RolesDTO rolesDTO) {
//		Optional<Roles> roleoptional = roleRepo.findById(rolesDTO.getId());	
//		if(roleoptional.isPresent()) {
//			Roles roles = roleoptional.get();
//			roles.setTitle(rolesDTO.getTitle());
//			roles.setDescription(rolesDTO.getDescription());
//			rolesDTO.getPermissions().forEach(permissionsdto->{
//			//Optional<Permissions>permissions = roles.getPermissions().stream().filter(perm->
//			perm.getId().equals(permissionsdto.getId())).findAny();	
//			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//			LocalDateTime now = LocalDateTime.now(); 
//			permissions.get().setUpdated_at(dtf.format(now));
//				roleRepo.save(roles);
//			});
			
		//}
			
			
			
			
		}
		
	
//}
