package com.vibeStream.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vibeStream.entities.Song;
import com.vibeStream.entities.Users;
import com.vibeStream.services.LikeService;
import com.vibeStream.services.SongService;
import com.vibeStream.services.UserService;

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
//		Users user= userService.getUser();
//		
//		List<Song> songList=service.fetchAllSongs();
//				
//		for(Song song: songList)
//		{
//			boolean likedByCurrentUser = likeService.isLikedByUser(user.getId(), song.getId());
//			song.setLikedByCurrentUser(likedByCurrentUser);
//		}
//		model.addAttribute("user", user);
//		System.out.println(user);	
//		model.addAttribute("songs",songList);
//		
//		
//		return "displaySongs";
//	}
	
	
	
	@GetMapping("/viewSongs")
	public String viewSongs(
	    @RequestParam(defaultValue = "0") int page, 
	    @RequestParam(defaultValue = "10") int size, 
	    Model model
	) { 
	    Users user = userService.getUser();
	    
	    // Fetch paginated songs with PageRequest
	    Pageable pageable = PageRequest.of(page, size);
	    Page<Song> songPage = service.getPaginatedSongs(pageable);
	    
	    // Get the song list and pagination info
	    List<Song> songList = songPage.getContent();
	    
	    // Check if each song is liked by the current user
	    for (Song song : songList) {
	        boolean likedByCurrentUser = likeService.isLikedByUser(user.getId(), song.getId());
	        song.setLikedByCurrentUser(likedByCurrentUser);
	    }
	    
	    // Add data to the model
	    model.addAttribute("user", user);
	    model.addAttribute("songs", songList);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", songPage.getTotalPages());
	    model.addAttribute("totalItems", songPage.getTotalElements());

	    return "displaySongs";
	}
	
	
	
	
	
	@GetMapping("/addSong")
	public String newSong()
	{
		return "addSong";
	}
	
	
	
}
