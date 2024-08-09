package com.vibeStream.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vibeStream.entities.Song;
import com.vibeStream.repositories.SongRepository;

import java.util.Optional;

@Service
public class SongServiceImplementation implements SongService {
	
	@Autowired
	SongRepository repo;

	@Override
	public List<Song> fetchAllSongs() {
		
		return repo.findAll();
	}

	@Override
	public boolean songExists(String name) {
		
		return repo.findByName(name)==null ? false : true;
	}

	@Override
	public void addSong(Song song) {
		repo.save(song);
		
	}

	@Override
	public void updateSong(Song song) {
		repo.save(song);
		
	}

	@Override
	public Song getSong(Integer songId) {
		Optional<Song> song = repo.findById(songId);
		
		return song.orElse(null);
	}

	

	
}
