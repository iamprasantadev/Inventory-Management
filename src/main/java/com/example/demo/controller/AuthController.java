package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserDetaildto;
import com.example.demo.entity.MessageResponse;
import com.example.demo.entity.RefreshToken;
import com.example.demo.entity.TokenRefreshResponse;
import com.example.demo.entity.User;
import com.example.demo.entity.UserInfoResponse;
import com.example.demo.security.config.JwtUtils;
import com.example.demo.service.RefreshTokenService;
import com.example.demo.service.UserDetailsImpl;
import com.example.demo.service.UserDetailsService;
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
	RefreshTokenService refreshTokenService;
	
	@Autowired
	UserService userService;
	@Autowired
	UserDetailsService userdetailsService;
	
	@PostMapping("/createuser")	
	public ResponseEntity<String>createuser(@RequestBody UserDetaildto userdto){
		userdetailsService.createuser(userdto);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	     }
	
	@PostMapping("/signup")
	public ResponseEntity<MessageResponse> saveDeveloper(@RequestBody User user) {
		
		if (userService.existsByUsername(user.getUsername())) {
		      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		    }

		userService.saveUser(user);
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
	@PostMapping("/refreshtoken")
	public ResponseEntity<?> refreshtoken(@RequestBody Map<String, String> refreshToken) {
		RefreshToken token = refreshTokenService.findByToken(refreshToken.get("token"));
		
		if(token != null && refreshTokenService.verifyExpiration(token) != null) {
			User user = token.getUser();
			Map<String, Object> claims = new HashMap<>();
			//claims.put("ROLES", user.getRoles().stream().map(item -> item.getRole()).collect(Collectors.toList()));
			String jwt = jwtUtils.createToken(claims, user.getUsername());
			
			return ResponseEntity.ok(new TokenRefreshResponse("Bearer", jwt, refreshToken.get("token")));
		}
		
		return ResponseEntity.badRequest().body("Refresh token expired!");
	}
	
	
	@PostMapping("/signout")
	public ResponseEntity<?> logoutUser() {
		ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
		return ResponseEntity.ok().body(cookie.toString());
	       }
	}



