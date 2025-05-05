package com.startapp.consumer.service;

import com.startapp.consumer.domain.Message;

public interface QueueClient {
    void receiveMessage(Message message);
}
