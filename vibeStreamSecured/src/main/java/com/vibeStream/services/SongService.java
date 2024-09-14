package com.vibeStream.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vibeStream.entities.Song;

public interface SongService {

	List<Song> fetchAllSongs();

	boolean songExists(String name);

	void addSong(Song song);

	void updateSong(Song s);

	Song getSong(Integer songId);
	
	public Page<Song> getPaginatedSongs(Pageable pageable);

	

}
