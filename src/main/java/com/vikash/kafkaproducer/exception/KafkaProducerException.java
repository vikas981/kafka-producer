package com.vikash.kafkaproducer.exception;

public class KafkaProducerException extends RuntimeException{
    public KafkaProducerException(String message) {
        super(message);
    }
}
