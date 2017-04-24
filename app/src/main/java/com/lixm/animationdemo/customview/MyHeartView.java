package com.lixm.animationdemo.customview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.lixm.animationdemo.R;

/**
 * @author Lixm
 * @date 2017/4/24
 * @detail
 */

public class MyHeartView extends RelativeLayout {
    private String TAG=getClass().getName();
    private Context mContext;
    private Drawable[] mDrawables;
    private Drawable mDrawabeBlue,mDrawablePink,mDrawableGreen;

    private LayoutParams mDrawableLp;


    public MyHeartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
        mDrawables=new Drawable[3];
        mDrawabeBlue=getResources().getDrawable(R.mipmap.icon_copper1);
        mDrawablePink=getResources().getDrawable(R.mipmap.icon_copper2);
        mDrawableGreen=getResources().getDrawable(R.mipmap.icon_copper3);
        mDrawables[0]=mDrawabeBlue;
        mDrawables[1]=mDrawablePink;
        mDrawables[2]=mDrawableGreen;
        mDrawableLp=new LayoutParams(40,40);
        mDrawableLp.addRule(CENTER_HORIZONTAL,TRUE);
        mDrawableLp.addRule(ALIGN_PARENT_BOTTOM,TRUE);
    }
}
