package com.blogs.entities;

public class Enums {
	// Enum for User Roles
    public enum UserRole {
        CUSTOMER, ADMIN, DELIVERY_PERSON
    }

    // Enum for Order Statuses
    public enum OrderStatus {
        PENDING, PROCESSING, DELIVERED, CANCELLED
    }

    // Enum for Payment Statuses
    public enum PaymentStatus {
        PENDING, PAID
    }

    // Enum for Delivery Statuses
    public enum DeliveryStatus {
        ASSIGNED, IN_TRANSIT, DELIVERED
    }

    // Enum for Payment Methods
    public enum PaymentMethod {
        CREDIT_CARD, DEBIT_CARD, PAYPAL
    }
}
