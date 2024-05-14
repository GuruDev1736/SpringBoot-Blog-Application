package com.GuruDev.Blog.Application.Services;

import com.GuruDev.Blog.Application.Model.Comment;
import com.GuruDev.Blog.Application.Payloads.CommentDTO;

public interface CommentService {

    CommentDTO createComment(int userId , int postId , CommentDTO commentDTO);

    void deleteComment(int commentId);

}
