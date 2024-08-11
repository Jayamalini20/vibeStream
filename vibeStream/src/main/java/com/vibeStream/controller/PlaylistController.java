package com.vibeStream.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String createPlaylist(Model model, HttpSession session)
	{
		List<Song> songList=songService.fetchAllSongs();
		String email = (String) session.getAttribute("email");
		Users user= userService.getUser(email);
		model.addAttribute("user", user);
		model.addAttribute("songs", songList);
		return "createPlaylist";
	}

	@PostMapping("/addPlaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist, HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session)
	{
		System.out.println(playlist);
		String email = (String) session.getAttribute("email");
		Users user= userService.getUser(email);
		if(user.getRole().equals("admin"))
		{
			playlist.setVisibility("everyone");
		}
		else
		{
			playlist.setVisibility(email);
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
	public String viewPlaylists(Model model, HttpSession session)
	{
		List<Playlist> allPlaylists=playlistService.fetchAllPlaylists();
		List<Playlist> plist = new ArrayList<>();
		String email = (String) session.getAttribute("email");
		for(Playlist list: allPlaylists)
		{
			if((list.getVisibility().equals("everyone")) || (list.getVisibility().equals(email)))
			{
				plist.add(list);
			}
			
		}
		model.addAttribute("plist",plist);
		
		Users user= userService.getUser(email);
		model.addAttribute("user", user);
		return "listPlaylists";
	}
	
	@GetMapping("/viewPlaylist/{id}")
	public String viewPlaylist(@PathVariable("id") int id, Model model, HttpSession session) {
	    Playlist playlist = playlistService.findPlaylistById(id);
	    if (playlist != null) {
	        model.addAttribute("playlist", playlist);
	        model.addAttribute("songs", playlist.getSongs());
	    }
	    String email = (String) session.getAttribute("email");
		Users user= userService.getUser(email);
		model.addAttribute("user", user);
	    return "viewPlaylistSongs";
	}
	
	@GetMapping("/viewPlaylist/logout")
	public String logout(HttpSession session) {
	    session.invalidate(); // or any other logout logic
	    return "redirect:/login"; // redirect to login page after logout
	}
}
