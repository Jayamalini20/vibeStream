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

@Controller
public class LikeController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	SongService songService;

	@Autowired
	LikeService likeService;
	@GetMapping("/like/{songId}")
	public String likeSong(@PathVariable("songId") Integer songId) {
	    
	    System.out.println("Song liked"+songId);
	    Users user= userService.getUser();
	    Song song = songService.getSong(songId);
	    
	    Likes like = new Likes();
	    like.setUser(user);
	    like.setSong(song);
	    
	    likeService.addlike(like);
	    System.out.println(user.getEmail()+" liked "+songId);
	    return "redirect:/viewSongs";
	}
	
	@GetMapping("/viewLikes")
	public String viewLikes(Model model) {
	    
	    List<Likes> likeList = likeService.findAll();
	    Map<Song, Long> songLikesMap = new HashMap<>();
	    Map<Users, Long> userLikedSongsMap = new HashMap<>();
	    
	    for (Likes like : likeList) {
	        Song likedSong = like.getSong();
	        Users user = like.getUser();
	        songLikesMap.put(likedSong, songLikesMap.getOrDefault(likedSong, 0L) + 1);
	        userLikedSongsMap.put(user, userLikedSongsMap.getOrDefault(user, 0L) + 1);
	    }
	    
	    model.addAttribute("songLikesMap", songLikesMap);
	    model.addAttribute("userLikedSongsMap", userLikedSongsMap);
	    
	    return "viewLikes"; 
	}

    
}