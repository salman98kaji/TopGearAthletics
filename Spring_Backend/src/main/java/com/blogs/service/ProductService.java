package com.blogs.service;

import java.util.List;
import java.util.Optional;

import com.blogs.dto.ProductDTO;

public interface ProductService {
	ProductDTO addProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long productId, ProductDTO productDTO);
    void deleteProduct(Long productId);
    Optional<ProductDTO> getProductById(Long productId);
    List<ProductDTO> searchProducts(String name);
}
