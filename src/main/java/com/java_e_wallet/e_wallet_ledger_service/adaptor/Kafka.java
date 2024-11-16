package com.java_e_wallet.e_wallet_ledger_service.adaptor;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java_e_wallet.e_wallet_ledger_service.config.Config;
import com.java_e_wallet.e_wallet_ledger_service.model.TransactionSummary;

import io.confluent.kafka.serializers.KafkaJsonDeserializer;
import io.confluent.kafka.serializers.KafkaJsonDeserializerConfig;

@Component
public class Kafka {
    private static Consumer<String, JsonNode> consumer;
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void Init() {
        Properties properties = new Properties();

        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Config.getKafkaAddress());
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaJsonDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "ledger_consumer");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(KafkaJsonDeserializerConfig.JSON_VALUE_TYPE, TransactionSummary.class.getName());

        consumer = new KafkaConsumer<>(properties);
    }


    public static void ListenTransactions() {
        if (consumer == null) {
            System.err.println("kafka not initialized");
        }

        consumer.subscribe(Arrays.asList(Config.getKafkaTransactionTopicName()));

        try {
            while (true) {
                ConsumerRecords<String, JsonNode> records = consumer.poll(Duration.ofSeconds(5));

                records.forEach(record -> {
                    try {
                        TransactionSummary summary = mapper.convertValue(record.value(), new TypeReference<TransactionSummary>() {});

                        System.out.println("test kafka");
                        System.out.println(summary.getRequestId());
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }

}
