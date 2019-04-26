package com.ivankov.data;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import java.util.Objects;

@Immutable
@ParametersAreNonnullByDefault
public class Message implements Comparable<Message> {

    public final String text;
    public final int priority;
    public final long timestamp;

    public Message(String text, int priority, long timestamp) {
        this.text = text;
        this.priority = priority;
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(Message otherMessage) {
        return this.priority == otherMessage.priority
                ? Long.compare(this.timestamp, otherMessage.timestamp)
                : Integer.compare(this.priority, otherMessage.priority);
    }

    @Override
    public String toString() {
        return "Message{" +
                "text='" + text + '\'' +
                ", priority=" + priority +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return priority == message.priority &&
                timestamp == message.timestamp &&
                text.equals(message.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, priority, timestamp);
    }
}
