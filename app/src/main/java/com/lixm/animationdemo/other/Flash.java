package com.lixm.animationdemo.other;

/**
 * Describe:
 * <p>
 * Author: Lixm
 * Date: 2018/9/3
 */
public class Flash implements USB {

    @Override
    public void start() {
        System.out.println("U盘开始工作。");
    }

    @Override
    public void stop() {
        System.out.println("U盘停止工作。");
    }
}
