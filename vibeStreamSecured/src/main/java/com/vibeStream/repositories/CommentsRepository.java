package com.vibeStream.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vibeStream.entities.Comments;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
}