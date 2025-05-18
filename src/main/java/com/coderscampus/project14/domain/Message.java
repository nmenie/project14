package com.coderscampus.project14.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String content;
    private String sender;
    private String channel; // e.g., "general", more channels can be added later
    private LocalDateTime timestamp = LocalDateTime.now();

    public Message() {}

    public Message(String content, String sender, String channel) {
        this.content = content;
        this.sender = sender;
        this.channel = channel;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", sender='" + sender + '\'' +
                ", channel='" + channel + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
