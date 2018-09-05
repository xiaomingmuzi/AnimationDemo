package com.lixm.animationdemo.Other;

/**
 * Describe:
 * <p>
 * Author: Lixm
 * Date: 2018/8/31
 */
public class AbstractCaseDemo02 {
    public static void main(String args[]){
        Person per1=null;//声明Person对象
        Person per2=null;//声明Person对象
        per1=new Student("张三",20,99.0f);//学生这个人
        per2=new Worker("李四",30,3000.0f);//工人是一个人
        per1.say();
        per2.say();
    }
}
