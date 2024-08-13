package com.blogs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogs.dto.OrderDTO;
import com.blogs.dto.OrderItemDTO;
import com.blogs.entities.Enums.OrderStatus;
import com.blogs.entities.Order;
import com.blogs.entities.OrderItem;
import com.blogs.entities.Product;
import com.blogs.entities.User;
import com.blogs.repository.OrderRepository;
import com.blogs.repository.ProductRepository;
import com.blogs.repository.UserRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public OrderDTO placeOrder(Long userId, List<OrderItemDTO> orderItems) {
		
		// Find the user
		User user = userRepository.findById(userId)
				.orElseThrow(()-> new RuntimeException("User not found"));
		
		//create a new order
		Order order = new Order();
		order.setUser(user);
		order.setStatus(OrderStatus.PENDING);
		
		//Calculate total amount
		double totalAmount=0;
		for(OrderItemDTO itemDTO : orderItems) {
			Product product = productRepository.findById(itemDTO.getProductId())
					.orElseThrow(()-> new RuntimeException("Product not found"));
			
			OrderItem orderItem = new OrderItem();
			orderItem.setProduct(product);
			orderItem.setQuantity(itemDTO.getQuantity());
			orderItem.setPrice(product.getPrice());
			orderItem.setTotalPrice(itemDTO.getQuantity()*product.getPrice());
			orderItem.setOrder(order);
			
			totalAmount += orderItem.getTotalPrice();
			order.getOrderItems().add(orderItem);
			
		}
		order.setTotalAmount(totalAmount);
		orderRepository.save(order);
		
		return convertToDTO(order);
	}

	private OrderDTO convertToDTO(Order order) {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setOrderId(order.getOrderId());
		orderDTO.setUserId(order.getUser().getUserId());
		orderDTO.setUserName(order.getUser().getUsername());
		orderDTO.setOrderStatus(order.getStatus());
		orderDTO.setTotalAmount(order.getTotalAmount());
		
		List<OrderItemDTO> orderItemDTOs = order.getOrderItems().stream()
				.map(this::convertToOrderItemDTO)
				.collect(Collectors.toList());
		
		orderDTO.setOrderItems(orderItemDTOs);
		return orderDTO;
	}
	
	private OrderItemDTO convertToOrderItemDTO(OrderItem orderItem) {
		OrderItemDTO orderItemDTO = new OrderItemDTO();
		orderItemDTO.setProductId(orderItem.getProduct().getProductId());
		orderItemDTO.setProductName(orderItem.getProduct().getName());
		orderItemDTO.setQuantity(orderItem.getQuantity());
        orderItemDTO.setPrice(orderItem.getPrice());
        orderItemDTO.setTotalPrice(orderItem.getTotalPrice());
        
        return orderItemDTO;
	}

	@Override
	public List<OrderDTO> getAllOrders() {
		List<Order> orders = orderRepository.findAll();
		return orders.stream()
				.map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public List<OrderDTO> getOrdersByUserId(Long userId) {
		List<Order> orders = orderRepository.findByUser_UserId(userId);
		return orders.stream().map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public OrderDTO getOrderByOrderId(Long orderId) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new RuntimeException("Order not found"));
		return convertToDTO(order);
	}

}
