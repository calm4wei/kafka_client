package com.cstor.kafka.client;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConnector;
import kafka.consumer.KafkaStream;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConsumerTest {

    public static void main(String[] args) throws Exception {
        KafkaConsumer<String, String> consumer = KafkaUtil.getConsumer();
        consumer.subscribe(Arrays.asList("test2"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("fetched from partition " + record.partition() + " ,topic=" + record.topic() +
                        " , key=" + record.key() + ", offset: " + record.offset() + ", message: " + record.value());

            }
        }
    }
}
