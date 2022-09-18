package com.vikash.kafkaproducer.config;

import com.vikash.kafkaproducer.config.data.KafkaConfigData;
import com.vikash.kafkaproducer.config.data.KafkaProducerConfigData;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class KafkaConfig {

    @Autowired
    private KafkaProducerConfigData producerConfigData;

    @Autowired
    private KafkaConfigData kafkaConfigData;


    @Bean
    public KafkaTemplate<?,?> kafkaTemplate(){
        KafkaTemplate<?,?> kafkaTemplate = new KafkaTemplate<>(producerFactory());
        return kafkaTemplate;
    }

    @Bean
    public ProducerFactory<String,Object> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public Map<String,Object> producerConfig(){
        Map<String,Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaConfigData.getBootStrapServers());
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,producerConfigData.getKeySerializerClass());
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,producerConfigData.getValueSerializerClass());
        configProps.put(ProducerConfig.LINGER_MS_CONFIG,producerConfigData.getLingerMS());
        configProps.put(ProducerConfig.ACKS_CONFIG,producerConfigData.getAcks());
        configProps.put(ProducerConfig.BATCH_SIZE_CONFIG,producerConfigData.getBatchSize()*producerConfigData.getBatchSizeBoostFactor());
        configProps.put(ProducerConfig.COMPRESSION_TYPE_CONFIG,producerConfigData.getCompressionType());
        configProps.put(ProducerConfig.RETRIES_CONFIG,producerConfigData.getRetryCount());
        configProps.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG,producerConfigData.getRequestTimeoutMS());
        return configProps;
    }


}
