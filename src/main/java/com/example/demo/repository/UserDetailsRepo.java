package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserDetails;

@Repository
public interface UserDetailsRepo extends CrudRepository<UserDetails,Integer>,JpaRepository<UserDetails,Integer> {
	
	UserDetails findByEmail(String email);
	
	

}
