package com.example.demo.entity;

public class UserInfoResponse {
	
	Integer userid;
	String username;
	String jwtToken;
	
	
	public UserInfoResponse(Integer userid,String username,String jwtToken) {
		this.userid=userid;
		this.username=username;
		this.jwtToken=jwtToken;
	}

}
