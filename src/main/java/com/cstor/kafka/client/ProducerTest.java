package com.cstor.kafka.client;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

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
}
