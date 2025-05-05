package com.startapp.consumer.service;

import com.startapp.consumer.domain.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class QueueClientImpl implements QueueClient {
    private final MessageService messageService;

    public QueueClientImpl(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    @RabbitListener(queues = "${queueName.amqp.queue}")
    public void receiveMessage(Message message) {
        log.info("consumer received > " + message.toString());
        messageService.save(message);
    }
}
