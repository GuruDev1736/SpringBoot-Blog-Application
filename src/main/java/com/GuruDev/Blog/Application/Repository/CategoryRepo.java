package com.GuruDev.Blog.Application.Repository;

import java.util.Locale.Category;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GuruDev.Blog.Application.Model.Categories;

public interface CategoryRepo extends JpaRepository<Categories, Integer> {

}
