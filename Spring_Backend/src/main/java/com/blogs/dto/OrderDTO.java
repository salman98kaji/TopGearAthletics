package com.blogs.dto;

import java.util.List;

import com.blogs.entities.Enums.OrderStatus;

import lombok.Data;

@Data
public class OrderDTO {
	
	private Long orderId;
    private Long userId;
    private String userName;
    private List<OrderItemDTO> orderItems;
    private double totalAmount;
    private OrderStatus orderStatus;
    
}
