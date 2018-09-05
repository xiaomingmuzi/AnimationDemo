package com.lixm.animationdemo.Other;

/**
 * Describe:
 * <p>
 * Author: Lixm
 * Date: 2018/9/3
 */
public class InnerExtDemo01 {
    public static void main(String args[]){
        C.B b=new X().new Y();//参考前面内部类的知识，实现内部类实例的方法：外部类.内部类  内部类对象=外部类对象.new 内部类
        b.printB();
    }
}
