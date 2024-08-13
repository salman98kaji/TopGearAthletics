package com.blogs.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogs.dto.ProductDTO;
import com.blogs.entities.Product;
import com.blogs.repository.CategoryRepository;
import com.blogs.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public ProductDTO addProduct(ProductDTO productDTO) {
		Product product =convertToEntity(productDTO);
		
		//set the category if it exists
		categoryRepository.findById(productDTO.getCategoryId()).ifPresent(product::setCategory);
		
		Product savedProduct = productRepository.save(product);
		return convertToDTO(savedProduct);
	}


	@Override
	public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {
		Optional<Product> existingProduct = productRepository.findById(productId);
		if(existingProduct.isPresent()) {
			Product product = existingProduct.get();
			product.setName(productDTO.getName());
			product.setDescription(productDTO.getDescription());
			product.setPrice(productDTO.getPrice());
			product.setStockQuantity(productDTO.getQuantity());
			
			//update category if exists
			categoryRepository.findById(productDTO.getCategoryId()).ifPresent(product::setCategory);
			
			Product updateProduct = productRepository.save(product);
			return convertToDTO(updateProduct);
		}
		throw new RuntimeException("Product not found with ID: " + productId);
	}

	@Override
	public void deleteProduct(Long productId) {
		productRepository.deleteById(productId);
	}

	@Override
	public Optional<ProductDTO> getProductById(Long productId) {
		return productRepository.findById(productId).map(this::convertToDTO);
	}

	@Override
	public List<ProductDTO> searchProducts(String name) {
		List<Product> product = productRepository.findByNameContainingIgnoreCase(name);
		return product.stream().map(this::convertToDTO).collect(Collectors.toList());
	}
	
	private Product convertToEntity(ProductDTO productDTO) {
		Product product = new Product();
		product.setName(productDTO.getName());
		product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getQuantity());
		return product;
	}
	
	private ProductDTO convertToDTO(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProductId(product.getProductId());
		productDTO.setName(product.getName());
		productDTO.setDescription(product.getDescription());
		productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getStockQuantity());
        productDTO.setCategoryId(product.getCategory() != null ? product.getCategory().getCategoryId():null);
		return productDTO;
	}

	   
}
