package com.blogs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogs.dto.DeliveryDTO;
import com.blogs.service.DeliveryService;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

	@Autowired
	private DeliveryService deliveryService;
	
	@PostMapping("/assign")
	public ResponseEntity<DeliveryDTO> assignDeliveryPerson(@RequestParam("orderId")Long orderId, 
															@RequestParam("deliveryPersonId") Long deliveryPersonId){
		DeliveryDTO deliveryDTO = deliveryService.assignDeliveryPerson(orderId, deliveryPersonId);
		return new ResponseEntity<>(deliveryDTO, HttpStatus.CREATED);
	}
	
	@PutMapping("/update-status/{deliveryId}")
	public ResponseEntity<DeliveryDTO> updateDeliveryStatus(@PathVariable("deliveryid") Long deliveryId,
															@RequestParam("status") String deliveryStatus){
		DeliveryDTO deliveryDTO = deliveryService.updateDeliveryStatus(deliveryId, deliveryStatus);
		return new ResponseEntity<>(deliveryDTO, HttpStatus.OK);
	}
	
	@GetMapping("/user")
	public ResponseEntity<List<DeliveryDTO>> getDeliveriesByUser(@RequestParam("userId") Long userId){
		List<DeliveryDTO> deliveries = deliveryService.getDeliveriesByUserId(userId);
		return new ResponseEntity<>(deliveries, HttpStatus.OK);
	}
	
	@GetMapping("/delivery-person")
	public ResponseEntity<List<DeliveryDTO>> getDeliveriesByDeliveryPersonId(@RequestParam("deliveryPersonId") Long deliveryPersonId){
		List<DeliveryDTO> deliveries = deliveryService.getDeliveriesByDeliveryPersonId(deliveryPersonId);
		return new ResponseEntity<>(deliveries, HttpStatus.OK);
	}
	
	@GetMapping("/status/{orderId}")
	public ResponseEntity<DeliveryDTO> getDeliveryStatusByOrderId(@RequestParam("orderId") Long orderId){ 
		DeliveryDTO deliveryDTO = deliveryService.getDeliveryStatusByOrderId(orderId);
		return new ResponseEntity<>(deliveryDTO, HttpStatus.OK);
	}
	
}
