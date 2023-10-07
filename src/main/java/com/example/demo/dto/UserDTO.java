package com.example.demo.dto;
import lombok.Data;

@Data
public class UserDTO {
	private Integer userid;
	private String username;
	private String password;
	private String lastlogin;
	private String code;
	private UserDetailDTO userDetail;
	private String created_at;
	private String update_at;
	
	}
    

