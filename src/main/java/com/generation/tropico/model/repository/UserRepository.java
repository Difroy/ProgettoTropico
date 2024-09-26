package com.generation.tropico.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.tropico.model.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmailAndPassword(String email, String password);
	
	
	
	
	
}
