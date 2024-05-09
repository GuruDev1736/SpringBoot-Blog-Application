package com.GuruDev.Blog.Application.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.GuruDev.Blog.Application.Model.Categories;
import com.GuruDev.Blog.Application.Model.Post;
import com.GuruDev.Blog.Application.Model.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

    Page<Post> findByUser(User user, Pageable pageable);

    Page<Post> findByCategories(Categories categories, Pageable pageable);

    List<Post> findByTitleContaining(String title);

}
