package com.vibeStream.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vibeStream.entities.Song;

public interface SongRepository extends JpaRepository<Song, Integer>{

	public Song findByName(String name);
	
	
	
}
