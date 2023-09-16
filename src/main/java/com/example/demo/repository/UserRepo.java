package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;
import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User,Integer>,JpaRepository<User,Integer> {

	User findByUsername(String username);
	
	User  findByUserid(Integer userid);
	
	//public User findByResetPasswordToken(String token);
	
	Boolean existsByUsername(String username);

}
