package com.ivankov.service;

import com.ivankov.data.Message;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@ParametersAreNonnullByDefault
public class MessageProducer extends Thread {

    private final static ThreadLocalRandom generator = ThreadLocalRandom.current();

    private final MessageManager messageManager;
    private final int prioritySeed;
//    private final int textLengthSeed;

    public MessageProducer(MessageManager messageManager, int prioritySeed) {
        this.messageManager = messageManager;
        this.prioritySeed = prioritySeed;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                System.out.println("Producer in");
                Thread.sleep(500);
                messageManager.add(generateMessage(prioritySeed));
                System.out.println("Producer out");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static Message generateMessage(int prioritySeed) {
        return new Message(generateRandomText(), generateRandomPriority(prioritySeed), System.nanoTime());
    }

    private static String generateRandomText() {
        return UUID.randomUUID().toString();
//        int textLength = generator.nextInt(textLengthSeed);
//        byte[] array = new byte[textLength];
//        generator.nextBytes(array);
//        return new String(array, StandardCharsets.UTF_8);
    }

    private static int generateRandomPriority(int prioritySeed) {
        return generator.nextInt(prioritySeed);
    }
}
