package com.lixm.animationdemo.Other;

/**
 * Describe:
 * <p>
 * Author: Lixm
 * Date: 2018/9/3
 */
public class X extends C {//继承抽象类

    @Override
    public void printA() {
        System.out.println("HELLO-->A");
    }

    class Y implements B {//定义内部类实现内容接口

        @Override
        public void printB() {
            System.out.println("HELLO-->B");
        }
    }
}
