package com.startapp.consumer.repository;

import com.startapp.consumer.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}