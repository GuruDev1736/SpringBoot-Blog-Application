package com.GuruDev.Blog.Application.Controller;

import com.GuruDev.Blog.Application.Payloads.ApiResponse;
import com.GuruDev.Blog.Application.Payloads.CommentDTO;
import com.GuruDev.Blog.Application.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService ;

    @PostMapping("/{userId}/{postId}/create")
    public ResponseEntity<CommentDTO> createComment(@PathVariable int userId , @PathVariable int postId , @RequestBody CommentDTO commentDTO){

        CommentDTO comment = this.commentService.createComment(userId,postId,commentDTO);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment (@PathVariable int commentId){
        this.commentService.deleteComment(commentId);
        return ResponseEntity.ok(new ApiResponse("Comment has Successfully deleted",true));
    }

}
