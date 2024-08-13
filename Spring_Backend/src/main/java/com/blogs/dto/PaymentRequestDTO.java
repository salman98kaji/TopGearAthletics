package com.blogs.dto;

import com.blogs.entities.Enums.PaymentMethod;

import lombok.Data;

@Data
public class PaymentRequestDTO {

	private Long orderId;
    private PaymentMethod paymentMethod; // Enum for payment method
    private Double amount;
}
