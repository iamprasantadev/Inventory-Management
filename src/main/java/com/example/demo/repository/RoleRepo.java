package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Roles;


@Repository
public interface RoleRepo extends CrudRepository<Roles,Integer> {
  Roles  findByTitle(String title);
}
