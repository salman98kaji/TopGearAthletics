package com.blogs.service;

import com.blogs.dto.CartDTO;
import com.blogs.dto.CartItemDTO;

public interface CartService {
	CartDTO addProductToCart(Long userId, CartItemDTO cartItemDTO);
    CartDTO removeProductFromCart(Long userId, Long productId);
    CartDTO getCartByUserId(Long userId);
    double calculateTotalAmount(Long cartId);
}
