package com.vibeStream.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vibeStream.entities.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer>{

}
