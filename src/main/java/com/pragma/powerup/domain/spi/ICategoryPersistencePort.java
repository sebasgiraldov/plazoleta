package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.CategoryModel;

import java.util.List;

public interface ICategoryPersistencePort {

    CategoryModel saveCategory(CategoryModel categoryModel);
    CategoryModel getCategory(Long categoryId);
    List<CategoryModel> getAllCategories();

}

