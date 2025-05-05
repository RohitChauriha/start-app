package com.startapp.consumer.service;

import com.startapp.consumer.domain.Message;
import com.startapp.consumer.repository.MessageRepository;

import java.util.List;
import java.util.Optional;

public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Optional<Message> findById(Long messageId) {
        return messageRepository.findById(messageId);
    }
}
