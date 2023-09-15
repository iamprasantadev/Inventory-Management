package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	private LocalDateTime lastlogin;
	
	
	
	
	
	
	 @OneToOne
     @JoinColumn(name="id")
     private UserDetails userdetail;
	 
	 @OneToOne
	 @JoinColumn(name="questionid")
	 private SecurityQuestion question;
	 
	 @OneToOne
	 @JoinColumn(name="answerid")
	 private SecurityAnswer answer;
	 
	 
	

}