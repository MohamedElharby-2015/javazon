package com.example.javazon.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationMessage {
    private String to;
    private String subject;
    private String body;
}
