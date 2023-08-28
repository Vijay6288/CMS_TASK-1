package com.content.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.content.management.model.User;

public interface UserRepository extends JpaRepository<User, Long>  {
	
	User findByEmailAndPassword(String email, String password);

}
