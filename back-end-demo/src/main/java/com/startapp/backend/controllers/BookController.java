package com.startapp.backend.controllers;

import com.startapp.backend.domain.Book;
import com.startapp.backend.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Book> list() {
        return bookService.findAll();
    }

    @GetMapping(path = "/{isbn}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> get(@PathVariable("isbn") String isbn) {
        log.info("fetching book for isbn {}", isbn);
        return bookService.find(isbn).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> create(@RequestBody Book book, UriComponentsBuilder uriBuilder) {
        Book created = bookService.create(book);
        log.info("created book with isbn {}", book.getIsbn());
        URI newBookUri = uriBuilder.path("/book/{isbn}").build(created.getIsbn());
        return ResponseEntity.created(newBookUri).body(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> update(@RequestBody Book book) {
        Book updated = bookService.update(book);
        log.info("updated book with isbn {}", book.getIsbn());
        return ResponseEntity.accepted().body(updated);
    }
}
