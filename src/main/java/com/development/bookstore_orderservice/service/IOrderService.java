package com.development.bookstore_orderservice.service;

import com.development.bookstore_orderservice.dto.OrderRequestDTO;
import com.development.bookstore_orderservice.entity.Order;

public interface IOrderService {

    public Order createOrder(OrderRequestDTO orderRequestDTO,String token);

    Order getOrder(int orderId,String token);

    Order cancelOrder(int orderId,String token);
}
