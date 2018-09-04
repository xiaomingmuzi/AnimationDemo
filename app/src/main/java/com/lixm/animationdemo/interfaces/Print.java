package com.lixm.animationdemo.interfaces;

/**
 * Describe:
 * <p>
 * Author: Lixm
 * Date: 2018/9/3
 */
public class Print implements USB {
    @Override
    public void start() {
        System.out.println("打印机开始工作。");
    }

    @Override
    public void stop() {
        System.out.println("打印机停止工作。");
    }
}
