package com.blogs.dto;

import lombok.Data;

@Data
public class CartItemDTO {

	private Long cartItemId;
	private Long productId;
    private String productName;
    private double price;
    private int quantity;
    private double totalPrice;
}
