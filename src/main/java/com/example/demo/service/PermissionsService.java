package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.PermissionsDTO;
import com.example.demo.entity.Permissions;
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

 public void createpermissions(PermissionsDTO permissonsdto) {
	    Permissions permissions = modelMapper.map(permissonsdto, Permissions.class);
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); 
		LocalDateTime now = LocalDateTime.now();
		permissions.setCreated_at(dtf.format(now));
		permissions.setUpdated_at(dtf.format(now));
		permissions.setStatus(Status.active);
	    permissionsRepo.save(permissions);
		  
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

	public String updatepermissions (PermissionsDTO permissionsdto) {
		Permissions permission=modelMapper.map(permissionsdto,Permissions.class);
			Optional<Permissions> permissions = permissionsRepo.findById(permissionsdto.getId());
			
			 if(!permissions.isEmpty()&&permissions.isPresent()) {				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); 
				LocalDateTime now = LocalDateTime.now();
				permission.setStatus(Status.active);
				permission.setCreated_at(dtf.format(now));
				permission.setUpdated_at(dtf.format(now));
				permissionsRepo.save(permission);
				 return "Update Succesfully";
			 }
			 return "Not update";

		
	}		
	   }
			
  
