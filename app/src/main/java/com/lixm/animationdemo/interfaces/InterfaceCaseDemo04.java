package com.lixm.animationdemo.interfaces;

/**
 * Describe:
 * <p>
 * Author: Lixm
 * Date: 2018/9/3
 */
public class InterfaceCaseDemo04 {
    public static void main(String args[]){
        Fruit f=Factory.getInstance("apple");//实例化接口
        f.eat();
    }
}
