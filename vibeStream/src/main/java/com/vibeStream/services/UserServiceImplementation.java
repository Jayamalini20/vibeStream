package com.vibeStream.services;

import java.util.List;

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
        return repo.findByEmail(email) != null;
    }

    @Override
    public void addUser(Users user) {
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

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Users user = repo.findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        return new User(user.getUsername(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase())));
//    }
}
