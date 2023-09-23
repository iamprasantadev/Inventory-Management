package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="roles")
public class Roles {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer roleid;
	private String title;
	private String description;
	private Integer active;
	private String created_at;
	private String update_at;
	
	/*
	 * @OneToOne(mappedBy = "role") private UserDetail userdetails;
	 */

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="roles_permissons", joinColumns = @JoinColumn(name = "roleid"), inverseJoinColumns = @JoinColumn(name = "permissionsid"))
	private Set<Permissions> permissions = new HashSet<>();
	
	@OneToOne(mappedBy="roles") 
	 private UserDetail userDetail;;	
}
