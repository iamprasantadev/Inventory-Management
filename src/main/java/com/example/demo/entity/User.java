package com.example.demo.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;



import lombok.Data;

@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userid;
	private String username;
	private String password;
	//private LocalDateTime lastlogin;
	private UUID code;
	
	
	
	/*
	 * @OneToOne(mappedBy = "user") private UserDetail userdetails;
	 */
}