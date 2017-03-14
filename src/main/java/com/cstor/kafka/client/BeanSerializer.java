package com.cstor.kafka.client;

import com.cstor.kafka.bean.Person;
import kafka.serializer.Encoder;

/**
 * Created on 2017/3/14
 *
 * @author feng.wei
 */
public class BeanSerializer implements Encoder<Person> {
    public byte[] toBytes(Person person) {
        System.out.println("encoder ---> " + person);
        return new byte[0];
    }
}
