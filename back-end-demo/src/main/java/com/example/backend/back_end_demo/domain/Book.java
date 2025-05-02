package com.example.backend.back_end_demo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "book", schema = "start_app")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long isbn;

    @Column(nullable = false)
    private String title;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "author", joinColumns = @JoinColumn(name = "isbn"))
    @Column(name = "authors", nullable = false) // 3
    private List<String> authors = new ArrayList<>();
    @Column(columnDefinition = "date", insertable = true, updatable = false)
    private LocalDateTime createdAt;
    @Column(columnDefinition = "date", insertable = true, updatable = true)
    private LocalDateTime modifiedAt;
}
