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

import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserDetailDTO;
import com.example.demo.service.UserDetailsService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	@Autowired
	UserDetailsService userdetailsService;

	@PostMapping("/createuser")
	public ResponseEntity<String> createuser(@RequestBody UserDTO userdto) {
		userdetailsService.createuser(userdto);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	// to view get all user

	@GetMapping("/getalluser")
	public List<UserDTO> getAllUserdetails() {
		return userdetailsService.getAllUserdetail();
	}
	// to get active users
	@GetMapping("/getActiveUser")
	public List<UserDTO>getAllActiveUsers(){
		return userdetailsService.getAllActiveUsers();
	}
	
	
// to view user by id

	@GetMapping("/user/{id}")
	public ResponseEntity<UserDetailDTO> getUserById(@PathVariable Integer id) {
		UserDetailDTO userdto = userdetailsService.getUserdetailsById(id);
		return new ResponseEntity<UserDetailDTO>(userdto, HttpStatus.OK);
	}
// to update user

	@PutMapping("/updateuser")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
		userDTO = userdetailsService.updateuser(userDTO);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
	}
// to delete user

	@DeleteMapping("/deleteuser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
		String msg = userdetailsService.deleteUserById(id);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

}
