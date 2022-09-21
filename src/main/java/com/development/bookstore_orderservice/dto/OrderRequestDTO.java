package com.development.bookstore_orderservice.dto;

import com.development.bookstore_orderservice.entity.Book;
import lombok.Data;

@Data
public class OrderRequestDTO {
    private int quantity;
    private  String address;
    private Book book;
}
