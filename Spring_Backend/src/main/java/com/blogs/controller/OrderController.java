package com.blogs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogs.dto.OrderDTO;
import com.blogs.dto.OrderItemDTO;
import com.blogs.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@PostMapping("/place")
	public ResponseEntity<OrderDTO> placeOrder(@RequestParam("uderId") Long userId, @RequestBody List<OrderItemDTO> oredrItems){
		OrderDTO orderDTO = orderService.placeOrder(userId, oredrItems);
		return new ResponseEntity<>(orderDTO, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<OrderDTO>> getAllOrders(){
		List<OrderDTO> orders = orderService.getAllOrders();
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
	
	@GetMapping("/my-orders")
	public ResponseEntity<List<OrderDTO>> getOrdersByUserId(@RequestParam("userId") Long userId){
		List<OrderDTO> orders = orderService.getOrdersByUserId(userId);
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderDTO> getOrderById(@PathVariable("orderId") Long orderId){
		OrderDTO orderDTO = orderService.getOrderByOrderId(orderId);
		return new ResponseEntity<>(orderDTO, HttpStatus.OK);
	}
	
	
	
	
}
