package com.example.demo.dto;

import java.util.Set;

import com.example.demo.entity.Status;

import lombok.Data;

@Data
public class RolesDTO {
	private Integer id;
	private String title;
	private String description;
	//private Integer active;
	private String created_at;
	private String update_at;
	private Status status;
	//private Set<PermissionsDTO> permissions;
 }
