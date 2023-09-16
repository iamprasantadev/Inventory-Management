package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;

	public ResponseEntity<String> sendEmail(String toEmail, String subject, String body)  {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("set mail");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);
		
		javaMailSender.send(message);
		
		return ResponseEntity.ok("Mail has sent successfully");
	}


}
