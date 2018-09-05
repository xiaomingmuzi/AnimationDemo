package com.lixm.animationdemo.Other;

/**
 * Describe:
 * <p>
 * Author: Lixm
 * Date: 2018/8/31
 */
public class Worker extends Person {

    private float salary;

    public Worker(String name, int age, float salary) {
        super(name, age);//调用父类中的构造方法
        this.salary = salary;
    }

    @Override
    public String getContent() {
        return "工人信息 --> 姓名：" + super.getName() +
                "；年龄：" + super.getAge() +
                "；工资：" + this.salary;
    }
}
