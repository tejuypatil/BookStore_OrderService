package com.development.bookstore_orderservice.repository;

import com.development.bookstore_orderservice.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserData,Integer> {
}
