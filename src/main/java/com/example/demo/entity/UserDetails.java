package com.example.demo.entity;

import javax.persistence.Entity;

import org.springframework.data.annotation.Id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Entity
@Data
public class UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;
	private Integer firstname;
	private Integer lastname;
	private String mail;
	private Long mobile;
	private String created_at;

}
