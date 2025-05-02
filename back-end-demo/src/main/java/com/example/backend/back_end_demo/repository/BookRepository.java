package com.example.backend.back_end_demo.repository;

import com.example.backend.back_end_demo.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
