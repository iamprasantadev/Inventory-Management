package com.example.demo.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="securityanswer")
public class SecurityAnswerDTO {
	@Id
	private Integer answerid;
	private String answer;

}
