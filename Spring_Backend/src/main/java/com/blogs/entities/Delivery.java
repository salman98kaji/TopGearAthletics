package com.blogs.entities;

import lombok.Data;

import javax.persistence.*;

import com.blogs.entities.Enums.DeliveryStatus;

@Data
@Entity
public class Delivery {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryId;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "delivery_person_id")
    private User deliveryPerson;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;
}

