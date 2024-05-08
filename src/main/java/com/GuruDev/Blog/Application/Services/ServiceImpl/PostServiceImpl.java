package com.GuruDev.Blog.Application.Services.ServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GuruDev.Blog.Application.Exceptions.ResourceNotFoundException;
import com.GuruDev.Blog.Application.Model.Categories;
import com.GuruDev.Blog.Application.Model.Post;
import com.GuruDev.Blog.Application.Model.User;
import com.GuruDev.Blog.Application.Payloads.PostDTO;
import com.GuruDev.Blog.Application.Repository.CategoryRepo;
import com.GuruDev.Blog.Application.Repository.PostRepo;
import com.GuruDev.Blog.Application.Repository.UserRepo;
import com.GuruDev.Blog.Application.Services.PostService;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {

        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Categories categories = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));

        Post post = this.modelMapper.map(postDTO, Post.class);
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategories(categories);

        Post SavedPost = this.postRepo.save(post);

        return this.modelMapper.map(SavedPost, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Integer postId, Integer categoryId) {

        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

        Categories categories = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));

        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setImageUrl(postDTO.getImageUrl());
        post.setCategories(categories);

        Post SavedPost = this.postRepo.save(post);

        return this.modelMapper.map(SavedPost, PostDTO.class);

    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

        this.postRepo.delete(post);
    }

    @Override
    public List<PostDTO> getAllPost() {

        List<Post> posts = this.postRepo.findAll();

        List<PostDTO> postDTOs = posts.stream().map(post -> this.modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());

        return postDTOs;
    }

    @Override
    public PostDTO getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

        return this.modelMapper.map(post, PostDTO.class);
    }

    @Override
    public List<PostDTO> getPostByCategory(Integer categoryId) {

        Categories categories = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));

        List<Post> posts = this.postRepo.findByCategories(categories);

        List<PostDTO> postDTOs = posts.stream().map(post -> this.modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());

        return postDTOs;
    }

    @Override
    public List<PostDTO> getPostByUser(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        List<Post> posts = this.postRepo.findByUser(user);

        List<PostDTO> postDTOs = posts.stream().map(post -> this.modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());

        return postDTOs;
    }

    @Override
    public List<PostDTO> searchPost(String keyword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchPost'");
    }

    @Override
    public void deleteAllPost() {
        this.postRepo.deleteAll();
    }

}
