package com.vibeStream.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vibeStream.entities.Likes;

public interface LikesRepository extends JpaRepository<Likes, Long> {
	
	boolean existsByUserIdAndSongId(int userId, int songId);
}