package com.blogs.dto;

import lombok.Data;

@Data
public class ProductDTO {

	private Long productId;
	private String name;
	private String description;
	private double price;
	private int quantity;
	private Long categoryId;
}
