package com.vibeStream.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vibeStream.entities.Users;
import com.vibeStream.repositories.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserRepository repo;
	
	
	@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      		
		Users user = repo.findByEmail(email); 
		
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        System.out.println("From My user detail Service");
        return new MyUserDetails(user);
    }

}
