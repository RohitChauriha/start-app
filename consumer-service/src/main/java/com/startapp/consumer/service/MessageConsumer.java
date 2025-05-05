package com.startapp.consumer.service;

import com.startapp.consumer.domain.Message;

public interface MessageConsumer {
    void receiveMessage(Message message);
}
