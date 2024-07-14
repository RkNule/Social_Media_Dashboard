package com.example.socialmediaanalytics.service;

import com.example.socialmediaanalytics.model.UserProfile;
import com.example.socialmediaanalytics.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileService {
    @Autowired
    private UserProfileRepository repository;

    public List<UserProfile> getAllProfiles() {
        return repository.findAll();
    }

    public UserProfile getProfileById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
