package com.example.demo.dto;

import com.example.demo.entity.Status;
import lombok.Data;

@Data
public class PermissionsDTO {
	
	private Integer id;
	private String title;
	private String description;
	//private Integer active;
	private Status status;
	private String created_at;
	private String updated_at;
	
}
