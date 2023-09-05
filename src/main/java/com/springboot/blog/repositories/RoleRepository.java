package com.springboot.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
}
