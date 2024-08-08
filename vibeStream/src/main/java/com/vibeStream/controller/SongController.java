package com.vibeStream.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.vibeStream.entities.Song;
import com.vibeStream.services.SongService;

@Controller
public class SongController {

	@Autowired
	SongService service;
	
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
	
	@GetMapping("/viewSongs")
	public String viewSongs(Model model)
	{
		List<Song> songList=service.fetchAllSongs();
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
