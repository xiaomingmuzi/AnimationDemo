package com.lixm.liveplayerlibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author Lixm
 * @date 2017/8/28
 * @detail
 */

public class ProgressAdjustPanel extends LinearLayout{
    private Context context;
    private LinearLayout mProgressTxtLayout;
    private NumberProgressBar numberProgressBar;
    private TextView mCurrentTime,mTotalTime;
    private ImageView mProgressImg;
    public ProgressAdjustPanel(Context context) {
        super(context);
        this.context=context;
        initView();
    }

    private void initView(){
        LayoutInflater.from(context).inflate(R.layout.adjust_panel_layout, this, true);
        mCurrentTime=findViewById(R.id.current);
        mTotalTime=findViewById(R.id.total);
        mProgressImg=findViewById(R.id.progress_img);
        numberProgressBar=findViewById(R.id.number_progress_bar);
        numberProgressBar.setVisibility(GONE);
        mProgressTxtLayout=findViewById(R.id.progress_txt_layout);
        mProgressTxtLayout.setVisibility(VISIBLE);
    }

    public void hidePanel(){
        setVisibility(GONE);


    }

    public void adjustForward(int cur,int total){
        setVisibility(VISIBLE);
       mProgressImg.setBackgroundResource(R.mipmap.back);
        mCurrentTime.setText(TimeUtil.secondToString(cur));
        mTotalTime.setText(TimeUtil.secondToString(total));
    }

    public void adjustBackward(int cur,int total){
        setVisibility(VISIBLE);
        mProgressImg.setBackgroundResource(R.mipmap.pre);
        mCurrentTime.setText(TimeUtil.secondToString(cur));
        mTotalTime.setText(TimeUtil.secondToString(total));
    }
}
