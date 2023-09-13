package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.MessageResponse;
import com.example.demo.entity.RefreshToken;
import com.example.demo.entity.TokenRefreshRequest;
import com.example.demo.entity.UserInfoResponse;
import com.example.demo.security.config.JwtUtils;
import com.example.demo.service.RefreshTokenService;
import com.example.demo.service.UserDetailsImpl;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

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
	RefreshTokenService refreshTokenService;
	@Autowired
	UserService userService;
	
//	@PostMapping("/signup")
//	public ResponseEntity<MessageResponse> saveDeveloper(@RequestBody UserDTO user) {
//		
//		if (userService.existsByUsername(user.getUsername())) {
//		      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
//		    }
//
//		userService.saveDeveloper(user);
//
//		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//
//	}
	
	
	@PostMapping("/signup")
	public ResponseEntity authenticateUser(@Valid@RequestBody UserDTO userDTO) {
		
		Authentication authentication = authenticationManager
		        .authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		String jwt = jwtUtils.generateJwtCookie(userDetails);
		
		return ResponseEntity.ok().body( new UserInfoResponse(userDetails.getUserid(),
                userDetails.getUsername(),jwt));
	}
	@PostMapping("/refreshtoken")
	public ResponseEntity refreshToken(@Valid@RequestBody TokenRefreshRequest tokenRefreshRequest) {
		String requestRefreshToken=tokenRefreshRequest.getRefreshToken();
		return refreshTokenService.findByToken(requestRefreshToken)
				 .map(requestRefreshToken::verifyExpiration)
		        .map(RefreshToken::getUser)
		        .map(user -> {
		          String token = jwtUtils.generateTokenFromUsername(user.getUsername());
		          return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
		        })
		        .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
		            "Refresh token is not in database!"));
		  }
	
	
	@PostMapping("/signout")
	public ResponseEntity<?> logoutUser() {
		ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
		return ResponseEntity.ok().body(cookie.toString());
	}
	}



