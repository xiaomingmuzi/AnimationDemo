package com.lixm.animationdemo.Other;

/**
 * Describe:
 * <p>
 * Author: Lixm
 * Date: 2018/9/3
 */
public class ProxyDemo  {
    public static void main(String args[]){
        Network net=null;
        /**
         * 指定代理操作，这里两次向上转型操作，第一次向上是实例化代理类，
         * 第二次向上转型是括号中，把真实类对象传入，以便在代理类中调用真实类中的方法。
         */
        net=new Proxy(new Real());
        net.browse();//客户只关心上网浏览一个操作。
    }
}
