package com.vibeStream.services;

import java.util.List;

import com.vibeStream.entities.Likes;

public interface LikeService {

	void addlike(Likes like);

	List<Likes> findAll();

	boolean isLikedByUser(int userId, int songId);

}
