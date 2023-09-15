package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;
	private Integer firstname;
	private Integer lastname;
	private String email;
	private Long mobile;
	private String created_at;
	private String resetpasswordtoken;
	
}
