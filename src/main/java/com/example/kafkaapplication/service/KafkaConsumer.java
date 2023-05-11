package com.example.kafkaapplication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics="advice-topic", groupId = "myGroup")
    public void consume(String message){
        logger.info(String.format("message received -- > %s", message));
    }
}
