package com.blogs.dto;

import java.util.List;

import lombok.Data;

@Data
public class CartDTO {
	private Long cartId;
	private Long userId;
	private double totalAmount;
	private List<CartItemDTO> cartItems;
	
}
