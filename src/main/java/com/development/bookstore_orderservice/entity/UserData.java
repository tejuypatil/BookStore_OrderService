package com.development.bookstore_orderservice.entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int userId;
    private String firstName;
    private String lastName;
    @Column(unique = true,nullable = false)
    private String email;
    private  String address;

    @Column(unique = true, nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean isAdmin;

}