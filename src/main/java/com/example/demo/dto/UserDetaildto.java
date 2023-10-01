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
	//private Integer userid;
	//private Integer roleid;
	private Status status;
	private String title;
	private UserDTO user;
	
   }

