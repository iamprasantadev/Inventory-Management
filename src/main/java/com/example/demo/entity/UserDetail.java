package com.example.demo.entity;

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
@Table(name="userdetails")
public class UserDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;
	private String firstname;
	private String lastname;
	private String email;
	private Long mobile;
	private String status;
	private String password;
	private String created_at;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    private User user;	
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roloeid")
    private Roles roles;
	
}
