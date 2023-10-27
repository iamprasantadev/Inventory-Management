package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="roles")

public class Roles {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;
	private String title;
	private String description;
	//private Integer active;
	private String created_at;
	private String update_at;
	@Enumerated(EnumType.STRING)
	private Status status;	
		
	@ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE})
	@JoinTable(name="role_permissions", joinColumns =
	@JoinColumn(name = "roleid"), inverseJoinColumns = @JoinColumn(name = "permissionsid"))
	private Set<Permissions> permissions = new HashSet<>();
	
		
    }
