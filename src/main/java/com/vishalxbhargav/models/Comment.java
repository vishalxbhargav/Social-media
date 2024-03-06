package com.vishalxbhargav.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer Id;
    private String content;
    @ManyToMany
    private User user;
    @ManyToMany
    private List<User> likes=new ArrayList<>();
    private LocalDateTime CreateAt;
}
