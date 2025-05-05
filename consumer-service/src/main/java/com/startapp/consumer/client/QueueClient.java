package com.startapp.consumer.client;

import com.startapp.consumer.domain.Message;

public interface QueueClient {
    void receiveMessage(Message message);
}
