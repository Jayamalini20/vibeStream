package com.vibeStream.services;

import java.util.List;

import com.vibeStream.entities.Song;

public interface SongService {

	List<Song> fetchAllSongs();

	boolean songExists(String name);

	void addSong(Song song);

	void updateSong(Song s);

}
