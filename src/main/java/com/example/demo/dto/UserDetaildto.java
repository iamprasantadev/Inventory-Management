package com.example.demo.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserDetailDTO {
	
	private Integer id;
	private String firstname;
	private String lastname;
	private String email;
	private Long mobile;
	private String status;
	private List<RolesDTO> rolelist;
	
   }

