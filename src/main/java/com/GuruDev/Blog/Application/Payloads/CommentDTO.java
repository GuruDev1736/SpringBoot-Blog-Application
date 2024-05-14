package com.GuruDev.Blog.Application.Payloads;


import com.GuruDev.Blog.Application.Model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private int commentId ;
    private String content ;
    private UserDTO user;

}
