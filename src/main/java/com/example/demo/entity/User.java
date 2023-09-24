package com.example.demo.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userid;
	private String username;
	private String password;
	private String lastlogin;
	private UUID code;
	
	@OneToOne(mappedBy="user") 
	 private UserDetail userDetail;;	
	
	

		/*
		 * @OneToOne
		 * 
		 * @JoinColumn(name="id") private UserDetails userdetail;
		 * 
		 */


	public void setLastlogin(String format) {
		
		
	}	


}