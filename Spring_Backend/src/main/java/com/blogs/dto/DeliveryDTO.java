package com.blogs.dto;

import com.blogs.entities.Enums.DeliveryStatus;
import com.blogs.entities.Enums.OrderStatus;

import lombok.Data;

@Data
public class DeliveryDTO {

	private Long deliveryId;
    private Long orderId;
    private Long deliveryPersonId;
    private String deliveryPersonName;
    private OrderStatus orderStatus;
    private DeliveryStatus deliveryStatus;
}
