package com.blogs.service;

import java.util.List;

import com.blogs.dto.DeliveryDTO;

public interface DeliveryService {

	DeliveryDTO assignDeliveryPerson(Long orderId, Long deliveryPersonId);
	List<DeliveryDTO> getDeliveriesByUserId(Long userId);
	List<DeliveryDTO> getDeliveriesByDeliveryPersonId(Long deliveryPersonId);
	DeliveryDTO updateDeliveryStatus(Long deliveryId, String deliveryStatus);
	DeliveryDTO getDeliveryStatusByOrderId(Long orderId);
}
