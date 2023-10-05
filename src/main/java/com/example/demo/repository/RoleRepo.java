package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.example.demo.entity.Roles;


public interface RoleRepo extends CrudRepository<Roles,Integer>,JpaRepository<Roles,Integer> {
  Roles  findByTitle(String title);
}
