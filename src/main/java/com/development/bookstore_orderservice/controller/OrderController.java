package com.development.bookstore_orderservice.controller;

import com.development.bookstore_orderservice.dto.OrderRequestDTO;
import com.development.bookstore_orderservice.dto.OrderResponseDTO;
import com.development.bookstore_orderservice.entity.Order;
import com.development.bookstore_orderservice.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    public IOrderService orderService;
    @PostMapping("/orderservice")
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO,@RequestHeader(name = "Authorization")String token){
        Order order = orderService.createOrder(orderRequestDTO,token);
        return new ResponseEntity<OrderResponseDTO>(new OrderResponseDTO("Inserted book data successfully",order), HttpStatus.OK);
    }

    @GetMapping("/orderservice/{orderId}")
    public ResponseEntity<OrderResponseDTO> getBookById(@PathVariable("orderId") int bookId,@RequestHeader(name = "Authorization")String token){
        Order book = orderService.getOrder(bookId,token);
        return new ResponseEntity<OrderResponseDTO>(new OrderResponseDTO("Get call for Id successful",book),HttpStatus.OK);

    }
    @PutMapping("/orderservice/{orderId}")
    public ResponseEntity<OrderResponseDTO>cancelOrder(@PathVariable("orderId")int orderId,@RequestHeader(name = "Authorization")String token){
        Order order = orderService.cancelOrder(orderId,token);
        return new ResponseEntity<OrderResponseDTO>(new OrderResponseDTO("Order cancelled successfully",order),HttpStatus.OK);
    }
}
