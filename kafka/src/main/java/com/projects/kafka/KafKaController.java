package com.projects.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafKaController {
    @Autowired
    private KafkaConsumerService consumerService;

    @GetMapping
    public String createConsumer() {
        consumerService.createDynamicConsumer();
        return "Consumer created!";
    }
}
