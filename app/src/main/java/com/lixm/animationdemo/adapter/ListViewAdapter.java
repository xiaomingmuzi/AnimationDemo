package com.lixm.animationdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.activity.FullScrollLayoutActivity;
import com.lixm.animationdemo.bean.TestData;
import com.lixm.animationdemo.customview.CustomHScrollView;

import java.util.List;

/**
 * @author Lixm
 * @date 2017/11/30
 * @detail
 */

public class ListViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<TestData> mList;
    private LayoutInflater mInflater;
    private RelativeLayout mHead;
    private int n = 1;//标记变量

    public ListViewAdapter(Context mContext, List<TestData> mList, RelativeLayout mHead) {
        this.mContext = mContext;
        this.mList = mList;
        this.mHead = mHead;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    MyViewHolder holder;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, parent, false);
            holder = new MyViewHolder();
            CustomHScrollView scrollView=convertView.findViewById(R.id.h_scrollview);
            holder.scrollView = scrollView;
            holder.mTextView1 = convertView.findViewById(R.id.textView_1);
            holder.mTextView2 = convertView.findViewById(R.id.textView_2);
            holder.mTextView3 = convertView.findViewById(R.id.textView_3);
            holder.mTextView4 = convertView.findViewById(R.id.textView_4);
            holder.mTextView5 = convertView.findViewById(R.id.textView_5);
            holder.mTextView6 = convertView.findViewById(R.id.textView_6);
            holder.mTextView7 = convertView.findViewById(R.id.textView_7);

            CustomHScrollView headScrollView=mHead.findViewById(R.id.h_scrollview);

            headScrollView.AddOnScrollChangedListener(new OnScrollChangedListenerImp(scrollView));
        }
        return convertView;
    }

    class OnScrollChangedListenerImp implements CustomHScrollView.OnScrollChangedListener {
        CustomHScrollView mScrollViewArg;

        public OnScrollChangedListenerImp(CustomHScrollView mScrollViewArg) {
            this.mScrollViewArg = mScrollViewArg;
        }

        @Override
        public void onScrollChanged(int l, int t, int oldl, int oldt) {
            mScrollViewArg.smoothScrollTo(l,t);
            if (n==1){//记录滚动的起始位置，避免因刷新数据引起错乱
                new FullScrollLayoutActivity().setPosData(oldl,oldt);
            }
            n++;
        }
    }

    class MyViewHolder {
        TextView mTextView1;
        TextView mTextView2;
        TextView mTextView3;
        TextView mTextView4;
        TextView mTextView5;
        TextView mTextView6;
        TextView mTextView7;
        HorizontalScrollView scrollView;
    }
}
