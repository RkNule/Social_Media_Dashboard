package com.example.socialmediaanalytics.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.socialmediaanalytics.model.Post;

import java.util.List;
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
 List<Post> findByUserId(Long userId);
}
