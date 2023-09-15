package com.example.demo.dto;

import lombok.Data;

@Data
public class Rolesdto {
	private Integer roleid;
	private String title;
	private String description;
	private Integer active;
	private String created_at;
	private String update_at;
}
