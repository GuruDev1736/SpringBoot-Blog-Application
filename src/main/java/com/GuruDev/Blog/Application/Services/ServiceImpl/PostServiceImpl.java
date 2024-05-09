package com.GuruDev.Blog.Application.Services.ServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.GuruDev.Blog.Application.Exceptions.ResourceNotFoundException;
import com.GuruDev.Blog.Application.Model.Categories;
import com.GuruDev.Blog.Application.Model.Post;
import com.GuruDev.Blog.Application.Model.User;
import com.GuruDev.Blog.Application.Payloads.PostDTO;
import com.GuruDev.Blog.Application.Payloads.PostResponse;
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
        public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

                Sort sort = null;

                if (sortDir.equalsIgnoreCase("asc")) {
                        sort = Sort.by(sortBy).ascending();
                } else {
                        sort = Sort.by(sortBy).descending();
                }

                Pageable p = PageRequest.of(pageNumber, pageSize, sort);
                Page<Post> pagePost = this.postRepo.findAll(p);
                List<Post> posts = pagePost.getContent();

                List<PostDTO> postDTOs = posts.stream().map(post -> this.modelMapper.map(post, PostDTO.class))
                                .collect(Collectors.toList());

                PostResponse postResponse = new PostResponse();
                postResponse.setContent(postDTOs);
                postResponse.setPageNumber(pagePost.getNumber());
                postResponse.setPageSize(pagePost.getSize());
                postResponse.setTotalElements(pagePost.getTotalElements());
                postResponse.setTotalPages(pagePost.getTotalPages());
                postResponse.setLastpage(pagePost.isLast());

                return postResponse;
        }

        @Override
        public PostDTO getPostById(Integer postId) {
                Post post = this.postRepo.findById(postId)
                                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

                return this.modelMapper.map(post, PostDTO.class);
        }

        @Override
        public PostResponse getPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize) {

                Categories categories = this.categoryRepo.findById(categoryId)
                                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));

                Pageable p = PageRequest.of(pageNumber, pageSize);
                Page<Post> pagePost = this.postRepo.findByCategories(categories, p);

                List<Post> posts = pagePost.getContent();

                List<PostDTO> postDTOs = posts.stream().map(post -> this.modelMapper.map(post, PostDTO.class))
                                .collect(Collectors.toList());

                PostResponse postResponse = new PostResponse();
                postResponse.setContent(postDTOs);
                postResponse.setPageNumber(pagePost.getNumber());
                postResponse.setPageSize(pagePost.getSize());
                postResponse.setTotalElements(pagePost.getTotalElements());
                postResponse.setTotalPages(pagePost.getTotalPages());
                postResponse.setLastpage(pagePost.isLast());

                return postResponse;
        }

        @Override
        public PostResponse getPostByUser(Integer userId, Integer pageNumber, Integer pageSize) {

                User user = this.userRepo.findById(userId)
                                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

                Pageable p = PageRequest.of(pageNumber, pageSize);
                Page<Post> pagePost = this.postRepo.findByUser(user, p);

                List<Post> posts = pagePost.getContent();

                List<PostDTO> postDTOs = posts.stream().map(post -> this.modelMapper.map(post, PostDTO.class))
                                .collect(Collectors.toList());

                PostResponse postResponse = new PostResponse();
                postResponse.setContent(postDTOs);
                postResponse.setPageNumber(pagePost.getNumber());
                postResponse.setPageSize(pagePost.getSize());
                postResponse.setTotalElements(pagePost.getTotalElements());
                postResponse.setTotalPages(pagePost.getTotalPages());
                postResponse.setLastpage(pagePost.isLast());

                return postResponse;
        }

        @Override
        public List<PostDTO> searchPost(String keyword) {

                List<Post> searchPost = this.postRepo.findByTitleContaining(keyword);

                List<PostDTO> postDTOs = searchPost.stream().map(post -> this.modelMapper.map(post, PostDTO.class))
                                .collect(Collectors.toList());

                return postDTOs;

        }

        @Override
        public void deleteAllPost() {
                this.postRepo.deleteAll();
        }

}
