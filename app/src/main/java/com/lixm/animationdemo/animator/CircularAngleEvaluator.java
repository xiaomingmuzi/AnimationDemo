package com.lixm.animationdemo.animator;

import android.animation.TypeEvaluator;

import com.lixm.animationdemo.bean.CircularAngleBean;

/**
 * @author Lixm
 * @date 2018/6/14 11:10
 * @detail
 */

public class CircularAngleEvaluator implements TypeEvaluator {

    private String TAG = getClass().getName();

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {

        int startAngle = (int) startValue;
        int endAngle = (int) endValue;

        int angle = (int) (startAngle + (endAngle-startAngle) * fraction);
        CircularAngleBean circularAngleBean = new CircularAngleBean();
        circularAngleBean.setSweepAngle(angle);
        circularAngleBean.setStartAngle(startAngle);
//        LogUtil.i(TAG, "current angle：" + angle + " fraction：" + fraction);
        return circularAngleBean;
    }
}
