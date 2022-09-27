package com.development.bookstore_orderservice.service;

import com.development.bookstore_orderservice.dto.BookRequestDTO;
import com.development.bookstore_orderservice.dto.BookResponseDTO;
import com.development.bookstore_orderservice.dto.OrderRequestDTO;
import com.development.bookstore_orderservice.dto.UserResponseDTO;
import com.development.bookstore_orderservice.entity.Order;
import com.development.bookstore_orderservice.entity.Book;

import com.development.bookstore_orderservice.entity.UserData;
import com.development.bookstore_orderservice.exception.InvalidTokenException;
import com.development.bookstore_orderservice.repository.OrderRepository;
import com.development.bookstore_orderservice.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    TokenUtility tokenUtility;
    @Autowired
    RestTemplate restTemplate;
    @Override
    public Order createOrder( OrderRequestDTO orderRequestDTO,String token) {
        int userId= tokenUtility.decodeToken(token);
        UserResponseDTO userResponseDTO= restTemplate.getForObject("http://localhost:8086/userservice/" + userId,UserResponseDTO.class);
        UserData userData = userResponseDTO.getUserData();
        if(userData!= null)
        {
            Order order = new Order(orderRequestDTO);
            order.setUserId(userData.getUserId());
            order.setLocalDate(LocalDate.now());

            HttpHeaders header = new HttpHeaders();
            header.set("Authorization",token);
            HttpEntity<Void> requestEntity = new HttpEntity<>(header);

            BookResponseDTO bookResponseDTO= restTemplate.exchange("http://localhost:8085/bookservice/" +order.getBookId(),HttpMethod.GET,requestEntity,BookResponseDTO.class).getBody();
            Book book = bookResponseDTO.getBook();
            order.setPrice(order.getQuantity() *book.getPrice());
            book.setQuantity(book.getQuantity()- order.getQuantity());
            BookRequestDTO bookRequestDTO = new BookRequestDTO();
            bookRequestDTO.setAuthor(book.getAuthor());
            bookRequestDTO.setName(book.getName());
            bookRequestDTO.setPrice(book.getPrice());
            bookRequestDTO.setQuantity(book.getQuantity());

            HttpEntity<BookRequestDTO> requestDTOHttpEntity = new HttpEntity<BookRequestDTO>(bookRequestDTO,header);
            ResponseEntity<BookResponseDTO> response= restTemplate.exchange("http://localhost:8085/bookservice/"+book.getBookId(), HttpMethod.PUT,requestDTOHttpEntity,BookResponseDTO.class);
            return orderRepository.save(order);
       }


        else {
            throw new InvalidTokenException(token);
        }
    }

    @Override
    public Order getOrder(int orderId,String token) {
        int userId= tokenUtility.decodeToken(token);
        UserResponseDTO userResponseDTO= restTemplate.getForObject("http://localhost:8086/userservice/" + userId,UserResponseDTO.class);
        UserData userData = userResponseDTO.getUserData();
        if(userData!= null)
        {
        return orderRepository.findById(orderId).get();
        }
        else {
            throw new InvalidTokenException(token);
        }
    }

    @Override
    public Order cancelOrder(int orderId,String token)
    {
        int userId= tokenUtility.decodeToken(token);
        UserResponseDTO userResponseDTO= restTemplate.getForObject("http://localhost:8086/userservice/" + userId,UserResponseDTO.class);
        UserData userData = userResponseDTO.getUserData();
        if(userData!= null)
        {
            Order order = new Order();

            order.setCanceled(true);
            /*HttpHeaders header = new HttpHeaders();
            header.set("Authorization",token);
            HttpEntity<Void> requestEntity = new HttpEntity<>(header);

            BookResponseDTO bookResponseDTO= restTemplate.exchange("http://localhost:8085/bookservice/" +order.getBookId(),HttpMethod.GET,requestEntity,BookResponseDTO.class).getBody();
            Book book = bookResponseDTO.getBook();
            book.setQuantity(book.getQuantity() + order.getQuantity());
            BookRequestDTO bookRequestDTO = new BookRequestDTO();
            bookRequestDTO.setAuthor(book.getAuthor());
            bookRequestDTO.setName(book.getName());
            bookRequestDTO.setPrice(book.getPrice());
            bookRequestDTO.setQuantity(book.getQuantity());

            HttpEntity<BookRequestDTO> requestDTOHttpEntity = new HttpEntity<BookRequestDTO>(bookRequestDTO,header);
            ResponseEntity<BookResponseDTO> response= restTemplate.exchange("http://localhost:8085/bookservice/"+book.getBookId(), HttpMethod.PUT,requestDTOHttpEntity,BookResponseDTO.class);*/
            return orderRepository.save(order);
        }
        else
        {
            throw new InvalidTokenException(token);
        }
    }
}