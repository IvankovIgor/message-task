package com.ivankov;

import com.ivankov.service.MessageHandler;
import com.ivankov.service.MessageManager;
import com.ivankov.service.MessageProducer;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
public class MessageTaskApplication {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Priority seed argument is absent");
            return;
        }
        int prioritySeed = 0;
        try {
            prioritySeed = Integer.valueOf(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Priority seed argument is not a valid number");
        }

        MessageManager messageManager = new MessageManager(List.of(new MessageHandler(), new MessageHandler()));
        messageManager.startHandlers();
        MessageProducer messageProducer = new MessageProducer(messageManager, prioritySeed);
        messageProducer.start();
    }
}
