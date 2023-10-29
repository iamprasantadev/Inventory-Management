package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
	
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

 @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
 public void createPermissions(PermissionsDTO permissonsdto) {
	 
	 try {
	 	logger.debug("Inside createPermission :: "+permissonsdto.toString());
	    Permissions permissions = modelMapper.map(permissonsdto, Permissions.class);
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); 
		LocalDateTime now = LocalDateTime.now();
		permissions.setCreated_at(dtf.format(now));
		permissions.setUpdated_at(dtf.format(now));
		permissions.setStatus(Status.active);
	    permissionsRepo.save(permissions);
	    logger.debug("Completed createPermission");
	    
	 }catch(Exception ex) {
		 logger.error("Exception in createPermissions :: "+ex.getMessage());
	 }
		  }

	public List<PermissionsDTO> getAllPermissions() {
		try {
			logger.debug("Inside getallpermission");
		List<Permissions> permissionList = permissionsRepo.findAll();
		List<PermissionsDTO> permissionsdtoList = modelMapper.map(permissionList,
				new TypeToken<List<PermissionsDTO>>() {
				}.getType());
		return permissionsdtoList;
		}catch(Exception ex) {
			logger.debug("Exception in getallpermissions ::"+ ex.getMessage());
		}return null;
	}
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
	public int deletepermissionById(Integer id) {
		try {
		logger.debug("Inside inactive permission");
		Optional<Permissions> permissions = permissionsRepo.findById(id);
		if (permissions.isPresent()) {
			 permissions.get().setStatus(Status.inactive);
			permissionsRepo.save(permissions.get());
			logger.debug("Completed inactive permission");
			return 0;
		}}catch (Exception ex) {
			logger.error("Exception in inactive permission ::"+ ex.getMessage());
		}
		return 1;
	}

	public PermissionsDTO getPermissionsById(Integer id) {
		try {
		logger.debug("Inside getallpermissionb by id");
		Optional<Permissions> permissions = permissionsRepo.findById(id);
		if (permissions.isPresent()) {
			PermissionsDTO permissiondto = modelMapper.map(permissions, PermissionsDTO.class);
			return permissiondto;
		}}catch(Exception ex) {
			logger.debug("Exception in getallpermission by id ::"+ex.getMessage());
		}
		return null;
	}
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
	public String updatepermissions (PermissionsDTO permissionsdto) {
		try {
		logger.debug("Inside updatepermission ::"+permissionsdto.toString());
		Permissions permission=modelMapper.map(permissionsdto,Permissions.class);
			Optional<Permissions> permissions = permissionsRepo.findById(permissionsdto.getId());		
			 if(!permissions.isEmpty()&&permissions.isPresent()) {				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); 
				LocalDateTime now = LocalDateTime.now();
				permission.setStatus(Status.active);
				permission.setCreated_at(dtf.format(now));
				permission.setUpdated_at(dtf.format(now));
				permissionsRepo.save(permission);
				logger.debug("Completed updatepermission");
				 return "Update Succesfully";
			 }}catch (Exception ex) {
				logger.debug("Exception in updatepermission::"+ex.getMessage()); 
			 }
			 return "Not update";		
	        }		
	   }
			
  
