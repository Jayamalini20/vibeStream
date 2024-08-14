package com.vibeStream.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vibeStream.entities.Playlist;
import com.vibeStream.entities.Song;
import com.vibeStream.entities.Users;
import com.vibeStream.services.PlaylistService;
import com.vibeStream.services.SongService;
import com.vibeStream.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class PlaylistController {

	@Autowired
	UserService userService;
	
	@Autowired
	SongService songService;
	
	@Autowired
	PlaylistService playlistService;
	
	@GetMapping("/createPlaylist")
	public String createPlaylist(Model model)
	{
		List<Song> songList=songService.fetchAllSongs();
		Users user= userService.getUser();
		model.addAttribute("user", user);
		model.addAttribute("songs", songList);
		System.out.println(songList);
		return "createPlaylist";
	}

	@PostMapping("/addPlaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist)
	{
		System.out.println(playlist);
		Users user= userService.getUser();
		if(user.getRole().equals("admin"))
		{
			playlist.setVisibility("everyone");
		}
		else
		{
			playlist.setVisibility(user.getEmail());
		}
		if(playlist.getImage().equals(""))
		{
			playlist.setImage("https://github.com/Jayamalini20/mp3Files/blob/main/defaultImage.png?raw=true");
		}
		System.out.println(playlist);
		playlistService.addPlaylist(playlist);
		List<Song> songList=playlist.getSongs();
		for(Song s : songList)
		{
			s.getPlaylists().add(playlist);
			songService.updateSong(s);
			
		}
				
	    return "redirect:/createPlaylist";
	}
	
	@GetMapping("/viewPlaylists")
	public String viewPlaylists(Model model)
	{
		List<Playlist> allPlaylists=playlistService.fetchAllPlaylists();
		List<Playlist> plist = new ArrayList<>();
		Users user= userService.getUser();
		for(Playlist list: allPlaylists)
		{
			if((list.getVisibility().equals("everyone")) || (list.getVisibility().equals(user.getEmail())))
			{
				plist.add(list);
			}
			
		}
		model.addAttribute("plist",plist);
		model.addAttribute("user", user);
		return "listPlaylists";
	}
	
	@GetMapping("/viewPlaylist/{id}")
	public String viewPlaylist(@PathVariable("id") int id, Model model) {
	    Playlist playlist = playlistService.findPlaylistById(id);
	    if (playlist != null) {
	        model.addAttribute("playlist", playlist);
	        model.addAttribute("songs", playlist.getSongs());
	    }
		Users user= userService.getUser();
		model.addAttribute("user", user);
	    return "viewPlaylistSongs";
	}
	
	@GetMapping("/viewPlaylist/logout")
	public String redirectToLogout() {
	    
	    return "redirect:/logout"; 
	}
	
	@GetMapping("/viewPlaylist/viewSongs")
	public String redirectToViewSongs() {
	    
	    return "redirect:/viewSongs"; 
	}
	
	@GetMapping("/viewPlaylist/viewUser")
	public String redirectToViewUser() {
	    
	    return "redirect:/viewUser"; 
	}
	
	@GetMapping("/viewPlaylist/viewLikes")
	public String redirectToViewLikes() {
	    
	    return "redirect:/viewLikes"; 
	}
	
	@GetMapping("/viewPlaylist/addSong")
	public String redirectToAddSong() {
	    
	    return "redirect:/addSong"; 
	}
	
	@GetMapping("/viewPlaylist/createPlaylist")
	public String redirectToCreatePlaylist() {
	    
	    return "redirect:/createPlaylist"; 
	}
}
