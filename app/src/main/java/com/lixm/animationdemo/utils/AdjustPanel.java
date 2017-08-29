package com.lixm.animationdemo.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.customview.NumberProgressBar;

/**
 * @author Lixm
 * @date 2017/8/28
 * @detail
 */

public class AdjustPanel extends LinearLayout {
    private Context context;
    private NumberProgressBar mNumberProgressBar;
    private ImageView mProgressImg;
    private LinearLayout mProgressTxtLayout;

    public AdjustPanel(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(context).inflate(R.layout.adjust_panel_layout, this, true);
        mNumberProgressBar = findViewById(R.id.number_progress_bar);
        mNumberProgressBar.setVisibility(VISIBLE);
        mNumberProgressBar.setMax(100);
        mProgressImg = findViewById(R.id.progress_img);
        mProgressTxtLayout = findViewById(R.id.progress_txt_layout);
        mProgressTxtLayout.setVisibility(GONE);

    }

    public void hidePanel() {
        setVisibility(GONE);
    }

    public void adjustBrightness(float brightness) {
        setVisibility(VISIBLE);
        mProgressImg.setBackgroundResource(R.mipmap.bright);
        mNumberProgressBar.setProgress((int) (brightness * 100));
    }

    public void adjustVolume(float volumePercent) {
        setVisibility(VISIBLE);
        mNumberProgressBar.setProgress((int) (volumePercent * 100));
        if (volumePercent == 0)
            mProgressImg.setBackgroundResource(R.mipmap.no_sound);
        else mProgressImg.setBackgroundResource(R.mipmap.sound);
    }
}

