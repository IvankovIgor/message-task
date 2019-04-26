package com.ivankov.service;

import com.ivankov.data.Message;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
public class MessageManager {

    private final List<MessageHandler> messageHandlers;

    public MessageManager(List<MessageHandler> messageHandlers) {
        this.messageHandlers = messageHandlers;
    }

    public void add(Message message) {
        messageHandlers.forEach(messageHandler -> messageHandler.add(message));
    }

    public void startHandlers() {
        messageHandlers.forEach(Thread::start);
    }
}
