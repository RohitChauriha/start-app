package com.startapp.backend.services;

import com.startapp.backend.domain.Message;

public interface MessagePublisher {
    void sendMessage(Message message);
}
