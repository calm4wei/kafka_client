package com.cstor.kafka.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

/**
 * Created on 2017/3/14
 *
 * @author feng.wei
 */
public class BeanSerializer implements org.apache.kafka.common.serialization.Serializer {

    public void configure(Map configs, boolean isKey) {

    }

    public byte[] serialize(String topic, Object data) {
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(data);
            System.out.println("serialize=" + data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                baos.close();
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return baos.toByteArray();

    }

    public void close() {
    }

}
