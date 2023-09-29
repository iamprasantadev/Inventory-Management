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
	//private String status;
	private String created_at;
	private String update_at;
	//private Integer roleid;
	private RolesDTO roles;
	private Status status;
	/*
	 * private String password; private String created_at; private Integer userid;
	 * private Integer roleid;
	 */
	
   }

