package com.vibeStream.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vibeStream.entities.Users;
import com.vibeStream.repositories.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	UserRepository repo;

	@Override
	public boolean emailExists(String email) {
		return repo.findByEmail(email)==null ? false : true;
	}

	@Override
	public void addUser(Users user) {
		repo.save(user);
		
	}

	@Override
	public boolean validateUser(String email, String password) {
		Users user=repo.findByEmail(email);
		String db_pass=user.getPassword();
		
		return password.equals(db_pass) ? true : false;
	}

	@Override
	public String getRole(String email) {
		Users user=repo.findByEmail(email);
	
		return user.getRole();
	}

	@Override
	public Users getUser(String email) {
		
		return repo.findByEmail(email);
	}

	@Override
	public void updateUser(Users user) {
		repo.save(user);
		
	}
	

}
