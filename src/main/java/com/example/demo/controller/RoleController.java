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
import com.example.demo.service.RolesService;

@RestController
@RequestMapping("/api/role")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoleController {

@Autowired	
RolesService roleService;	
 // to add role
@PostMapping("/addrole")	
public ResponseEntity<String>createuser(@RequestBody RolesDTO rolesdto){
	roleService.createrole(rolesdto);
	return new ResponseEntity<String>(HttpStatus.CREATED);
    }
//to get all role
@GetMapping("/getallrole")
public List<RolesDTO>getAllRoles(){
	return roleService.getAllRoles();
  }
// to get all role by permissions
@GetMapping("/getallrole/{id}")
public  List<RolesDTO> getAllRoles(@PathVariable ("id") Integer id) {
	//return roleService//.getAllRoles(id);
	return null;
    }
//to view role by id
@GetMapping("/role/{id}")
public ResponseEntity<RolesDTO> getRoleById(@PathVariable Integer id){
	RolesDTO roledto= roleService.getRolesById(id);
	 return new ResponseEntity<RolesDTO>(roledto,HttpStatus.OK);
    }
//to update role
@PutMapping("/updaterole")
public ResponseEntity<RolesDTO> updateRole( @RequestBody  RolesDTO roledto){
	 roleService.updateRole(roledto);
    return new ResponseEntity<RolesDTO>(HttpStatus.OK);
    }
// to delete role
@DeleteMapping("/inactiveRolesById/{id}")
public String inactiveRolesById(@PathVariable("id") int id){
   roleService.inactiveRolesById(id);
  return "Successfully Marked as Inactive";
    }
//to active role
@GetMapping("/getactiveroles")
public List<RolesDTO>getAllactiveRole(){
	return roleService.getallactiveroles();
    }


}
