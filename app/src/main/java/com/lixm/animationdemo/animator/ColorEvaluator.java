package com.lixm.animationdemo.animator;

import android.animation.TypeEvaluator;

/**
 * @author Lixm
 * @date 2017/4/13
 * @detail
 */

public class ColorEvaluator implements TypeEvaluator {
    private int mCurrentRed = -1;
    private int mCurrentGreen = -1;
    private int mCurrentBlue = -1;

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {

        String startColor = (String) startValue;
        String endColor = (String) endValue;
        int startRed = getColor(startColor, 1);
        int startGreen = getColor(startColor, 3);
        int startBlue = getColor(startColor, 5);

        int endRed = getColor(endColor, 1);
        int endGreen = getColor(endColor, 3);
        int endBlue = getColor(endColor, 5);

        //初始化颜色的值
        if (mCurrentRed == -1) {
            mCurrentRed = startRed;
        }
        if (mCurrentGreen == -1) {
            mCurrentGreen = startGreen;
        }
        if (mCurrentBlue == -1) {
            mCurrentBlue = startBlue;
        }

        //计算初始颜色和结束颜色之间的差值
        int redDiff = Math.abs(startRed - endRed);
        int greenDiff = Math.abs(startGreen - endGreen);
        int blueDiff = Math.abs(startBlue - endBlue);

        int colorDiff = redDiff + greenDiff + blueDiff;
        if (mCurrentRed != endRed) {
            mCurrentRed = getCurrentColor(startRed, endRed, colorDiff, 0, fraction);
        } else if (mCurrentGreen != endGreen) {
            mCurrentGreen = getCurrentColor(startGreen, endGreen, colorDiff, redDiff, fraction);
        } else if (mCurrentBlue != endBlue) {
            mCurrentBlue = getCurrentColor(startBlue, endBlue, colorDiff, redDiff + greenDiff, fraction);
        }
        //将计算出的当前颜色的值组装返回
        String currentColor = "#" + getHexString(mCurrentRed) + getHexString(mCurrentGreen) + getHexString(mCurrentBlue);
        return currentColor;
    }

    private String getHexString(int value) {
        String hexString = Integer.toHexString(value);
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        return hexString;
    }

    private int getCurrentColor(int startClolr, int endColor, int colorDiff, int offset, float fraction) {
        int currentColor;
        if (startClolr > endColor) {
            currentColor = (int) (startClolr - (fraction * colorDiff - offset));
            if (currentColor < endColor) {
                currentColor = endColor;
            }
        } else {
            currentColor = (int) (startClolr + (fraction * colorDiff - offset));
            if (currentColor > endColor) {
                currentColor = endColor;
            }
        }
        return currentColor;
    }

    /**
     * 根据颜色取出不同的色值
     *
     * @param color
     * @param start
     * @return
     */
    private int getColor(String color, int start) {
        return Integer.parseInt(color.substring(start, start + 2), 16);
    }
}
