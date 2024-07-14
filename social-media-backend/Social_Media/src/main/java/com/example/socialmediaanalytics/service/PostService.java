package com.example.socialmediaanalytics.service;

import com.example.socialmediaanalytics.model.Post;
import com.example.socialmediaanalytics.model.User;
import com.example.socialmediaanalytics.repository.PostRepository;
import com.example.socialmediaanalytics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> findByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }

    public Post createPost(Long userId, String content) {
        // Find the user by userId
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();

        // Create a new post
        Post post = new Post();
        post.setContent(content);
        post.setUser(user);

        // Save the post to the repository
        return postRepository.save(post);
    }
}
