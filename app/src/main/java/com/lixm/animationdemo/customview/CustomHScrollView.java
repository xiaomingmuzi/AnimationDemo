package com.lixm.animationdemo.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lixm
 * @date 2017/11/30
 * @detail
 */

public class CustomHScrollView extends HorizontalScrollView {

    ScrollViewObserver mScrollViewObserver = new ScrollViewObserver();

    public CustomHScrollView(Context context) {
        super(context);
    }

    public CustomHScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        //滚动时通知观察者
        if (mScrollViewObserver!=null){
            mScrollViewObserver.NotifyOnScrollChanged(l,t,oldl,oldt);
        }
        super.onScrollChanged(l, t, oldl, oldt);
    }

    /**
     * 添加滚动事件监听
     * @param listener
     */
    public void AddOnScrollChangedListener(OnScrollChangedListener listener){
        mScrollViewObserver.AddOnScrollChangedListener(listener);
    }

    /**
     * 移除滚动事件监听
     * @param listener
     */
    public void RemoveOnScrollChangedListener(OnScrollChangedListener listener){
        mScrollViewObserver.RemoveOnScrollChangedListener(listener);
    }

    public static interface OnScrollChangedListener {
        public void onScrollChanged(int l, int t, int oldl, int oldt);
    }

    /**
     * 添加观察者
     */
    public static class ScrollViewObserver {
        List<OnScrollChangedListener> mChangedListeners;

        public ScrollViewObserver() {
            mChangedListeners = new ArrayList<>();
        }

        /**
         * 添加滚动事件监听
         *
         * @param listener
         */
        public void AddOnScrollChangedListener(OnScrollChangedListener listener) {
            mChangedListeners.add(listener);
        }

        /**
         * 移除滚动事件监听
         *
         * @param listener
         */
        public void RemoveOnScrollChangedListener(OnScrollChangedListener listener) {
            mChangedListeners.remove(listener);
        }

        public void NotifyOnScrollChanged(int l, int t, int oldl, int oldt) {
            if (mChangedListeners == null || mChangedListeners.size() == 0)
                return;
            for (int i = 0; i < mChangedListeners.size(); i++) {
                if (mChangedListeners.get(i) != null)
                    mChangedListeners.get(i).onScrollChanged(l, t, oldl, oldt);
            }
        }


    }
}
