package com.example.backend.back_end_demo.services;

import com.example.backend.back_end_demo.domain.Book;

import java.util.Optional;

public interface BookService {
    Iterable<Book> findAll();
    Book create(Book book);
    Optional<Book> find(String isbn);
    Book update(Book book);
}
