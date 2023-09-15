package com.example.demo.dto;

import lombok.Data;

@Data
public class UserDetailDTO {
	
	private Integer id;
	private String firstname;
	private String lastname;
	private String email;
	private Long mobile;
	private String created_at;
	private Integer userid;
	private Integer roleid;
	
//	private Integer questionid;
//	private Integer answerid;


}
