package com.vibeStream.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vibeStream.entities.Likes;

public interface LikeRepository extends JpaRepository<Likes, Long>{

	boolean existsByUserIdAndSongId(int userId, int songId);

}
