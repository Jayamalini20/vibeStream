package com.vibeStream.services;


import java.util.List;

import com.vibeStream.entities.Users;

public interface UserService {

	boolean emailExists(String email);

	void addUser(Users user);

	boolean validateUser(String email, String password);

	String getRole(String email);

	Users getUser(String email);

	void updateUser(Users u);

	List<Users> findAllUsers();
	
	
	
	

}
