package com.blogs.service;

import com.blogs.dto.PaymentRequestDTO;
import com.blogs.dto.PaymentResponseDTO;

public interface PaymentService {

	PaymentResponseDTO makePayment(PaymentRequestDTO paymentRequestDTO);
    PaymentResponseDTO getPaymentByOrderId(Long orderId);
}
