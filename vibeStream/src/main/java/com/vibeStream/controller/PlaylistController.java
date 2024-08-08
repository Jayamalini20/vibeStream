package com.vibeStream.controller;

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
import com.vibeStream.services.PlaylistService;
import com.vibeStream.services.SongService;
import com.vibeStream.services.UserService;

import jakarta.servlet.http.HttpServletRequest;

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
		model.addAttribute("songs", songList);
		return "createPlaylist";
	}

	@PostMapping("/addPlaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist, HttpServletRequest request, RedirectAttributes redirectAttributes)
	{
		System.out.println(playlist);
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
				
	    // Add a success message if needed
	    redirectAttributes.addFlashAttribute("message", "Playlist created successfully!");

	    // Redirect to the createPlaylist endpoint
	    return "redirect:/createPlaylist";
	}
	
	@GetMapping("/viewPlaylists")
	public String viewPlaylists(Model model)
	{
		List<Playlist> allPlaylists=playlistService.fetchAllPlaylists();
		model.addAttribute("allPlaylists",allPlaylists);
		System.out.println(allPlaylists);
		//return "displayPlaylists";
		return "listPlaylists";
	}
	
	@GetMapping("/viewPlaylist/{id}")
	public String viewPlaylist(@PathVariable("id") int id, Model model) {
	    Playlist playlist = playlistService.findPlaylistById(id);
	    if (playlist != null) {
	        model.addAttribute("playlist", playlist);
	        model.addAttribute("songs", playlist.getSongs());
	    }
	    return "viewPlaylistSongs";
	}
}
