package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
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
public class PermissionsService {
	@Autowired
	PermissionsRepo permissionsRepo;
	@Autowired
	RoleRepo roleRepo;
	@Autowired
	ModelMapper modelMapper;

 public void createpermissions(RolesDTO rolesdto) {		 
  Optional <Roles> role = roleRepo.findById(rolesdto.getId());
  if(role.isPresent()){
  List<Permissions> permissions= modelMapper.map(rolesdto.getPermissions(),new TypeToken<List<Permissions>>() {}.getType() );
     permissions.stream().forEach(perm->{			
			  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			  LocalDateTime now = LocalDateTime.now(); 
			  perm.setCreated_at(dtf.format(now));
			  perm.setUpdated_at(dtf.format(now));
			  perm.setStatus(Status.active);			 
     });
     role.get().getPermissions().addAll(permissions);	 
     roleRepo.save(role.get()); 

  }
	  
    }

	public List<PermissionsDTO> getAllPermissions() {
		List<Permissions> permissionList = permissionsRepo.findAll();
		List<PermissionsDTO> permissionsdtoList = modelMapper.map(permissionList,
				new TypeToken<List<PermissionsDTO>>() {
				}.getType());
		return permissionsdtoList;
	}

	public int deletepermissionById(Integer id) {
		Optional<Permissions> permissions = permissionsRepo.findById(id);
		if (permissions.isPresent()) {
			 permissions.get().setStatus(Status.inactive);
			permissionsRepo.save(permissions.get());
			return 0;
		}
		return 1;
	}

	public PermissionsDTO getPermissionsById(Integer id) {
		Optional<Permissions> permissions = permissionsRepo.findById(id);
		if (permissions.isPresent()) {
			PermissionsDTO permissiondto = modelMapper.map(permissions, PermissionsDTO.class);
			return permissiondto;
		}
		return null;
	}

	public void updatepermissions (RolesDTO rolesdto) {
	//Roles rolesEntity = modelMapper.map(rolesdto, Roles.class);
	Optional<Roles> roleoptional = roleRepo.findById(rolesdto.getId());	
	if(roleoptional.isPresent()) {
		Roles roles = roleoptional.get();
		rolesdto.getPermissions().forEach(permissionsdto->{
		Optional<Permissions>permissions = roles.getPermissions().stream().filter(perm->
		perm.getId().equals(permissionsdto.getId())).findAny();	
			permissions.get().setDescription(permissionsdto.getDescription());
			permissions.get().setTitle(permissionsdto.getTitle());
			permissions.get().setStatus(Status.active);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			LocalDateTime now = LocalDateTime.now(); 
			permissions.get().setUpdated_at(dtf.format(now));
			roleRepo.save(roles);
		});
		
	}
		
		
		
		
	}
	
	
	
}
