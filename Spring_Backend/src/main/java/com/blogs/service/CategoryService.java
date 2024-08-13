package com.blogs.service;

import java.util.List;
import java.util.Optional;

import com.blogs.dto.CategoryDTO;
import com.blogs.dto.ProductDTO;

public interface CategoryService {
	CategoryDTO addCategory(CategoryDTO categoryDTO);
	CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO);
	List<CategoryDTO> getAllCategories();
	Optional<CategoryDTO> getCategoryById(Long categoryId);
	List<ProductDTO> getProductsByCategory(Long categoryId);
}
