package com.lixm.animationdemo.other;

/**
 * Describe:
 * <p>
 * Author: Lixm
 * Date: 2018/9/3
 */
public class Proxy implements Network {//代理类

    private Network network;//代理对象

    public Proxy(Network network) {//初始化，把真实对象传给代理对象，向上转型操作
        this.network = network;
    }

    public void check(){
        System.out.println("检查用户是否合法。");
    }

    @Override
    public void browse() {
        this.check();
        this.network.browse();//调用真实的主题操作，这里调用的是真实类里的对象
    }
}
