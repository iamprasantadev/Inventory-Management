package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SecurityQuestionDTO;
import com.example.demo.entity.SecurityQuestion;
import com.example.demo.entity.User;
import com.example.demo.entity.UserDetails;
import com.example.demo.repository.SecurityQuestionRepo;
import com.example.demo.repository.UserRepo;
@Service
public class SecurityQuestionService {
	@Autowired
	UserRepo userRepo;
	@Autowired
	SecurityQuestionRepo securityQuestionRepo;

	
	public void createQuestion(SecurityQuestionDTO securityQuestionDTO) {
		User user=userRepo.findByUserid(securityQuestionDTO.getUserid());
		
		if(user!=null) {
			SecurityQuestion question=new SecurityQuestion();
			question.setQuestion(securityQuestionDTO.getQuestion());
			securityQuestionRepo.save(question);
		}
	}
}
		


