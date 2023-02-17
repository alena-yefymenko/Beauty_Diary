package com.example.beautydiary.repositories;

import com.example.beautydiary.entities.Customer;
import com.example.beautydiary.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<User, Long> {
    Customer findByEmailAndPassword(String email,String password);
}