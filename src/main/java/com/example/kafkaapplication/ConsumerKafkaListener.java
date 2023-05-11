//package com.example.kafkaapplication;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.kafka.annotation.KafkaListener;
//
//@Component
//public class ConsumerKafkaListener {
//
//    private static final Logger logger = LoggerFactory.getLogger(ConsumerKafkaListener.class);
//
//    @KafkaListener(
//            topics = "newTopic", groupId = "kafkaGroup"
//    )
//   public void Listener(String data){
//        logger.info(String.format("Listener received the data --> %s", data));
//    }
//}
