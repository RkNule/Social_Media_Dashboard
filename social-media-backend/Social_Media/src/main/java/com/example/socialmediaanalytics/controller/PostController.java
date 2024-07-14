package com.example.socialmediaanalytics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.socialmediaanalytics.model.Post;
import com.example.socialmediaanalytics.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/user/{userId}")
    public List<Post> getPostsByUserId(@PathVariable Long userId) {
        return postService.findByUserId(userId);
    }

    @PostMapping("/create/{userId}")
    public Post createPost(@PathVariable Long userId, @RequestBody String content) {
        return postService.createPost(userId, content);
    }
}
