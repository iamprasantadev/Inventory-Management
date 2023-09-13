package com.example.demo.entity;

import lombok.Data;

@Data
public class JwtResponse {
	
	private String token;
	private String type = "Bearer";
	private String refreshToken;
	private Integer userid;
	private String email;
	private String password;
	

}
