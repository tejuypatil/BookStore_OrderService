package com.development.bookstore_orderservice.entity;

import com.development.bookstore_orderservice.dto.OrderRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


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
    private boolean isCanceled;

    // Given from Postman/User
    private int quantity;
    private  String address;
    private int userId;
    /*@ElementCollection
    @CollectionTable(name = "Order_Book",joinColumns =@JoinColumn(name = "order_id"))
    private List<Integer> bookIds;*/
    private  int bookId;
    

    public Order(OrderRequestDTO orderRequestDTO)
    {
        this.quantity=orderRequestDTO.getQuantity();
        this.address=orderRequestDTO.getAddress();
        this.bookId=orderRequestDTO.getBookId();

    }

}
