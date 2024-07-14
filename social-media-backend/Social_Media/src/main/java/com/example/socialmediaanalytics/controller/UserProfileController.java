package com.example.socialmediaanalytics.controller;

import com.example.socialmediaanalytics.model.UserProfile;
import com.example.socialmediaanalytics.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class UserProfileController {
    @Autowired
    private UserProfileService service;

    @GetMapping
    public List<UserProfile> getAllProfiles() {
        return service.getAllProfiles();
    }

    @GetMapping("/{id}")
    public UserProfile getProfileById(@PathVariable Long id) {
        return service.getProfileById(id);
    }
}
