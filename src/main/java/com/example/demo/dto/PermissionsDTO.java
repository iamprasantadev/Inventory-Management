package com.example.demo.dto;

import lombok.Data;

@Data
public class PermissionsDTO {
	
	private Integer id;
	private String title;
	private String description;
	private Integer active;
	private String created_at;
	private String updated_at;

}
