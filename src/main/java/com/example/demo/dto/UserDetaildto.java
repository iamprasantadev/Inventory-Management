package com.example.demo.dto;

import com.example.demo.entity.Status;
import lombok.Data;

@Data
public class UserDetailDTO {
	
	private Integer id;
	private String firstname;
	private String lastname;
	private String email;
	private Long mobile;
	private String created_at;
	private String update_at;
	private String title;
	private Status status;
	private RolesDTO roles;


	
		
   }