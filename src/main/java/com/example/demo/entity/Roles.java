package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

}
