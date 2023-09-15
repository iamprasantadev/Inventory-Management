//package com.example.demo.entity;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//
//import org.springframework.data.annotation.Id;
//
//import lombok.Data;
//
//@Entity
//@Data
//public class Roles {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;
//	private String title;
//	private String description;
//	private Integer active;
//	private String created_at;
//	private String update_at;
//	
//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(name = "roles_permissions", joinColumns = @JoinColumn(name = "roleid"), inverseJoinColumns = @JoinColumn(name = "permissionid"))
//	private Set<Permissions> permission = new HashSet<>();
//
//
//}
