package com.cstor.kafka;

import com.cstor.kafka.bean.Person;
import com.cstor.kafka.client.ConsumerObject;
import com.cstor.kafka.client.ConsumerString;
import com.cstor.kafka.client.ProducerObject;
import com.cstor.kafka.client.ProducerString;
import org.junit.Test;

/**
 * Created by fengwei on 17/5/15.
 */
public class ClientTest {

    @Test
    public void test_produce_str() {
        ProducerString producer = new ProducerString("test");
        producer.send();
    }

    @Test
    public void test_consumer_str() {
        ConsumerString consumer = new ConsumerString(new String[]{"test"});
        consumer.consumerData();
    }

    @Test
    public void test_produce_obj() throws InterruptedException {
        ProducerObject<Person> producerObject = new ProducerObject<Person>("test");
        producerObject.sendBean();
    }

    @Test
    public void test_consume_obj() {
        ConsumerObject<Person> consumerObject = new ConsumerObject<Person>(new String[]{"test"});
        consumerObject.consumeBean();
    }
}
