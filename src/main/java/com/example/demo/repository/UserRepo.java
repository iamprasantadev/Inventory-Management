package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.User;
import java.util.List;

public interface UserRepo extends CrudRepository<User,Integer>,JpaRepository<User,Integer> {

	User findByUsername(String username);
	
	Boolean existsByUsername(String username);

}
