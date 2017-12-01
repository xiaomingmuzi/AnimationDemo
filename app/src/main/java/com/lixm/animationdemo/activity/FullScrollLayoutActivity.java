package com.lixm.animationdemo.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.adapter.ListViewAdapter;
import com.lixm.animationdemo.bean.TestData;
import com.lixm.animationdemo.customview.CustomHScrollView;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lixm
 * @date 2017/12/1
 * @detail
 */

public class FullScrollLayoutActivity extends BaseActivity {

    private RelativeLayout mHead;
    private ListView mListView;
    private List<TestData> mDataList;
    private ListViewAdapter mAdapter;
    private int leftPos;//用于记录CustomHScrollView初始位置
    private int topPos;
    private CustomHScrollView mScrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_scroll);
        LogUtil.w("FullScrollLayoutActivity");
        initView();
        initData();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    private void initView() {
        mListView = findViewById(R.id.list_view);
        mScrollView = findViewById(R.id.h_scrollview);
        mHead = findViewById(R.id.head_layout);
        mHead.setBackgroundColor(getResources().getColor(R.color.red_record_title_tab));
        mHead.setFocusable(true);
        mHead.setClickable(true);
        mHead.setOnTouchListener(new MyTouchLinstener());
        mListView.setOnTouchListener(new MyTouchLinstener());
    }

    private void initData() {
        mDataList = new ArrayList<>();
        TestData data = null;
        for (int i = 1; i < 50; i++) {
            data = new TestData();
            data.setText1("第" + i + "行-1");
            data.setText2("第" + i + "行-2");
            data.setText3("第" + i + "行-3");
            data.setText4("第" + i + "行-4");
            data.setText5("第" + i + "行-5");
            data.setText6("第" + i + "行-6");
            data.setText7("第" + i + "行-7");
            mDataList.add(data);
        }
        mAdapter = new ListViewAdapter(this, mDataList, mHead);
        mListView.setAdapter(mAdapter);
    }

    private float x, y;

    class MyTouchLinstener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            //当在表头和listview控件上touch时，将事件分发给ScrollView
            LogUtil.w("===========" + (v.getId() == R.id.list_view));
            HorizontalScrollView headScrollView = mHead.findViewById(R.id.h_scrollview);
            if (v.getId() == R.id.head_layout) {
                headScrollView.onTouchEvent(event);
                return false;
            }
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    x = event.getX();
                    y = event.getY();
                    headScrollView.onTouchEvent(event);
                    break;
                case MotionEvent.ACTION_MOVE:
                    float xDis = Math.abs(x - event.getX());
                    float yDis = Math.abs(y - event.getY());
                    LogUtil.w(xDis + "======" + yDis);
                    if ((xDis > yDis) && yDis < 50) {
                        headScrollView.onTouchEvent(event);
                        LogUtil.w("======横向滑动========");
                        return true;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    headScrollView.onTouchEvent(event);
                    break;
            }
            return false;
        }
    }

    public void setPosData(int l, int t) {
        this.leftPos = l;
        this.topPos = t;
    }
}
