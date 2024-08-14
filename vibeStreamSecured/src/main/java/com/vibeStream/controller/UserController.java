package com.vibeStream.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.vibeStream.entities.Users;
import com.vibeStream.services.SongService;
import com.vibeStream.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	
	@Autowired
	UserService service;
	
	@Autowired
	SongService songService;
	
	@PostMapping("/register")
	public String addUsers(@ModelAttribute Users user)
	{
		boolean userStatus=service.emailExists(user.getEmail());
		if(userStatus==false)
		{
			service.addUser(user);
			System.out.println("User Added...!");
		}
		else
		{
			System.out.println("User Alread Exist with same email ID...!");
		}
		return "login";
	}
	
	@GetMapping("/validate")
	public String validateUser()
	{
		Users user = service.getUser();
		System.out.println("Inside validate");
		System.out.println(user);
		if(user.getRole().equalsIgnoreCase("admin"))
		{
			System.out.println("Welcome admin");
			return "redirect:/viewUser";
		}
		else if(user.getRole().equalsIgnoreCase("customer"))
		{ 	
			System.out.println("Welcome customer");
			if(user.isPremium())
			{
				return "redirect:/viewSongs";
			}
			else
			{
				return "customerHome";
			}
		}
		return null;
	}

//	@PostMapping("/validate")
//	public String validate(@RequestParam("email") String email,
//			@RequestParam("password") String password, HttpSession session, Model model)
//	{
//		if(service.validateUser(email,password)==true)
//		{
//			String role=service.getRole(email);
//			session.setAttribute("email", email);//creating a session object for user to store user activity
//			if(role.equals("admin"))
//			{
//				System.out.println("Admin Home Call received");
//				return "addSong";
//			}
//			else
//			{
//				System.out.println("Customer Home Call received");
//				Users user=service.getUser(email);
//				boolean userStatus =user.isPremium();
//				
//				if(userStatus==true)
//				{
////					List<Song> songList=songService.fetchAllSongs();
////					model.addAttribute("songs",songList);
//					return "redirect:/viewSongs";
//				}
//				else
//				{
//					return "customerHome";
//				}
//			}
//		}
//		else
//		{
//			System.out.println("In login else block");
//			return "login";
//		}
//	}
//	
//	
//	
	
	@GetMapping("/viewUser")
	public String viewusers(Model model)
	{
		List<Users> userList = service.findAllUsers();
		model.addAttribute("users",userList);
		return "viewUser";
	}
	
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session)
	{
		session.invalidate();
		return "login";
	}
}
