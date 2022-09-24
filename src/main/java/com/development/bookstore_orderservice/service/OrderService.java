package com.development.bookstore_orderservice.service;

import com.development.bookstore_orderservice.dto.OrderRequestDTO;
import com.development.bookstore_orderservice.entity.Order;
import com.development.bookstore_orderservice.entity.Book;

import com.development.bookstore_orderservice.entity.UserData;
import com.development.bookstore_orderservice.exception.InvalidTokenException;
import com.development.bookstore_orderservice.repository.OrderRepository;
import com.development.bookstore_orderservice.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    OrderRepository orderRepository;
    @Override
    public Order createOrder( OrderRequestDTO orderRequestDTO) {
       /* int userId= tokenUtility.decodeToken(token);
        Optional<UserData> optionalUserData = userRepository.findById(userId);
        if(optionalUserData.isPresent())
        {*/
        Order order = new Order(orderRequestDTO);
        // UserData loggedInUserData = optionalUserData.get();
        //order.setUser(loggedInUserData);
        order.setLocalDate(LocalDate.now());

          /*  Book book=bookRepository.findById(order.getBook().getBookId()).get();
            order.setPrice(order.getQuantity() * book.getPrice());
           // book.setQuantity( book.getQuantity()- order.getQuantity());
           // bookRepository.save(book);
            //order.setBook(book);*/
        return orderRepository.save(order);
       /* }


        else {
            throw new InvalidTokenException(token);
        }*/
    }

    @Override
    public Order getOrder(int orderId) {
        /*int userId= tokenUtility.decodeToken(token);
        Optional<UserData> optionalUserData = userRepository.findById(userId);
        if(optionalUserData.isPresent())
        {*/
        return orderRepository.findById(orderId).get();
       /* }
        else {
            throw new InvalidTokenException(token);
        }*/
    }

    @Override
    public Order cancelOrder(int orderId) {
        /*int userId= tokenUtility.decodeToken(token);
        Optional<UserData> optionalUserData = userRepository.findById(userId);
        if(optionalUserData.isPresent()) {*/
        Order order = new Order();
        order.setCanceled(true);
            /*Book book = bookRepository.findById(order.getBook().getBookId()).get();
            book.setQuantity(book.getQuantity() + order.getQuantity());
            bookRepository.save(book);
            order.setBook(book);*/
        return orderRepository.save(order);
        /*}
        else
        {
            throw new InvalidTokenException(token);
        }*/
    }
}