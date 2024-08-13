package com.blogs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogs.dto.CategoryDTO;
import com.blogs.dto.ProductDTO;
import com.blogs.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO){
		CategoryDTO createdCategory = categoryService.addCategory(categoryDTO);
		return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDTO> updateCategory(@PathVariable("id") Long categoryId, @RequestBody  CategoryDTO categoryDTO){
		CategoryDTO updatedCategory = categoryService.updateCategory(categoryId, categoryDTO);
		return ResponseEntity.ok(updatedCategory);
	}
	
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> getAllCategories(){
		List<CategoryDTO> categories = categoryService.getAllCategories();
		return ResponseEntity.ok(categories);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("id") Long categoryId){
		return categoryService.getCategoryById(categoryId)
				.map(ResponseEntity::ok)
				.orElseGet(()-> ResponseEntity.notFound().build());			
	}
	
	@GetMapping("/{id}/products")
	public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable("id") Long categoryId){
		try {
			List<ProductDTO> products = categoryService.getProductsByCategory(categoryId);
			return ResponseEntity.ok(products);
		}catch(RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
