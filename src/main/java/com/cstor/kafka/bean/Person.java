package com.cstor.kafka.bean;

import java.io.Serializable;

/**
 * Created on 2017/3/14
 *
 * @author feng.wei
 */
public class Person implements Serializable {

    private String name;
    private Integer age;

    public Person() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "name=" + name + " ,age=" + age;
    }
}
