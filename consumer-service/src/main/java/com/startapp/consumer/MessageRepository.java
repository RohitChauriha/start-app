package com.startapp.consumer;

import com.startapp.consumer.domain.Message;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Id> {
}
