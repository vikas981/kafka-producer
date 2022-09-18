package com.vikash.kafkaproducer.service;

public interface KafkaProducer<K,V> {
    void send(String topicName,K key,V value);
    void close();
}
