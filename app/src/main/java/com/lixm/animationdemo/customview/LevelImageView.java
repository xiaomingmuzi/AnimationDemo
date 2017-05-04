package com.lixm.animationdemo.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author Lixm
 * @date 2017/5/4
 * @detail
 */

public class LevelImageView extends android.support.v7.widget.AppCompatImageView {
    public LevelImageView(Context context) {
        super(context);
    }

    public LevelImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private int imageLevel = 0;

    public void setImageLevel(int level) {
        if (this.imageLevel == level)
            return;
        super.setImageLevel(level);
        this.imageLevel = level;
    }

    public int getImageLevel() {
        return imageLevel;
    }

    // 下一level接口。
    public void nextLevel() {
        setImageLevel(imageLevel++ % maxLevel);
    }

    private int maxLevel = 10;

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }
}