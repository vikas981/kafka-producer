package com.vikash.kafkaproducer.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vikash.kafkaproducer.service.KafkaProducer;
import com.vikash.kafkaproducer.exception.KafkaProducerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

@Service
public class KafkaProducerImpl<K,V> implements KafkaProducer<K,V> {

    @Autowired
    private KafkaTemplate<K,V> kafkaTemplate;



    @Override
    public void send(String topicName,K key,V value) {
        try{
            kafkaTemplate.send(topicName,key,value);
        }catch (Exception e){
            e.printStackTrace();
            throw new KafkaProducerException("Something Went Wrong!");
        }

    }

    @PreDestroy
    @Override
    public void close() {
      if(kafkaTemplate !=null){
          kafkaTemplate.destroy();
      }
    }
}
