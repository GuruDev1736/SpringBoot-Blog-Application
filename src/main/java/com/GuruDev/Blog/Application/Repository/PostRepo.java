package com.GuruDev.Blog.Application.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GuruDev.Blog.Application.Model.Categories;
import com.GuruDev.Blog.Application.Model.Post;
import com.GuruDev.Blog.Application.Model.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);

    List<Post> findByCategories(Categories categories);

}
