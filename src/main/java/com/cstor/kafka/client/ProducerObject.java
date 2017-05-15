package com.cstor.kafka.client;

import com.cstor.kafka.bean.Person;
import com.cstor.kafka.util.DateUtils;
import org.apache.kafka.clients.producer.*;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.util.Properties;

/**
 * Created by Administrator on 2016/4/25.
 */
public class ProducerObject<T> {

    private KafkaProducer<String, T> producer = null;
    private String topic;

    public ProducerObject(String topic) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "master01:9092");
        props.put("acks", "1");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "com.cstor.kafka.client.BeanSerializer");
        producer = new KafkaProducer<String, T>(props);
        this.topic = topic;
    }

    public void sendBean() throws InterruptedException {
        Person p = new Person();
        p.setName("Tom");
        p.setAge(20);
        int i = 0;
        while (true) {
            final ProducerRecord<String, T> record = new ProducerRecord<String, T>(this.topic, 0,
                    System.currentTimeMillis(), "key-" + i, (T) p);
            producer.send(record, new Callback() {
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    System.out.println("time=" + DateUtils.format(record.timestamp()) + " ,partition= " + metadata.partition() + ", offset: " + metadata.offset()
                            + " ,key=" + record.key() + " ,value=" + record.value());
                }
            });
            i++;
            Thread.sleep(2000);
        }

    }
}
