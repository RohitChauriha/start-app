package com.startapp.backend.services;

import com.startapp.backend.domain.Book;

import java.util.Optional;

public interface BookService {
    Iterable<Book> findAll();
    Book create(Book book);
    Optional<Book> find(String isbn);
    Book update(Book book);
}
