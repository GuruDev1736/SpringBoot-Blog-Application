package com.GuruDev.Blog.Application.Payloads;

import java.util.Date;

import com.GuruDev.Blog.Application.Model.Categories;
import com.GuruDev.Blog.Application.Model.User;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private Integer postId;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    private String imageUrl;

    private Date addedDate;

    private CategoryDTO categories;
    private UserDTO user;

}
