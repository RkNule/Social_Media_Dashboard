package com.example.socialmediaanalytics.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
