package com.vibeStream.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vibeStream.entities.Users;
import com.vibeStream.repositories.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
    
    @Autowired
    UserRepository repo;
    
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public boolean emailExists(String email) {
        return repo.findByEmail(email) != null;
    }

    @Override
    public void addUser(Users user) {
    	user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
    }

    @Override
    public boolean validateUser(String email, String password) {
        Users user = repo.findByEmail(email);
        return user != null && password.equals(user.getPassword());
    }

    @Override
    public String getRole(String email) {
        Users user = repo.findByEmail(email);
        return user != null ? user.getRole() : null;
    }

    @Override
    public Users getUser(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public void updateUser(Users user) {
        repo.save(user);
    }

    @Override
    public List<Users> findAllUsers() {
        return repo.findAll();
    }

	@Override
	public Users getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();	
        return repo.findByEmail(email);
	}

}
