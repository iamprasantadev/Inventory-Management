package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SecurityQuestionDTO;
import com.example.demo.service.SecurityQuestionService;

@RestController
@RequestMapping("/api/question")
public class UserController {
	
  @Autowired
  SecurityQuestionService securityQuestionservice;
  
  @PostMapping("/createQuestion")
  public ResponseEntity<SecurityQuestionDTO> createQuestion(@RequestBody SecurityQuestionDTO securityQuestionDTO){
	  securityQuestionservice.createQuestion(securityQuestionDTO);
	  return new ResponseEntity<>(HttpStatus.OK);
  }

}
