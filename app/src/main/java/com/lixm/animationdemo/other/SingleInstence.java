package com.lixm.animationdemo.other;

/**
 * Describe:
 * <p>
 * Author: Lixm
 * Date: 2018/9/4
 */
public class SingleInstence {

//    private volatile static SingleInstence INSTENCE; //volatile ：禁止指令重排序优化
//
//    private SingleInstence() {
//
//    }
//
//    public static SingleInstence getInstence() {
//        if (INSTENCE == null) {
//            synchronized (SingleInstence.class) {//双重校验锁
//                if (INSTENCE == null)
//                    INSTENCE = new SingleInstence();
//            }
//        }
//        return INSTENCE;
//    }

    /** 静态内部类方法 **/
    //这样就避免了静态实例在 Singleton 类加载的时候就创建对象，并且由于静态内部类只会被加载一次，所以这种写法也是线程安全的。
    private static class Holder {
        private static SingleInstence INSTANCE = new SingleInstence();
    }

    public SingleInstence() {
    }

    public static SingleInstence getInstance(){
        return Holder.INSTANCE;
    }

    /** 枚举方法实现单例 **/
    public enum EasySingleton{
        INSTANCE;
    }
}
