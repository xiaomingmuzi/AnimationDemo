package com.lixm.animationdemo.interfaces;

/**
 * Describe:
 * <p>
 * Author: Lixm
 * Date: 2018/9/3
 */
public class InterfaceCaseDemo02 {

    public static void main(String args[]){
        Computer.plugin(new Flash());
        Computer.plugin(new Print());
    }

}
