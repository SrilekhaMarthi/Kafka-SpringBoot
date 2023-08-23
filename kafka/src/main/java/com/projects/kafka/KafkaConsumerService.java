package com.projects.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class KafkaConsumerService {
    @Autowired
    private ConsumerFactory<String, String> consumerFactory;

    private final List<Consumer<String, String>> dynamicConsumers = new ArrayList<>();

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);


    private Consumer<String, String> createConsumer() {
        return consumerFactory.createConsumer();

    }

    @KafkaListener(id = "myGroup", topics = "kafka-topic")
    public void listen(String message) {
        if (message.contains("important")) {
           System.out.println(message);
        }
    }

    public void createDynamicConsumer() {
        Consumer<String, String> consumer = createConsumer();
        consumer.subscribe(Collections.singleton("kafka-topic"));
        System.out.println("Consumer created: " + consumer);
        dynamicConsumers.add(consumer);
        System.out.println("dynamicConsumers:"+dynamicConsumers);

        Thread consumerThread = new Thread(() -> {
            System.out.println("Another thread created");
            try {
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                    for (ConsumerRecord<String, String> record : records) {
                       // if ((record.value()).contains("important")){
                            System.out.println("Received message: " + record.value());
                       // }
                    }
                }
            } finally {
                consumer.close();
                System.out.println("consumer closed: " + consumer);
                dynamicConsumers.remove(consumer);
            }
        });

        consumerThread.start();
    }

}
