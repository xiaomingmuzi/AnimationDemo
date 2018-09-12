package com.lixm.animationdemo.other;

/**
 * Describe:
 * <p>
 * Author: Lixm
 * Date: 2018/9/3
 */
public class Factory {//定义工厂类
    public static Fruit getInstance(String className){//注意这里的方法是static修饰的，因为在主方法中是Factory调用
        Fruit f=null;
        if ("apple".equals(className)){//判断是否要的是苹果的子类
            f=new Apple();
        }
        if ("orange".equals(className)){//判断是否要的是橘子的子类
            f=new Orange();
        }
        return f;
    }
}
