package com.lixm.animationdemo.interfaces;

/**
 * Describe:
 * <p>
 * Author: Lixm
 * Date: 2018/8/31
 */
public abstract class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void say() {//人说话是一个具体的功能
        System.out.println(getContent());
    }

    public abstract String getContent();//说话的内容由子类决定
}
