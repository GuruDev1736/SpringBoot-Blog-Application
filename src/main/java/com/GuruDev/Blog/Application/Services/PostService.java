package com.GuruDev.Blog.Application.Services;

import java.util.List;

import com.GuruDev.Blog.Application.Payloads.PostDTO;

public interface PostService {

    PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);

    PostDTO updatePost(PostDTO postDTO, Integer postId, Integer categoryId);

    void deletePost(Integer postId);

    void deleteAllPost();

    List<PostDTO> getAllPost();

    PostDTO getPostById(Integer postId);

    List<PostDTO> getPostByCategory(Integer categoryId);

    List<PostDTO> getPostByUser(Integer userId);

    List<PostDTO> searchPost(String keyword);
}
