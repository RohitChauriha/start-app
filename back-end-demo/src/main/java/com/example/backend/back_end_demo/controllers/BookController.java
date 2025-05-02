package com.example.backend.back_end_demo.controllers;

import com.example.backend.back_end_demo.domain.Book;
import com.example.backend.back_end_demo.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Iterable<Book> list() {
        return bookService.findAll();
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> get(@PathVariable("isbn") String isbn) {
        log.info("fetching book for isbn {}", isbn);
        return bookService.find(isbn).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book, UriComponentsBuilder uriBuilder) {
        Book created = bookService.create(book);
        log.info("created book with isbn {}", book.getIsbn());
        URI newBookUri = uriBuilder.path("/books/{isbn}").build(created.getIsbn());
        return ResponseEntity.created(newBookUri).body(created);
    }

    @PutMapping
    public ResponseEntity<Book> update(@RequestBody Book book) {
        Book updated = bookService.update(book);
        log.info("updated book with isbn {}", book.getIsbn());
        return ResponseEntity.accepted().body(updated);
    }
}
