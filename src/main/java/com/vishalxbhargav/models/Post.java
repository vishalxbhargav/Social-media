package com.vishalxbhargav.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String caption;
    private String image;
    private String video;
    @ManyToOne
    private User user;
    @OneToMany
    private List<User> liked=new ArrayList<>();
    @OneToMany
    private List<Comment> Comments=new ArrayList<>();
    private LocalDateTime createdAt;
}
