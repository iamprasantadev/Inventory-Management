package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="securityanswer")
public class SecurityAnswer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer answerid;
	private String answer;

}
