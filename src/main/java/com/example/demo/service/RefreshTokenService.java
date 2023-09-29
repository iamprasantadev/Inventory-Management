package com.example.demo.service;

import java.time.Instant;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.RefreshToken;
import com.example.demo.entity.TokenRefreshException;
import com.example.demo.repository.RefreshTokenRepo;

@Service
public class RefreshTokenService {
	
	@Autowired
	RefreshTokenRepo refreshTokenRepo;
//	@Value("${login.jwtRefreshExpirationMs}")
//	  private Long refreshTokenDurationMs;
	
	public RefreshToken findByToken(String Token) {
		return refreshTokenRepo.findByToken(Token);
	}
	
	public RefreshToken createRefreshToken(Long userid) {
		
		RefreshToken refreshToken=new RefreshToken();
		refreshToken.setExpiryDate(Instant.now().plusMillis(3000000));
		 refreshToken.setToken(UUID.randomUUID().toString());
		 
		 refreshToken=refreshTokenRepo.save(refreshToken);
		 return refreshToken;	
	}
	
	public RefreshToken verifyExpiration(RefreshToken token) {
	    if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
	    	refreshTokenRepo.delete(token);
	      throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
	    }

	    return token;
	  }

}
