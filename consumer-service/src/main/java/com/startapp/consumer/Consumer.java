package com.startapp.consumer;

import com.startapp.consumer.domain.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {
    @RabbitListener(queues = "${queueName.amqp.queue}")
    public void processToDo(Message message) {
        log.info("Consumer Received!!> " + message.toString());
    }
}
