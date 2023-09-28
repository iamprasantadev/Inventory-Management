package com.example.demo.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "userdetails")
public class UserDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;
	private String firstname;
	private String lastname;
	private String email;
	private Long mobile;
	private String status;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="role_id", referencedColumnName="id")
	private Roles roles;
	
	
	
	
	 
}
