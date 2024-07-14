package com.example.socialmediaanalytics.repository;


import com.example.socialmediaanalytics.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
