package com.lixm.animationdemo.customview;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.lixm.animationdemo.R;

/**
 * @author Lixm
 * @date 2018/4/11
 * @detail 连接点View
 */

public class NodeView extends ImageView {

    private int number;

    public NodeView(Context context) {
        super(context);
        setImageResource(R.mipmap.gesture_normal);
    }

    public NodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setImageResource(R.mipmap.gesture_normal);

    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
