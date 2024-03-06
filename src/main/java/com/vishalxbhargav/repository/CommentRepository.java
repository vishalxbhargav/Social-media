package com.vishalxbhargav.repository;

import com.vishalxbhargav.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

}
