package com.lixm.animationdemo.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lixm.animationdemo.R;

/**
 * Describe:可以设置倾斜的字的TextView
 * <p>
 * Author: Lixm
 * Date: 2018/10/17
 */
public class MySkewWord extends TextView {

    private Context context;
    private float mDegrees = 10;
    private int mCornerRadius = 0; //圆角半径
    private int mCornerPosition = 0;//圆角位置
    private int mFillColor = 0;//填充颜色
    private static final int TOP_LEFT = 1;
    private static final int TOP_RIGHT = 2;
    private static final int BOTTOM_RIGHT = 4;
    private static final int BOTTOM_LEFT = 8;
    private String content;

    /**
     * 普通shape样式
     */
    private GradientDrawable normalGradientDrawable = new GradientDrawable();

    public MySkewWord(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MySkewWord);
        mDegrees = array.getFloat(R.styleable.MySkewWord_msw_degrees, 0);
        mFillColor = array.getColor(R.styleable.MySkewWord_msw_fillColor, 0xffffffff);
        mCornerRadius = array.getDimensionPixelSize(R.styleable.MySkewWord_msw_cornerRadius, 0);
        mCornerPosition = array.getInt(R.styleable.MySkewWord_msw_cornerPosition, -1);
        array.recycle();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        normalGradientDrawable.setColor(mFillColor);
        if (mCornerPosition == -1) {
            normalGradientDrawable.setCornerRadius(mCornerRadius);
        } else {
            normalGradientDrawable.setCornerRadii(getCornerRadiusByPosition());
        }
        setBackground(normalGradientDrawable);
    }

    float x, y, textWidth, textHeight;

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //获取文字宽度
        textWidth = getPaint().measureText(getText().toString());
        //获取文字高度
        Paint.FontMetrics fontMetrics = getPaint().getFontMetrics();
        //单行高度
        float singeLineHeight = (float) Math.ceil(fontMetrics.descent - fontMetrics.ascent);
        //总的行间距
        int totalLineSpaceHeight = (int) ((getLineCount() - 1) * getLineSpacingExtra());
        textHeight = singeLineHeight * getLineCount() + totalLineSpaceHeight;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate((getWidth() - textWidth) / 2, (getHeight() - textHeight) / 2);
        canvas.rotate(mDegrees);
        super.onDraw(canvas);

    }

    private float[] getCornerRadiusByPosition() {
        float[] result = new float[]{0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f};
        float cornerRadius = mCornerRadius;
        if (containsFlag(mCornerPosition, TOP_LEFT)) {
            result[0] = cornerRadius;
            result[1] = cornerRadius;
        }
        if (containsFlag(mCornerPosition, TOP_RIGHT)) {
            result[2] = cornerRadius;
            result[3] = cornerRadius;
        }
        if (containsFlag(mCornerPosition, BOTTOM_RIGHT)) {
            result[4] = cornerRadius;
            result[5] = cornerRadius;
        }
        if (containsFlag(mCornerPosition, BOTTOM_LEFT)) {
            result[6] = cornerRadius;
            result[7] = cornerRadius;
        }
        return result;
    }

    private boolean containsFlag(int flagSet, int flag) {
        return (flagSet | flag) == flagSet;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
