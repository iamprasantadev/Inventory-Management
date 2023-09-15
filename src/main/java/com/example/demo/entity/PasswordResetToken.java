//package com.example.demo.entity;
//
//import java.time.LocalDateTime;
//
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.OneToOne;
//
//import lombok.Data;
//@Entity
//@Data
//public class PasswordResetToken {
//	
//	private Long id;
//	private String token;
//	private LocalDateTime expiryDate;
//	
//	 @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
//	    private User user;
//
//}
