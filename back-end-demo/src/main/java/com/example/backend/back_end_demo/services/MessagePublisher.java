package com.example.backend.back_end_demo.services;

import com.example.backend.back_end_demo.domain.Message;

public interface MessagePublisher {
    void sendMessage(Message message);
}
