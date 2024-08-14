package com.vibeStream.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vibeStream.entities.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{

	public Users findByEmail(String email);
	
	Users findByUsername(String username);
}
