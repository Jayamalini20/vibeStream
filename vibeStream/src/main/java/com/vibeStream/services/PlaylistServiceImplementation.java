package com.vibeStream.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vibeStream.entities.Playlist;
import com.vibeStream.repositories.PlaylistRepository;

@Service
public class PlaylistServiceImplementation implements PlaylistService{
	
	@Autowired
	PlaylistRepository repo;

	@Override
	public void addPlaylist(Playlist playlist) {
		repo.save(playlist);	
	}

	@Override
	public List<Playlist> fetchAllPlaylists() {
		return repo.findAll();
	}

	@Override
	public Playlist findPlaylistById(int id) {
		return repo.findById(id).orElse(null);
	}

}
