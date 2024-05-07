package com.GuruDev.Blog.Application.Services;

import java.util.List;

import com.GuruDev.Blog.Application.Payloads.CategoryDTO;
import com.GuruDev.Blog.Application.Payloads.UserDTO;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId);

    CategoryDTO getCategoryById(Integer categoryId);

    List<CategoryDTO> getAllCategory();

    void deleteCategory(Integer categoryId);

}
