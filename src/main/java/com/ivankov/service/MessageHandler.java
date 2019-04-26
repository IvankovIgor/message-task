package com.ivankov.service;

import com.ivankov.data.Message;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collections;
import java.util.concurrent.PriorityBlockingQueue;

@ParametersAreNonnullByDefault
public class MessageHandler extends Thread {

    private static final PriorityBlockingQueue<Message> queue
            = new PriorityBlockingQueue<>(11, Collections.reverseOrder());

    public void add(Message message) {
        queue.offer(message);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("Consumer in");
                Message message = queue.take();
                System.out.println(message.text);
                Thread.sleep(1000);
                System.out.println("Consumer out");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
