package com.example.demo.dto;

import lombok.Data;

@Data
public class RolesDTO {
	private Integer roleid;
	private String title;
	private String description;
	private Integer active;
	private String created_at;
	private String update_at;
	private Integer permissionsid;
 }
