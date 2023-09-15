package com.example.demo.entity;

import java.time.Instant;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TokenRefreshRequest {
	 @NotBlank
	 private String refreshToken;
	 private Instant expirationTimestamp;
	 
	 public Boolean verifyExpiration() {
		 
		 Instant currentTimeStamp=Instant.now();
		 return !currentTimeStamp.isAfter(expirationTimestamp);
	 }

}
