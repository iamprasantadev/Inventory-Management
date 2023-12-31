package com.example.demo.controller;

import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.MessageResponse;
import com.example.demo.entity.UserInfoResponse;
import com.example.demo.security.config.*;
import com.example.demo.service.UserDetailsImpl;
import com.example.demo.service.UserService;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	UserService userService;
	
	
	@PostMapping("/signup")
	public ResponseEntity<MessageResponse> saveDeveloper(@RequestBody UserDTO userdto) {		
		if (userService.existsByUsername(userdto.getUsername())){
		      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		      }
		userService.saveUser(userdto);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

	}

	
	@PostMapping("/signin")
	public ResponseEntity<UserInfoResponse> authenticateUser(@Valid@RequestBody UserDTO userDTO) {		
		Authentication authentication = authenticationManager
		        .authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));	
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		String jwt = jwtUtils.generateJwtCookie(userDetails);		
		return ResponseEntity.ok().body( new UserInfoResponse(userDetails.getUserid(),
                userDetails.getUsername(),jwt));
	}
	
	
	@PostMapping("/signout")
	public ResponseEntity<?> logoutUser() {
	ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
	return ResponseEntity.ok().body(cookie.toString());		
	       }
	
	@PostMapping("/forgotpassword")
	public ResponseEntity<UUID> forgotPassword(@RequestBody UserDTO userDTO) {
	UUID generatedUuid=userService.forgotpassword(userDTO);
	return new ResponseEntity<UUID>(generatedUuid,HttpStatus.OK);	       
           }
	
	@PutMapping("/updatepassword")
	public String updatePassword(@RequestBody UserDTO userDTO) {
	userService.updatePassword(userDTO);
	return "Password ResetSuccessfully";
	     }


	}



