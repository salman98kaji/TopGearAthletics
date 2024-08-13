package com.blogs.dto;

import java.time.LocalDateTime;

import com.blogs.entities.Enums.PaymentMethod;
import com.blogs.entities.Enums.PaymentStatus;

import lombok.Data;

@Data
public class PaymentResponseDTO {

	private Long paymentId;
    private Long orderId;
    private PaymentMethod paymentMethod;
    private Double amount;
    private PaymentStatus paymentStatus;
    private LocalDateTime paymentDate;
}
