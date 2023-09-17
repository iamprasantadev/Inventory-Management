package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.UUID;

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
	private String lastlogin;
	private UUID code;
	
	
	

	 @OneToOne
     @JoinColumn(name="id")
     private UserDetails userdetail;




	public void setLastlogin(String format) {
		
		
	}	

}