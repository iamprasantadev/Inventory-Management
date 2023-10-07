package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	private String created_at;
	private String update_at;
	@Enumerated(EnumType.STRING)
	private Status status;
	
	
	
	/*
	 * @ManyToMany(fetch = FetchType.LAZY,cascade = { CascadeType.ALL })
	 * 
	 * @JoinTable(name="userDetails_roles", joinColumns = @JoinColumn(name =
	 * "userdetailsid"), inverseJoinColumns = @JoinColumn(name = "roleid")) private
	 * Set<Roles> roles = new HashSet<>();
	 */
		
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="role_id", referencedColumnName="id")
	private Roles roles;
						 
  }
