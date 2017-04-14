package com.lixm.animationdemo.animator;

import android.animation.TimeInterpolator;

/**
 * @author Lixm
 * @date 2017/4/14
 * @detail 使用正弦函数来实现先减速后加速的功能的
 */

public class DeToAcInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        float result;
        if (input<=0.5){
            result=(float)(Math.sin(Math.PI*input))/2;
        }
        else result=(float)(2-Math.sin(Math.PI*input))/2;
        return result;
    }
}
