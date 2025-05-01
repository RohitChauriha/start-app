package com.example.backend.back_end_demo.repository;

import com.example.backend.back_end_demo.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByLastname(String lastname);

    Customer findById(long id);
}
