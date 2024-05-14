package com.GuruDev.Blog.Application.Services.ServiceImpl;

import com.GuruDev.Blog.Application.Exceptions.ResourceNotFoundException;
import com.GuruDev.Blog.Application.Model.Comment;
import com.GuruDev.Blog.Application.Model.Post;
import com.GuruDev.Blog.Application.Model.User;
import com.GuruDev.Blog.Application.Payloads.CommentDTO;
import com.GuruDev.Blog.Application.Payloads.PostDTO;
import com.GuruDev.Blog.Application.Repository.CommentRepo;
import com.GuruDev.Blog.Application.Repository.PostRepo;
import com.GuruDev.Blog.Application.Repository.UserRepo;
import com.GuruDev.Blog.Application.Services.CommentService;
import com.GuruDev.Blog.Application.Services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo ;

    @Autowired
    private CommentRepo commentRepo ;

    @Autowired
    private ModelMapper modelMapper ;

    @Override
    public CommentDTO createComment(int userId , int postId, CommentDTO commentDTO) {

        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));

        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));

        Comment comment = this.modelMapper.map(commentDTO, Comment.class);

        comment.setPost(post);
        comment.setUser(user);
        Comment savedComment = this.commentRepo.save(comment);

        return this.modelMapper.map(savedComment,CommentDTO.class);

    }

    @Override
    public void deleteComment(int commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","Id",commentId));
        this.commentRepo.delete(comment);
    }
}
