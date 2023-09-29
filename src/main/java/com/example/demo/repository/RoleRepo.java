package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.entity.Roles;


public interface RoleRepo extends CrudRepository<Roles,Integer> {
  Roles  findByTitle(String title);
}
