package com.development.bookstore_orderservice.entity;

import com.development.bookstore_orderservice.dto.OrderRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
@NoArgsConstructor
@Table(name = "OrderData")
public class Order {

    // Automatically set
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int orderId;
    private LocalDate localDate;
    private int price;
//    @JsonIgnoreProperties({"applications","hibernateLazyInitializer"})
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "userId")
//    private  UserData user;
    private boolean isCanceled;

    // Given from Postman/User
    private int quantity;
    private  String address;


//    @JsonIgnoreProperties({"applications","hibernateLazyInitializer"})
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "bookId")
//    private Book book;

    public Order(OrderRequestDTO orderRequestDTO)
    {
        this.quantity=orderRequestDTO.getQuantity();
        this.address=orderRequestDTO.getAddress();

    }

}
