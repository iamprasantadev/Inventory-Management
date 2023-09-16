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

import com.example.demo.dto.UserDetailDTO;
import com.example.demo.entity.UserDetails;
import com.example.demo.service.UserDetailsService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")

public class UserController {
@Autowired
UserDetailsService userdetailsService;
	
//@PostMapping("/createuser")	
//public ResponseEntity<String>createuser(@RequestBody UserDetailDTO userdto){
//	userdetailsService.createuser(userdto);
//	return new ResponseEntity<String>(HttpStatus.CREATED);
//     }	
@GetMapping("/getalluser")
public  List<UserDetailDTO> getAllUserdetails() {
	return userdetailsService.getAllUserdetail();
     }
@GetMapping("/user/{id}")
public ResponseEntity<UserDetailDTO> getFacaltyById(@PathVariable Integer id) {
	UserDetailDTO userdto= userdetailsService.getUserdetailsById(id);
	 return new ResponseEntity<UserDetailDTO>(userdto,HttpStatus.OK);
     }
@PutMapping("/updateuser")
public ResponseEntity<UserDetailDTO> updateUser( @RequestBody UserDetails user){
	 userdetailsService.updateuser(user);
     return new ResponseEntity<UserDetailDTO>(HttpStatus.OK);
     }
@DeleteMapping("/deleteuser/{id}")
public String deleteCourse(@PathVariable("id") int id) {
	userdetailsService.deleteUserById(id);
    return "Successfully Deleted";
     }

 }


