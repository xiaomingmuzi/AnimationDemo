package com.lixm.animationdemo.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.method.Touch;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lixm.animationdemo.R;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;

/**
 * Describe:
 * <p>
 * Author: Lixm
 * Date: 2018/7/6
 */
public class ClickTextView extends TextView {
    private Context context;

    public ClickTextView(Context context) {
        this(context, null);
    }

    public ClickTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClickTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public void setText(ArrayList<String> content) {
        String str = content.toString();
        SpannableString spannableString = new SpannableString(str);
        for (int i = 0; i < content.size(); i++) {
            final String temp = content.get(i);
            LogUtil.i("temp："+temp);
            int start = str.indexOf(temp);
            LogUtil.i("start：" + start );
            if (start != -1) {
                final int finalI = i;
                spannableString.setSpan(new ClickableSpan() {
                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setColor(context.getResources().getColor(R.color.live_beauty_progress));
                        ds.setUnderlineText(false);
                    }

                    @Override
                    public void onClick(View widget) {
                        Toast.makeText(context,"点了我："+temp+" 第"+ finalI +"个",Toast.LENGTH_SHORT).show();
                    }
                },start,start+temp.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
//        setMovementMethod(new LinkMovementMethod());
        setText(spannableString);
    }

    @Override
    public boolean isFocused() {
        return true;
    }

    @Override
    public boolean isSelected() {
        return true;
    }
}
