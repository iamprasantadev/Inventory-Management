
package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.UserDetail;

public interface UserDetailsRepo extends CrudRepository<UserDetail,Integer>,JpaRepository<UserDetail,Integer> {
	
	UserDetail findByEmail(String email);
	
	

}

