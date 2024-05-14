package com.GuruDev.Blog.Application.Repository;

import com.GuruDev.Blog.Application.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {

}
