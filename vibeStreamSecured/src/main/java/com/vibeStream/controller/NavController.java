package com.vibeStream.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class NavController {
	
	@GetMapping("/")
    public String index() {
        return "index";
		
    }
	
//	@GetMapping("/login")
//	public String login()
//	{
//		return "login";
//	}
	
	@GetMapping("/register")
	public String register()
	{
		return "register";
	}
	
	@GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
		if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "login";
    }
}
