package com.lixm.animationdemo.interfaces;

/**
 * Describe:
 * <p>
 * Author: Lixm
 * Date: 2018/8/31
 */
public class Student extends Person {

    private float score;

    public Student(String name, int age,float score) {
        super(name, age);//调用父类中的构造方法
        this.score=score;
    }

    @Override
    public String getContent() {
        return "学生信息 --> 姓名："+super.getName()+
                "; 年龄："+super.getAge()+
                "; 成绩："+this.score;
    }
}
