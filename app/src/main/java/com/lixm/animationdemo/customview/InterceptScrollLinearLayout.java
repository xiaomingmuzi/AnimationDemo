package com.lixm.animationdemo.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * @author Lixm
 * @date 2017/11/30
 * @detail
 */

public class InterceptScrollLinearLayout extends LinearLayout {
    public InterceptScrollLinearLayout(Context context) {
        super(context);
    }

    public InterceptScrollLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}
