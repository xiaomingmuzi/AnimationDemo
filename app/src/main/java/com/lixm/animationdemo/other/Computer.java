package com.lixm.animationdemo.other;

/**
 * Describe:
 * <p>
 * Author: Lixm
 * Date: 2018/8/31
 */
public class Computer {
    //电脑上可以插入USB设备，向上转型，这里就相当于是个接口，
    // 只有符合这个接口的标准的类的对象（即只有继承这个接口的类的对象），才能被这个方法调用
    public static void plugin(USB usb){
        usb.start();
        System.out.println("=======USB 设备工作=======");
        usb.stop();
    }
}
