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
import com.example.demo.dto.RolesDTO;
import com.example.demo.entity.Roles;
import com.example.demo.service.RolesService;

@RestController
@RequestMapping("/api/role")
@CrossOrigin(origins = "*")
public class RoleController {

@Autowired	
RolesService roleService;	
	
@PostMapping("/addrole")	
public ResponseEntity<String>createuser(@RequestBody RolesDTO roledto){
	roleService.createrole(roledto);
	return new ResponseEntity<String>(HttpStatus.CREATED);
    }	
@GetMapping("/getallrole")
public  List<RolesDTO> getAllRoles() {
	return roleService.getAllRoles();
    }
@GetMapping("/role/{id}")
public ResponseEntity<RolesDTO> getRoleById(@PathVariable Integer roleid){
	RolesDTO roledto= roleService.getRolesById(roleid);
	 return new ResponseEntity<RolesDTO>(roledto,HttpStatus.OK);
    }
@PutMapping("/updaterole")
public ResponseEntity<RolesDTO> updateRole( @RequestBody  Roles role){
	 roleService.updaterole(role);
    return new ResponseEntity<RolesDTO>(HttpStatus.OK);
    }
@DeleteMapping("/deleterole/{roleid}")
public String deleteRole(@PathVariable("roleid") int roleid){
	roleService.deleteRolesById(roleid);
  return "Successfully Deleted";
    }

	
}
