package com.vikash.kafkaproducer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vikash.kafkaproducer.config.data.KafkaConfigData;
import com.vikash.kafkaproducer.model.Student;
import com.vikash.kafkaproducer.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaProducerController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private KafkaConfigData kafkaConfigData;

    @Autowired
    private ObjectMapper mapper;

    @PostMapping("/produce")
    public ResponseEntity<String> produceMessage(@RequestBody Student student) throws JsonProcessingException {
        kafkaProducer.send(kafkaConfigData.getTopicName(),"student",mapper.writeValueAsString(student));
        return new ResponseEntity<>("Message Produced", HttpStatus.OK);
    }
}
