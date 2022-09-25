package com.development.bookstore_orderservice.dto;


import com.development.bookstore_orderservice.entity.Book;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookResponseDTO {
    public String message;
    public Book book;
    public BookResponseDTO(String message, Book book) {
        this.message = message;
        this.book= book;
    }


}
