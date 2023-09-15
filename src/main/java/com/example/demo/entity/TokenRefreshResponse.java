package com.example.demo.entity;

import lombok.Data;

@Data
public class TokenRefreshResponse {
	
	private String accessToken;
	private String refreshToken;
	private String tokenType="Bearer";
	
	public TokenRefreshResponse(String tokenType, String accessToken, String refreshToken) {
		this.tokenType = tokenType;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

}
