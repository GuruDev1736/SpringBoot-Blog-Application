package com.GuruDev.Blog.Application.Services;

import java.util.List;

import com.GuruDev.Blog.Application.Payloads.PostDTO;
import com.GuruDev.Blog.Application.Payloads.PostResponse;

public interface PostService {

    PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);

    PostDTO updatePost(PostDTO postDTO, Integer postId, Integer categoryId);

    void deletePost(Integer postId);

    void deleteAllPost();

    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    PostDTO getPostById(Integer postId);

    PostResponse getPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize);

    PostResponse getPostByUser(Integer userId, Integer pageNumber, Integer pageSize);

    List<PostDTO> searchPost(String keyword);
}
