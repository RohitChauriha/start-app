package com.startapp.consumer.service;

import com.startapp.consumer.MessageRepository;
import com.startapp.consumer.domain.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageConsumerImpl implements MessageConsumer {
    private final MessageRepository messageRepository;

    public MessageConsumerImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    @RabbitListener(queues = "${queueName.amqp.queue}")
    public void receiveMessage(Message message) {
        log.info("consumer received > " + message.toString());
        messageRepository.save(message);
    }
}
