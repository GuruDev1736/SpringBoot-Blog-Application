package com.GuruDev.Blog.Application.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResponseExtractor;

import com.GuruDev.Blog.Application.Payloads.ApiResponse;
import com.GuruDev.Blog.Application.Payloads.PostDTO;
import com.GuruDev.Blog.Application.Services.PostService;
import com.GuruDev.Blog.Application.Services.ServiceImpl.PostServiceImpl;

import io.micrometer.core.ipc.http.HttpSender.Response;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/{userId}/{categoryId}")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable Integer userId,
            @PathVariable Integer categoryId) {
        PostDTO body = this.postService.createPost(postDTO, userId, categoryId);

        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}/{postId}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO,
            @PathVariable Integer categoryId, @PathVariable Integer postId) {

        PostDTO postDto = this.postService.updatePost(postDTO, postId, categoryId);

        return ResponseEntity.ok(postDto);

    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {

        this.postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post has Successfully deleted", true), HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> getAllPost() {

        List<PostDTO> postDTOs = this.postService.getAllPost();
        return ResponseEntity.ok(postDTOs);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId) {
        return ResponseEntity.ok(this.postService.getPostById(postId));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable Integer categoryId) {

        List<PostDTO> postDTOs = this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<List<PostDTO>>(postDTOs, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable Integer userId) {
        List<PostDTO> postDTOs = this.postService.getPostByUser(userId);
        return new ResponseEntity<List<PostDTO>>(postDTOs, HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<ApiResponse> deleteAllPost() {
        this.postService.deleteAllPost();
        return new ResponseEntity<>(new ApiResponse("All Posts has deleted", true), HttpStatus.OK);
    }

}
