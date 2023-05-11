package com.example.kafkaapplication.Controller;

import com.example.kafkaapplication.service.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("api/kafka")
public class HelloKafkaController {

    private static final Logger logger =
            LoggerFactory.getLogger(HelloKafkaController.class);


    @Autowired
    private final KafkaTemplate<String, Object> template;
    private final String topicName;
    private final int messagesPerRequest;
    private CountDownLatch latch;

    @Autowired
    private KafkaProducer kafkaProducer;


    public HelloKafkaController(
            final KafkaTemplate<String, Object> template,
            @Value("${tpd.topic-name}") final String topicName,
            @Value("${tpd.messages-per-request}") final int messagesPerRequest) {
        this.template = template;
        this.topicName = topicName;
        this.messagesPerRequest = messagesPerRequest;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello(@RequestParam("message") String message) throws Exception {
        latch = new CountDownLatch(messagesPerRequest);
        for(int i=0; i<messagesPerRequest; i++) {
            kafkaProducer.sendMessage(message + i);
            latch.countDown();
        }
        latch.await(10, TimeUnit.SECONDS);
        logger.info("All messages received");

        return ResponseEntity.ok("Successfully running!");
    }
}
