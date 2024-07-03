package com.GuruDev.Blog.Application.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.GuruDev.Blog.Application.Config.Constant;
import com.GuruDev.Blog.Application.Payloads.ApiResponse;
import com.GuruDev.Blog.Application.Payloads.ImageResponse;
import com.GuruDev.Blog.Application.Payloads.PostDTO;
import com.GuruDev.Blog.Application.Payloads.PostResponse;
import com.GuruDev.Blog.Application.Services.PostService;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber", defaultValue = Constant.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = Constant.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = Constant.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = Constant.SORT_DIR, required = false) String sortDir) {

        PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId) {
        return ResponseEntity.ok(this.postService.getPostById(postId));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<PostResponse> getPostByCategory(@PathVariable Integer categoryId,
            @RequestParam(value = "pageNumber", defaultValue = Constant.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = Constant.PAGE_SIZE, required = false) Integer pageSize) {

        PostResponse postResponse = this.postService.getPostByCategory(categoryId, pageNumber, pageSize);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<PostResponse> getPostByUser(@PathVariable Integer userId,
            @RequestParam(value = "pageNumber", defaultValue = Constant.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = Constant.PAGE_SIZE, required = false) Integer pageSize) {
        PostResponse postDTOs = this.postService.getPostByUser(userId, pageNumber, pageSize);

        return new ResponseEntity<PostResponse>(postDTOs, HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<ApiResponse> deleteAllPost() {
        this.postService.deleteAllPost();
        return new ResponseEntity<>(new ApiResponse("All Posts has deleted", true), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PostDTO>> searchPost(@RequestParam(value = "search", required = true) String keyword) {
        List<PostDTO> postDTOs = this.postService.searchPost(keyword);
        return ResponseEntity.ok(postDTOs);
    }


}
