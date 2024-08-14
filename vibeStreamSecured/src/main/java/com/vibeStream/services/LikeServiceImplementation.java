package com.vibeStream.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vibeStream.entities.Likes;
import com.vibeStream.repositories.LikeRepository;

@Service
public class LikeServiceImplementation implements LikeService{
	
	@Autowired
	LikeRepository repo;

	@Override
	public void addlike(Likes like) {
		
		repo.save(like);
	}

	@Override
	public List<Likes> findAll() {
		
		return repo.findAll();
	}

	@Override
	public boolean isLikedByUser(int userId, int songId) {
		
		return repo.existsByUserIdAndSongId(userId, songId);
	}

	

}
