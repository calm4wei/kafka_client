package com.cstor.kafka.client;

import com.cstor.kafka.bean.Person;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class ConsumerObject<V> {

    private KafkaConsumer<String, V> consumer = null;
    private String[] topics;

    public ConsumerObject(String[] topics) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "master01:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer-group-string");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "com.cstor.kafka.client.BeanDeSerializer");
        consumer = new KafkaConsumer<String, V>(props);
        this.topics = topics;
    }

    public void consumeBean() {
        consumer.subscribe(Arrays.asList(this.topics));
        while (true) {
            ConsumerRecords<String, V> records = consumer.poll(1000);
            for (ConsumerRecord<String, V> record : records) {
                System.out.println(" ,partition " + record.partition() + " , topic=" + record.topic() +
                        ", offset: " + record.offset() +
                        " , key=" + record.key() + ", value: " + record.value());

            }
        }
    }

}
