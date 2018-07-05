package com.lixm.animationdemo.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.lixm.animationdemo.R;

/**
 * Describe:
 * <p>
 * Author: Lixm
 * Date: 2018/7/5
 */
public class MarqueeView extends HorizontalScrollView implements Runnable {

    private Context context;
    private int width;//控件宽度
    private LinearLayout mainLayout;//跑马灯内容布局
    private int layoutMargin = 20;//内容控件间距
    private int viewWidth;//View总宽度
    private int scrollSpeed = 5;//滚动速度
    private int scrollDirection = LEFT_TO_RIGHT;//滑动方向
    private int currentX;//当前x坐标

    public static final int LEFT_TO_RIGHT = 1;
    public static final int RIGHT_TO_LEFT = 2;

    public MarqueeView(Context context) {
        this(context, null);
    }

    public MarqueeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MarqueeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MarqueeView, defStyleAttr, 0);
        layoutMargin = array.getInteger(R.styleable.MarqueeView_layout_margin, 20);
        scrollSpeed = array.getInteger(R.styleable.MarqueeView_scroll_speed, 5);
        scrollDirection = array.getInteger(R.styleable.MarqueeView_scroll_direction, LEFT_TO_RIGHT);
        array.recycle();
        initView();
    }

    private void initView() {
        mainLayout = new LinearLayout(context);
        mainLayout.setOrientation(LinearLayout.HORIZONTAL);
        this.addView(mainLayout);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measure(widthMeasureSpec, true), measure(heightMeasureSpec, false));
    }

    private int measure(int measureSpec, boolean isWith) {
        int result;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        int padding = isWith ? getPaddingLeft() + getPaddingRight() : getPaddingTop() + getPaddingBottom();
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = isWith ? getSuggestedMinimumWidth() : getSuggestedMinimumHeight();
            result += padding;
            if (mode == MeasureSpec.AT_MOST) {
                if (isWith) {
                    result = Math.max(result, size);
                } else {
                    result = Math.min(result, size);
                }
            }
        }
        return result;
    }

    public void addViewInQueue(View view) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(layoutMargin, 0, 0, 0);
        view.setLayoutParams(params);
        mainLayout.addView(view);
        view.measure(0, 0);//测量View
        viewWidth += view.getMeasuredWidth() + layoutMargin;
    }

    /**
     * 开始滑动，从整个view的宽度位置或者从屏幕开始滑动
     */
    public void startScroll() {
        removeCallbacks(this);
        currentX = scrollDirection == LEFT_TO_RIGHT ? viewWidth : -width;
        post(this);
    }

    /**
     * 停止滚动
     */
    public void stopScroll() {
        removeCallbacks(this);
    }

    /**
     * 设置间距
     *
     * @param startMargin
     */
    public void setLayoutMargin(int startMargin) {
        this.layoutMargin = startMargin;
    }

    /**
     * 设置滚动速度
     *
     * @param scrollSpeed
     */
    public void setScrollSpeed(int scrollSpeed) {
        this.scrollSpeed = scrollSpeed;
    }

    /**
     * 设置滚动方向 默认从左到右
     *
     * @param scrollDirection
     */
    public void setScrollDirection(int scrollDirection) {
        this.scrollDirection = scrollDirection;
    }

    @Override
    public void run() {
        switch (scrollDirection) {
            case LEFT_TO_RIGHT:
                mainLayout.scrollTo(currentX, 0);
                currentX--;
                if (-currentX >= width) {
                    mainLayout.scrollTo(viewWidth, 0);
                    currentX = viewWidth;
                }
                break;
            case RIGHT_TO_LEFT:
                mainLayout.scrollTo(currentX, 0);
                currentX++;
                if (currentX >= viewWidth) {
                    mainLayout.scrollTo(-width, 0);
                    currentX = -width;
                }
                break;
        }
        postDelayed(this, 50 / scrollSpeed);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
