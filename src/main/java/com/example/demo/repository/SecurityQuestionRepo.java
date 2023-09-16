package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.SecurityQuestion;

public interface SecurityQuestionRepo extends CrudRepository<SecurityQuestion,Integer> {

}
