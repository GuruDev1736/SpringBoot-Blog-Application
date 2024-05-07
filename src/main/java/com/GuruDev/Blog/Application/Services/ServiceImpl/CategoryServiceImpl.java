package com.GuruDev.Blog.Application.Services.ServiceImpl;

import java.util.List;
import java.util.Locale.Category;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GuruDev.Blog.Application.Exceptions.ResourceNotFoundException;
import com.GuruDev.Blog.Application.Model.Categories;
import com.GuruDev.Blog.Application.Model.User;
import com.GuruDev.Blog.Application.Payloads.CategoryDTO;
import com.GuruDev.Blog.Application.Payloads.UserDTO;
import com.GuruDev.Blog.Application.Repository.CategoryRepo;
import com.GuruDev.Blog.Application.Services.CategoryService;

import lombok.val;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {

        Categories categories = DTOToModel(categoryDTO);
        Categories save = this.categoryRepo.save(categories);
        return this.ModelToDTO(categories);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {

        Categories categories = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        categories.setCategoryTitle(categoryDTO.getCategoryTitle());
        categories.setCategoryDescription(categoryDTO.getCategoryDescription());

        Categories updated = this.categoryRepo.save(categories);
        return this.ModelToDTO(updated);

    }

    @Override
    public CategoryDTO getCategoryById(Integer categoryId) {

        Categories categories = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        return this.ModelToDTO(categories);

    }

    @Override
    public List<CategoryDTO> getAllCategory() {

        List<Categories> categories = this.categoryRepo.findAll();
        List<CategoryDTO> categoryDTOs = categories.stream().map(category -> this.ModelToDTO(category))
                .collect(Collectors.toList());

        return categoryDTOs;
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Categories categories = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        this.categoryRepo.delete(categories);

    }

    private Categories DTOToModel(CategoryDTO categoryDTO) {
        Categories category = this.modelMapper.map(categoryDTO, Categories.class);
        return category;
    }

    private CategoryDTO ModelToDTO(Categories categories) {
        CategoryDTO categoryDTO = this.modelMapper.map(categories, CategoryDTO.class);
        return categoryDTO;
    }

}
