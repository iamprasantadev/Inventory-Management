package com.example.demo.entity;

import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userid;
	private String username;
	private String password;
	private String lastlogin;
	private UUID code;
	private String created_at;
	private String update_at;
	
	
	  @OneToOne(cascade = CascadeType.ALL)	  
	  @JoinColumn(name = "userdetails_id", referencedColumnName = "id") 
	  private UserDetail userDetail;
	 

}