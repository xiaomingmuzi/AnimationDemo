package com.lixm.animationdemo.other;

/**
 * Describe:
 * <p>
 * Author: Lixm
 * Date: 2018/9/5
 */
public class MathPowDemo {

    private static double power(double base, int exponent) {
        //以为除了0以外，任何数值的0次方都为1，所以我们默认为1.0；
        //0 的 0 次方，在数学上是没有意义的，为了贴切，我们也默认为1.0
        double result = 1.0;
        if (base == 0)
            return 0.0;
        //处理负数次方的情况
        boolean isNegetive = false;
        if (exponent < 0) {
            isNegetive = true;
            exponent = -exponent;
        }
        result=getTheResult(base,exponent);
        if (isNegetive) {
            return 1 / result;
        } else
            return result;
    }

    private static double getTheResult(double base, int exponent) {
        //如果指数为0，返回1
        if (exponent == 0)
            return 1;
        //指数为1，返回底数
        if (exponent == 1)
            return base;
        //递归求一半的值
        double result = getTheResult(base, exponent >> 1);// >> 1 带符号右移一位
        //求最终值，如果是奇数，还要乘一次底数
        result *= result;
        if ((exponent & 0x1) == 1) {//& 0x1的作用是只保留最后一位的值
            result *= base;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(power(2, 2));
        System.out.println(power(2, 4));
        System.out.println(power(3, 1));
        System.out.println(power(3, 0));
    }

}
