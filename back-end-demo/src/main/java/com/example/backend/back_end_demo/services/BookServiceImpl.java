package com.example.backend.back_end_demo.services;

import com.example.backend.back_end_demo.domain.Book;
import com.example.backend.back_end_demo.domain.Message;
import com.example.backend.back_end_demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    @Autowired
    private MessagePublisher messagePublisher;

    @Autowired
    BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book create(Book book) {
        book.setCreatedAt(LocalDateTime.now());
        book.setModifiedAt(LocalDateTime.now());
        Book saved = bookRepository.save(book);
        messagePublisher.sendMessage(new Message("Created\n" + book.toString()));
        return saved;
    }

    @Override
    public Book update(Book book) {
        book.setModifiedAt(LocalDateTime.now());
        return bookRepository.save(book);
    }


    @Override
    public Optional<Book> find(String isbn) {
        return bookRepository.findById(isbn);
    }
}
