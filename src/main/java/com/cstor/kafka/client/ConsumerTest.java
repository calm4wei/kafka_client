package com.cstor.kafka.client;

import com.cstor.kafka.bean.Person;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class ConsumerTest {

    public static void main(String[] args) throws Exception {
        consumeObj();
//        consumeStr();
    }

    public static void consumeStr() {
        KafkaConsumer<String, String> consumer = KafkaUtil.getConsumer();
        consumer.subscribe(Arrays.asList("test"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("fetched from partition " + record.partition() + " ,topic=" + record.topic() +
                        " , key=" + record.key() + ", offset: " + record.offset() + ", message: " + record.value());

            }
        }
    }

    public static void consumeObj() {

        Properties props = new Properties();
        props.put("bootstrap.servers", "datacube154:9092");
        props.put("group.id", "consumer-group-obj");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "com.cstor.kafka.client.BeanDeSerializer");
        props.put("value.deserializer", "com.cstor.kafka.client.BeanDeSerializer");
        KafkaConsumer<Person, Person> consumer = new KafkaConsumer<Person, Person>(props);
        consumer.subscribe(Arrays.asList("test"));
        while (true) {
            ConsumerRecords<Person, Person> records = consumer.poll(1000);
            System.out.println("count=" + records.count());
            for (ConsumerRecord<Person, Person> record : records) {
                System.out.println("fetched from partition " + record.partition() + " ,topic=" + record.topic() +
                        " , key=" + record.key() + ", offset: " + record.offset() + ", message: " + record.value());

            }
        }
    }
}
