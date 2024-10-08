package com.vibeStream.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.vibeStream.entities.Song;
import com.vibeStream.entities.Users;
import com.vibeStream.services.LikeService;
import com.vibeStream.services.SongService;
import com.vibeStream.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class SongController {

	@Autowired
	SongService service;
	
	@Autowired
	LikeService likeService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/addSong")
	public String addSong(@ModelAttribute Song song)
	{
		boolean songStatus=service.songExists(song.getName());
		if(songStatus==false)
		{
			service.addSong(song);
			System.out.println("Song added");
		}
		else
		{
			System.out.println("Song already exist");
		}
		return "addSong";
	}
	
//	@GetMapping("/viewSongs")
//	public String viewSongs(Model model)
//	{
//		List<Song> songList=service.fetchAllSongs();
//		System.out.println(songList);	
//		model.addAttribute("songs",songList);
//		return "displaySongs";
//	}
	
	
	
	
	@GetMapping("/viewSongs")
	public String viewSongs(Model model, HttpSession session)
	{
		String email = (String) session.getAttribute("email");
		Users user= userService.getUser(email);
		System.out.println(user.getRole());	
		List<Song> songList=service.fetchAllSongs();
		
		
		
		for(Song song: songList)
		{
			boolean likedByCurrentUser = likeService.isLikedByUser(user.getId(), song.getId());
			song.setLikedByCurrentUser(likedByCurrentUser);
		}
		model.addAttribute("user", user);
		System.out.println(songList);	
		model.addAttribute("songs",songList);
		return "displaySongs";
	}
	
	@GetMapping("/addSong")
	public String newSong()
	{
		return "addSong";
	}
	
	
	
}
