package com.blogs.dto;

import lombok.Data;

@Data
public class OrderItemDTO {

	  private Long productId;
	  private String productName;
	  private int quantity;
	  private double price;
	  private double totalPrice;
}
