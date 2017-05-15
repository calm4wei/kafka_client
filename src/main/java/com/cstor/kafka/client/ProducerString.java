package com.cstor.kafka.client;

import com.cstor.kafka.bean.Person;
import com.cstor.kafka.util.DateUtils;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * Created by fengwei on 17/5/15.
 */
public class ProducerString {

    private KafkaProducer<String, String> producer = null;
    private String topic;

    public ProducerString(String topic) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "master01:9092");
        props.put("acks", "1");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<String, String>(props);
        this.topic = topic;
    }

    public void send() {
        int i = 0;
        while (true) {
            final ProducerRecord<String, String> record = new ProducerRecord<String, String>(
                    this.topic, 0, System.currentTimeMillis(), "key-" + i, "value-" + i);
            producer.send(record, new Callback() {
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    System.out.println("time=" + DateUtils.format(record.timestamp()) + " ,key=" + record.key() + " ,value=" + record.value() +
                            " ,partition " + metadata.partition() + ", offset: " + metadata.offset());
                }
            });
            i++;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
