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
import com.example.demo.repository.PermissionsRepo;

@Service
public class PermissionsService {
@Autowired    
PermissionsRepo permissionsRepo;
@Autowired
ModelMapper modelMapper;

public void createpermissions(PermissionsDTO permissionsdto) {
	Permissions permissions = modelMapper.map(permissionsdto, Permissions.class);
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	  LocalDateTime now = LocalDateTime.now();
	  permissions.setCreated_at(dtf.format(now));
	  permissions.setUpdated_at(dtf.format(now));
	  permissionsRepo.save(permissions);
      }

public  List<PermissionsDTO> getAllPermissions(){
	List<Permissions> permissionList=permissionsRepo.findAll();
	List<PermissionsDTO> permissionsdtoList= modelMapper.map(permissionList,new TypeToken<List<PermissionsDTO>>() {}.getType() );
     return permissionsdtoList;
     } 

public int deletepermissionById(Integer permissionsid) {
	Optional<Permissions> permissions = permissionsRepo.findById(permissionsid);
	if(permissions.isPresent()) {
		permissionsRepo.deleteById(permissionsid);
		return 0;
	}
	return 1;
  }
public PermissionsDTO getPermissionsById(Integer permissionsid) {
	 Optional<Permissions> permissions = permissionsRepo.findById(permissionsid);
	     if(permissions.isPresent()) {
	    	 PermissionsDTO permissiondto= modelMapper.map(permissions,PermissionsDTO.class);	 
		  return permissiondto;
	     }
	 return null;
    }




}
