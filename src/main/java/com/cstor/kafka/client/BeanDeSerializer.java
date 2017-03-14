package com.cstor.kafka.client;

import com.cstor.kafka.bean.Person;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

/**
 * Created on 2017/3/14
 *
 * @author feng.wei
 */
public class BeanDeSerializer implements org.apache.kafka.common.serialization.Deserializer<Person> {
    public void configure(Map configs, boolean isKey) {

    }

    public Person deserialize(String topic, byte[] data) {
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        Person obj = null;
        try {
            bais = new ByteArrayInputStream(data);
            ois = new ObjectInputStream(bais);
            obj = (Person) ois.readObject();
            System.out.println("deserialize=" + obj);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                bais.close();
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    public void close() {

    }
}
