package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.CategoryModel;

import java.util.List;

public interface ICategoryServicePort {
    CategoryModel saveCategory(CategoryModel categoryModel);

    CategoryModel getCategory(Long categoryId);

    List<CategoryModel> getAllCategories();

}
