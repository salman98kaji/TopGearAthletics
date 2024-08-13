package com.blogs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogs.dto.DeliveryDTO;
import com.blogs.entities.Delivery;
import com.blogs.entities.Enums.DeliveryStatus;
import com.blogs.entities.Order;
import com.blogs.entities.User;
import com.blogs.repository.DeliveryRepository;
import com.blogs.repository.OrderRepository;
import com.blogs.repository.UserRepository;

@Service
public class DeliveryServiceImpl implements DeliveryService {

	@Autowired
	private DeliveryRepository deliveryRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public DeliveryDTO assignDeliveryPerson(Long orderId, Long deliveryPersonId) {
		// find order and delivery person
		Order order = orderRepository.findById(orderId)
				.orElseThrow(()-> new RuntimeException("Order not found"));
		
		User deliveryPerson = userRepository.findById(deliveryPersonId)
				.orElseThrow(() -> new RuntimeException("Delivery person not found"));

		//create a new delivery
		Delivery delivery = new Delivery();
		delivery.setOrder(order);
		delivery.setDeliveryPerson(deliveryPerson);
		delivery.setDeliveryStatus(DeliveryStatus.ASSIGNED);
		
		//save the delivery
		deliveryRepository.save(delivery);
		
		return convertToDTO(delivery);

	}

	private DeliveryDTO convertToDTO(Delivery delivery) {
		DeliveryDTO deliveryDTO = new DeliveryDTO();
		deliveryDTO.setDeliveryId(delivery.getDeliveryId());
		deliveryDTO.setDeliveryPersonId(delivery.getDeliveryPerson().getUserId());
		deliveryDTO.setDeliveryPersonName(delivery.getDeliveryPerson().getUsername());
		deliveryDTO.setOrderId(delivery.getOrder().getOrderId());
		deliveryDTO.setDeliveryStatus(delivery.getDeliveryStatus());
		return deliveryDTO;
	}

	@Override
	public List<DeliveryDTO> getDeliveriesByUserId(Long userId) {
		List<Delivery> deliveries = deliveryRepository.findByOrder_User_UserId(userId);
		return deliveries.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<DeliveryDTO> getDeliveriesByDeliveryPersonId(Long deliveryPersonId) {
		List<Delivery> deliveries = deliveryRepository.findByDeliveryPerson_UserId(deliveryPersonId);
		return deliveries.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public DeliveryDTO updateDeliveryStatus(Long deliveryId, String deliveryStatus) {
		Delivery delivery = deliveryRepository.findById(deliveryId)
				.orElseThrow(()-> new RuntimeException("Delivery not found"));
		
		delivery.setDeliveryStatus(DeliveryStatus.valueOf(deliveryStatus));
		deliveryRepository.save(delivery);
		return convertToDTO(delivery);
	}

	@Override
	public DeliveryDTO getDeliveryStatusByOrderId(Long orderId) {
		Delivery delivery = deliveryRepository.findByOrder_OrderId(orderId);
		return convertToDTO(delivery);
	}

}
