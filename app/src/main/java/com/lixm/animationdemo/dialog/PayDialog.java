package com.lixm.animationdemo.dialog;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.customview.CustomPayEditText;



/**
 * Created by ywl on 2017/2/28.
 */

public class PayDialog extends BaseDialog{

    private CustomPayEditText CustomPayEditText;

    public PayDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_dialog_lyaout);
        CustomPayEditText = (CustomPayEditText) findViewById(R.id.ppet);

        CustomPayEditText.initStyle(R.drawable.edit_num_bg_red, 6, 0.33f, R.color.colorAccent, R.color.colorAccent, 20);
        CustomPayEditText.setOnTextFinishListener(new CustomPayEditText.OnTextFinishListener() {
            @Override
            public void onFinish(String str) {//密码输入完后的回调
                Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                CustomPayEditText.setFocus();
            }
        }, 100);

    }
}
