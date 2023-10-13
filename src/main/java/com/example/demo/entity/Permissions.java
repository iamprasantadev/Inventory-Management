package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="permissions")
public class Permissions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;
	private String title;
	private String description;
	private Integer active;
	private Status status;
	private String created_at;
	private String updated_at;

	
	
  @ManyToMany(fetch = FetchType.LAZY,cascade =
  CascadeType.ALL ,mappedBy = "permissions") 
  private Set<Roles> roles = new HashSet<>();




	 
	 
}
