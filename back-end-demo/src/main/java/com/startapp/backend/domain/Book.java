package com.startapp.backend.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "book", schema = "start_app")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "isbn", nullable = false)
    private Long isbn;

    @Column(name = "title", nullable = false)
    private String title;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "isbn")
    private List<Author> authors = new ArrayList<>();

    @Column(columnDefinition = "date", updatable = false)
    private LocalDateTime createdAt;
    @Column(columnDefinition = "date")
    private LocalDateTime modifiedAt;
}
