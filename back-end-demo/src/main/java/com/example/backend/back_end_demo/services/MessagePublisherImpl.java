package com.example.backend.back_end_demo.services;

import com.example.backend.back_end_demo.domain.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessagePublisherImpl implements MessagePublisher {
    private final RabbitTemplate rabbitTemplate;

    @Value("${queueName.amqp.queue}")
    private String destination;

    public MessagePublisherImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private void send(Message message) {
        this.rabbitTemplate.convertAndSend(destination, message);
        log.info("{}: sent massage {}", System.getProperty("HOSTNAME"), message);
    }

    @Override
    public void sendMessage(Message message) {
        this.send(message);
    }
}
