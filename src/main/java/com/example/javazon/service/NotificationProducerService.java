package com.example.javazon.service;

import com.example.javazon.model.NotificationMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationProducerService {
    private final KafkaTemplate<String, NotificationMessage> kafkaTemplate;
    private static final String TOPIC = "notifications_topic";

    public void sendWelcomeEmail(String toEmail) {
        NotificationMessage message = NotificationMessage.builder()
                .to(toEmail)
                .subject("Welcome to JavaZon E-Commerce!")
                .body("Thank you for registering at JavaZonE. We're excited to have you on board!")
                .build();
        kafkaTemplate.send(TOPIC, message);
        log.info("Sent notification to topic {}: {}", TOPIC, message);
    }
}
