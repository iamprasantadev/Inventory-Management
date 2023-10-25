package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.PermissionsDTO;
import com.example.demo.dto.RolesDTO;
import com.example.demo.service.PermissionsService;

@RestController
@RequestMapping("/api/permissions")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PermissionController {
	
@Autowired
PermissionsService permissionsService;

// to create permissions
@PostMapping("/addpermissions")
private ResponseEntity<String>createpermissions(@RequestBody PermissionsDTO permissionsdto){
	permissionsService.createpermissions(permissionsdto);	
  return new ResponseEntity<String>("Permissions added succesfully", HttpStatus.CREATED);
      }

@GetMapping("/getallpermissions")
public  List<PermissionsDTO> getAllPermissions() {
	return permissionsService.getAllPermissions();
     }
@DeleteMapping("/deletepermissions/{id}")
public String deletepermissions(@PathVariable("id")int id) {
	permissionsService.deletepermissionById(id);
	 return "Successfully Deleted";
     }
@GetMapping("/permissions/{permissionsid}")
public ResponseEntity<PermissionsDTO> getPermissionsById(@PathVariable ("permissionsid") Integer permissionsid) {
	PermissionsDTO permissionsdto= permissionsService.getPermissionsById(permissionsid);
	 return new ResponseEntity<PermissionsDTO>(permissionsdto,HttpStatus.OK);
     }
		
  @PutMapping("/updatepermissions") public
  ResponseEntity<String>updatepermissions(@RequestBody PermissionsDTO  permissionsdto ){ 
	  permissionsService.updatepermissions(permissionsdto);
  return new ResponseEntity<String>("Update Succesfully",HttpStatus.OK); }
		 





 }

