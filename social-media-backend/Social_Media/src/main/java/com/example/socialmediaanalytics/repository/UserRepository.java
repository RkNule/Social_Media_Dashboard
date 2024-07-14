package com.example.socialmediaanalytics.repository;

import com.example.socialmediaanalytics.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

	Optional<User> findByUsername(String username);
}
