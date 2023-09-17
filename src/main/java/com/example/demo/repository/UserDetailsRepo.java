
package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserDetail;

@Repository
public interface UserDetailsRepo extends CrudRepository<UserDetail,Integer>,JpaRepository<UserDetail,Integer> {
	
	UserDetail findByEmail(String email);
	
	

}

