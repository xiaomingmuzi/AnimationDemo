package com.lixm.animationdemo.Other;

/**
 * Describe:
 * <p>
 * Author: Lixm
 * Date: 2018/9/3
 */
public class WindowImpl extends WindowAdapter {
    @Override
    public void open() {
        super.open();
        System.out.println("窗口打卡。");
    }

    @Override
    public void close() {
        super.close();
        System.out.println("窗口关闭。");
    }
}
