package com.development.bookstore_orderservice.dto;

import com.development.bookstore_orderservice.entity.Order;

public class OrderResponseDTO {
    public String message;
    public Order order;
    public OrderResponseDTO(String message, Order order) {
        this.message = message;
        this.order= order;
    }
}
