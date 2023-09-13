package com.example.demo.entity;

import java.time.Instant;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Data
@Entity(name="refreshtoken")
public class RefreshToken {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long refreshid;
	
	 @Column(nullable = false, unique = true)
	 private String token;
	 
	 @Column(nullable = false)
	 private Instant expiryDate;

	public Object map(Object object) {
		
		return null;
	}

}
