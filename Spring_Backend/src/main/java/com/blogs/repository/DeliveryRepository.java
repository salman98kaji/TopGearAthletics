package com.blogs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogs.entities.Delivery;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
	List<Delivery> findByOrder_User_UserId(Long userId);
    List<Delivery> findByDeliveryPerson_UserId(Long deliveryPersonId);
    Delivery findByOrder_OrderId(Long orderId);
}
