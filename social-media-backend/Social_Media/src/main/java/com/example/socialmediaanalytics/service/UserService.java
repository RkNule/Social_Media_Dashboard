package com.example.socialmediaanalytics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.socialmediaanalytics.model.User;
import com.example.socialmediaanalytics.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void registerUser(User newUser) {
        // Check if the user already exists
        if (userRepository.findByEmail(newUser.getEmail()) != null) {
            throw new RuntimeException("User already exists");
        }

        // Save the new user
        userRepository.save(newUser);
    }

    public void follow(Long userId, Long followId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        User follow = userRepository.findById(followId).orElseThrow(() -> new RuntimeException("User to follow not found"));
        user.getFollowing().add(follow);
        userRepository.save(user);
    }

    public void unfollow(Long userId, Long followId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        User follow = userRepository.findById(followId).orElseThrow(() -> new RuntimeException("User to unfollow not found"));
        user.getFollowing().remove(follow);
        userRepository.save(user);
    }

    public List<User> getFollowing(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getFollowing().stream().collect(Collectors.toList());
    }

    public List<User> getFollowers(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getFollowers().stream().collect(Collectors.toList());
    }

    public User authenticateUser(User loginUser) {
        // Find user by email
        User user = userRepository.findByEmail(loginUser.getEmail());
        if (user == null) {
            return null; // User not found
        }

        // Check if the provided password matches the stored password
        if (user.getPassword().equals(loginUser.getPassword())) {
            return user;
        }

        return null; // Invalid password
    }
}
