package com.lixm.animationdemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.utils.NativeUtil;

import org.xutils.common.util.LogUtil;

public class JNIDemoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jnidemo);
        findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NativeUtil nativeUtil=new NativeUtil();
                String result=nativeUtil.getStrFromJNI();
                LogUtil.w("result："+result);
                Toast.makeText(JNIDemoActivity.this,result,Toast.LENGTH_SHORT).show();
            }
        });
    }

    static {
        System.loadLibrary("demo");
    }
}
