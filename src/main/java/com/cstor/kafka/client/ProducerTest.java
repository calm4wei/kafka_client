package com.cstor.kafka.client;

import com.cstor.kafka.bean.Person;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * Created by Administrator on 2016/4/25.
 */
public class ProducerTest {

    public static void main(String[] args) throws Exception {
        Producer<String, String> producer = KafkaUtil.getProducer();
        int i = 1;
        while (true) {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("test", 1, "this is message" + i, "value" + i);
            producer.send(record, new Callback() {
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    if (e != null)
                        e.printStackTrace();
                    System.out.println("message send to partition " + metadata.partition() + ", offset: " + metadata.offset());
                }
            });
            i++;
            Thread.sleep(1000);
        }
    }

    public static void sendBean() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "datacube201:9092");
        props.put("acks", "1");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<Person, Person> producer = new KafkaProducer<Person, Person>(props);

        Person p = new Person();
        p.setName("Tom");
        p.setAge(20);

        ProducerRecord<Person, Person> record = new ProducerRecord<Person, Person>("test2", p);
        producer.send(record, new Callback() {
            public void onCompletion(RecordMetadata metadata, Exception e) {
                if (e != null)
                    e.printStackTrace();
                System.out.println("message send to partition " + metadata.partition() + ", offset: " + metadata.offset());
            }
        });

    }
}
