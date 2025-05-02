package com.example.backend.back_end_demo;

import com.example.backend.back_end_demo.domain.Book;
import com.example.backend.back_end_demo.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Arrays;


@SpringBootApplication
@Slf4j
public class BackEndDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackEndDemoApplication.class, args);
    }

//    @Autowired
//    BookService bookService;
//
//    @Bean
//    public CommandLineRunner startup() {
//
//        return args -> {
//            bookService.create(Book.builder().title("First Book").authors(Arrays.asList("author1", "author2")).createdAt(LocalDateTime.now()).modifiedAt(LocalDateTime.now()).build());
//            bookService.create(Book.builder().title("Second Book").authors(Arrays.asList("author2", "author3")).createdAt(LocalDateTime.now()).modifiedAt(LocalDateTime.now()).build());
//            System.out.println("Database initialized!");
//            Iterable<Book> books = bookService.findAll();
//            books.forEach(System.out::println);
//
//        };
//    }
}
