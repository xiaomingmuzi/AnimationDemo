package com.lixm.animationdemo.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.lixm.animationdemo.R;

import org.xutils.common.util.LogUtil;

/**
 * @author Lixm
 * @date 2017/9/30
 * @detail
 */

public class FixureProgressBar extends ProgressBar {

    private Context mContext;
    private RectF rectF = new RectF(0, 0, 0, 0);
    private Paint mPaint;
    private int fixurePoi;
    private int fixureColor;

    private int defaultFixureColor = Color.parseColor("#fd8957");
    private int defaultFixurePoi = 0;

    public FixureProgressBar(Context context) {
        this(context, null);
        this.mContext = context;
    }

    public FixureProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FixureProgressBar,
                0, 0);

        fixurePoi = array.getInteger(R.styleable.FixureProgressBar_progress_fixure_pb, defaultFixurePoi);
        fixureColor = array.getColor(R.styleable.FixureProgressBar_progress_fixure_color_pb, defaultFixureColor);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
        mPaint.setColor(fixureColor);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LogUtil.w("---getMax：" + getMax() + "==fixurePoi：" + fixurePoi+"===getWidth()："+getWidth());
        if (fixurePoi > 0 && fixurePoi < getMax()) {
            rectF.left = getWidth() / getMax() * fixurePoi + getPaddingLeft();
            rectF.top = getPaddingTop();
            rectF.right = rectF.left + 10;
            rectF.bottom = getHeight() - getPaddingBottom();
            canvas.drawRect(rectF, mPaint);
        }
    }

    public void setFixurePoi(int fixurePoi) {
        this.fixurePoi = fixurePoi;
    }
}
