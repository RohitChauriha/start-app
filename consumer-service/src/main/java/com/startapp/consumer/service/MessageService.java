package com.startapp.consumer.service;

import com.startapp.consumer.domain.Message;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    Message save(Message message);
    List<Message> findAll();
    Optional<Message> findById(Long messageId);
}
