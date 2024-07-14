package com.example.socialmediaanalytics.controller;

import com.example.socialmediaanalytics.model.User;
import com.example.socialmediaanalytics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User newUser) {
        Map<String, String> response = new HashMap<>();
        try {
            userService.registerUser(newUser);
            response.put("message", "User registered successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("message", "Registration failed: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody User loginUser) {
        Map<String, Object> response = new HashMap<>();
        try {
            User authenticatedUser = userService.authenticateUser(loginUser);
            if (authenticatedUser != null) {
                response.put("message", "Login successful");
                response.put("user", authenticatedUser);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("message", "Invalid email or password");
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            response.put("message", "Login failed: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/following")
    public ResponseEntity<List<User>> getFollowing(@PathVariable Long id) {
        List<User> following = userService.getFollowing(id);
        return new ResponseEntity<>(following, HttpStatus.OK);
    }

    @GetMapping("/{id}/followers")
    public ResponseEntity<List<User>> getFollowers(@PathVariable Long id) {
        List<User> followers = userService.getFollowers(id);
        return new ResponseEntity<>(followers, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/follow/{userId}/{followId}")
    public ResponseEntity<String> followUser(@PathVariable Long userId, @PathVariable Long followId) {
        try {
            userService.follow(userId, followId);
            return new ResponseEntity<>("Followed successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to follow: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/unfollow/{userId}/{followId}")
    public ResponseEntity<String> unfollowUser(@PathVariable Long userId, @PathVariable Long followId) {
        try {
            userService.unfollow(userId, followId);
            return new ResponseEntity<>("Unfollowed successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to unfollow: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
