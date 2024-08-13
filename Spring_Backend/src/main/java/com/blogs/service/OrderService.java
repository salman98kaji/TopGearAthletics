package com.blogs.service;

import java.util.List;

import com.blogs.dto.OrderDTO;
import com.blogs.dto.OrderItemDTO;

public interface OrderService {

	//userId tells the system which user is placing the order.orderItems is a list containing details of all the products that the user wants to order, including quantities and prices.
	OrderDTO placeOrder(Long userId, List<OrderItemDTO> orderItems);
	List<OrderDTO>getAllOrders();
	List<OrderDTO> getOrdersByUserId(Long userId);
	OrderDTO getOrderByOrderId(Long orderId);
}
