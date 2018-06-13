package com.lixm.animationdemo.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lixm.animationdemo.R;

/**
 * @author Lixm
 * @date 2018/6/13 17:16
 * @detail
 */

public class CircularPercentagView extends View {

    private Context mContext;
    private Paint mPaint,mCirclePaint,mTxtPaint;
    private int width,height;

    public CircularPercentagView(Context context) {
        super(context);
        init(context);
    }

    public CircularPercentagView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        mContext=context;
        mPaint=new Paint();
        mPaint.setAntiAlias(true);

        mCirclePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(context.getResources().getColor(R.color.bank_choose));
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(5);

        mTxtPaint=new Paint();
        mTxtPaint.setAntiAlias(true);
        mTxtPaint.setTextSize(32);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width=getMeasuredWidth();
        height=getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.WHITE);
        canvas.drawCircle(width/2,height/2,width/2,mPaint);

        canvas.drawArc(10,10,width-10,height-10,-90,135,false,mCirclePaint);
//        canvas.drawCircle(width/2,height/2,width/2-10,mCirclePaint);
        double d=135*100/360;
        canvas.drawText((d+"%"),);

    }
}
