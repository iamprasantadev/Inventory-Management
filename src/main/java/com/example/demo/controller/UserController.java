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
import com.example.demo.dto.UserDetaildto;
import com.example.demo.entity.UserDetails;
import com.example.demo.service.UserDetailsService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {

@Autowired
UserDetailsService userdetailsService;
	
@PostMapping("/createuser")	
public ResponseEntity<String>createuser(@RequestBody UserDetaildto userdto){
	userdetailsService.createuser(userdto);
	return new ResponseEntity<String>(HttpStatus.CREATED);
     }	
@GetMapping("/getalluser")
public  List<UserDetaildto> getAllUserdetails() {
	return userdetailsService.getAllUserdetail();
     }
@GetMapping("/user/{id}")
public ResponseEntity<UserDetaildto> getFacaltyById(@PathVariable Integer id) {
	 UserDetaildto userdto= userdetailsService.getUserdetailsById(id);
	 return new ResponseEntity<UserDetaildto>(userdto,HttpStatus.OK);
     }
@PutMapping("/updateuser")
public ResponseEntity<UserDetaildto> updateUser( @RequestBody UserDetails user){
	 userdetailsService.updateuser(user);
     return new ResponseEntity<UserDetaildto>(HttpStatus.OK);
     }
@DeleteMapping("/deleteuser/{id}")
public String deleteCourse(@PathVariable("id") int id) {
	userdetailsService.deleteUserById(id);
    return "Successfully Deleted";
     }

 }
