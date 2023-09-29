package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="roles")
public class Roles {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;
	private String title;
	private String description;
	private Integer active;
	private String created_at;
	private String update_at;
	
	

	/*
	 * @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	 * 
	 * @JoinTable(name="roles_permissons", joinColumns = @JoinColumn(name =
	 * "roleid"), inverseJoinColumns = @JoinColumn(name = "permissionsid")) private
	 * Set<Permissions> permissions = new HashSet<>();
	 */
		
	 
	 
}
