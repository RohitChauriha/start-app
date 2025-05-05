package com.startapp.consumer.service;

import com.startapp.consumer.domain.Message;
import com.startapp.consumer.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message save(Message message) {
        Message saved = messageRepository.save(message);
        log.info("saved message with id {}", saved.getId());
        return saved;
    }

    @Override
    public List<Message> findAll() {
        log.info("fetching all messages");
        return messageRepository.findAll();
    }

    @Override
    public Optional<Message> findById(Long messageId) {
        log.info("finding message by id {}", messageId);
        return messageRepository.findById(messageId);
    }
}
