package com.vibeStream.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.vibeStream.entities.Likes;
import com.vibeStream.entities.Song;
import com.vibeStream.entities.Users;
import com.vibeStream.services.LikeService;
import com.vibeStream.services.SongService;
import com.vibeStream.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LikeController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	SongService songService;

	@Autowired
	LikeService likeService;
	@GetMapping("/like/{songId}")
	public String likeSong(@PathVariable("songId") Integer songId, HttpSession session) {
	    // Retrieve user email from session
	    String email = (String) session.getAttribute("email");
	    
	    Users user= userService.getUser(email);
	    Song song = songService.getSong(songId);
	    
	    Likes like = new Likes();
	    like.setUser(user);
	    like.setSong(song);
	    
	    likeService.addlike(like);

	    return "redirect:/viewSongs";
	}
	
	@GetMapping("/viewLikes")
	public String viewLikes(Model model) {
	    // Fetch all likes from the database
	    List<Likes> likeList = likeService.findAll();
	    
	    // Create a map to store the total likes for each song
	    Map<Song, Long> songLikesMap = new HashMap<>();
	    
	    // Create a map to store the total number of liked songs for each user
	    Map<Users, Long> userLikedSongsMap = new HashMap<>();
	    
	    // Loop through all the likes
	    for (Likes like : likeList) {
	        // Get the song and user associated with this like
	        Song likedSong = like.getSong();
	        
	        Users user = like.getUser();
	        
	        
	        // Update the total likes for the song
	        songLikesMap.put(likedSong, songLikesMap.getOrDefault(likedSong, 0L) + 1);
	        
	        // Update the total liked songs for the user
	        userLikedSongsMap.put(user, userLikedSongsMap.getOrDefault(user, 0L) + 1);
	    }
	    
	    // Add the maps to the model so they can be accessed in the view
	    model.addAttribute("songLikesMap", songLikesMap);
	    model.addAttribute("userLikedSongsMap", userLikedSongsMap);
	    
	    return "viewLikes"; // Return the name of the view template
	}

    
}