package com.blogs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.blogs.dto.PaymentRequestDTO;
import com.blogs.dto.PaymentResponseDTO;
import com.blogs.entities.Enums.PaymentStatus;
import com.blogs.entities.Order;
import com.blogs.entities.Payment;
import com.blogs.repository.OrderRepository;
import com.blogs.repository.PaymentRepository;

public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	@Transactional
	public PaymentResponseDTO makePayment(PaymentRequestDTO paymentRequestDTO) {
		Order order = orderRepository.findById(paymentRequestDTO.getOrderId())
				.orElseThrow(()->  new RuntimeException("Order not found for ID: " + paymentRequestDTO.getOrderId()));
		
		//Assuming the payment is successful 
		Payment payment = new Payment();
		payment.setOrder(order);
		payment.setPaymentMethod(paymentRequestDTO.getPaymentMethod());
		payment.setAmount(paymentRequestDTO.getAmount());
		payment.setPaymentStatus(PaymentStatus.PAID);
		
		//save payment to the database
		Payment savedPayment = paymentRepository.save(payment);
		
		//Convert the saved Payment entity to PaymentResponseDTO
		return convertToDTO(savedPayment);
		
	}

	private PaymentResponseDTO convertToDTO(Payment payment) {
		 PaymentResponseDTO dto = new PaymentResponseDTO();
	     dto.setPaymentId(payment.getPaymentId());
	     dto.setOrderId(payment.getOrder().getOrderId());
	     dto.setAmount(payment.getAmount());
	     dto.setPaymentMethod(payment.getPaymentMethod());
	     dto.setPaymentStatus(payment.getPaymentStatus());
		 return dto;
	}

	@Override
	public PaymentResponseDTO getPaymentByOrderId(Long orderId) {
		Payment payment = paymentRepository.findByOrder_OrderId(orderId)
				.orElseThrow(()->new RuntimeException("Payment not found for Order ID: " + orderId));
		return convertToDTO(payment);
	}

}
