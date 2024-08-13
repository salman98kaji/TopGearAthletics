package com.blogs.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogs.dto.CategoryDTO;
import com.blogs.dto.ProductDTO;
import com.blogs.entities.Category;
import com.blogs.entities.Product;
import com.blogs.repository.CategoryRepository;
import com.blogs.repository.ProductRepository;
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public CategoryDTO addCategory(CategoryDTO categoryDTO) {
		Category category = convertToEntity(categoryDTO);
		Category savedCategory = categoryRepository.save(category);
		return convertToDTO(savedCategory);
	}

	private CategoryDTO convertToDTO(Category category) {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategoryId(category.getCategoryId());
		categoryDTO.setCategoryName(category.getName());
		categoryDTO.setDescription(category.getDescription());
		return categoryDTO;
	}

	private Category convertToEntity(CategoryDTO categoryDTO) {
		Category category = new Category();
		category.setName(categoryDTO.getCategoryName());
		category.setDescription(categoryDTO.getDescription());
		
		return category;
	}

	@Override
	public CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO) {
		Optional<Category> existingCategory = categoryRepository.findById(categoryId);
		if (existingCategory.isPresent()) {
			Category category = existingCategory.get();
			category.setName(categoryDTO.getCategoryName());
			category.setDescription(categoryDTO.getDescription());
			Category updatedCategory = categoryRepository.save(category);
			return convertToDTO(updatedCategory);
		}
		throw new RuntimeException("Category not found with ID: " + categoryId);
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
		List<Category> categories = categoryRepository.findAll();
		return categories.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public Optional<CategoryDTO> getCategoryById(Long categoryId) {
		return categoryRepository.findById(categoryId).map(this::convertToDTO);
	}

	@Override
	public List<ProductDTO> getProductsByCategory(Long categoryId) {
		Optional<Category> category = categoryRepository.findById(categoryId);
		if(category.isPresent()) {
			List<Product> products = productRepository.findAll().stream()
					.filter(product -> product.getCategory() != null && product.getCategory().getCategoryId().equals(categoryId))
					.collect(Collectors.toList());
			return products.stream().map(this::convertToProductDTO).collect(Collectors.toList());
		}
		throw new RuntimeException("Category not found with ID: " + categoryId);
	}
	
	private ProductDTO convertToProductDTO(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProductId(product.getProductId());
		productDTO.setName(product.getName());
		productDTO.setDescription(product.getDescription());
		productDTO.setPrice(product.getPrice());
	    productDTO.setQuantity(product.getStockQuantity());
	    productDTO.setCategoryId(product.getCategory().getCategoryId());
	    return productDTO;
	}

}
