package com.lixm.animationdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.customview.CustomPayEditText;
import com.lixm.animationdemo.dialog.PayDialog;

public class PayPassportActivity extends AppCompatActivity {

    private String TAG="PayPassportActivity";
    private CustomPayEditText mCustomPayEditText;
    private CustomPayEditText CustomPayEditText2;

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_passport);
        mCustomPayEditText = (CustomPayEditText) findViewById(R.id.ppe_pwd);
        CustomPayEditText2 = (CustomPayEditText) findViewById(R.id.ppe_pwd2);
        button = (Button) findViewById(R.id.btn_dialog);

        mCustomPayEditText.initStyle(R.drawable.edit_num_bg, 6, 0.33f, R.color.color_333, R.color.aaaaa, 20);
        mCustomPayEditText.setOnTextFinishListener(new CustomPayEditText.OnTextFinishListener() {
            @Override
            public void onFinish(String str) {//密码输入完后的回调
                Toast.makeText(PayPassportActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });

        CustomPayEditText2.initStyle(R.drawable.edit_num_bg_red, 8, 0.33f, R.color.colorAccent, R.color.colorAccent, 20);
        CustomPayEditText2.setOnTextFinishListener(new CustomPayEditText.OnTextFinishListener() {
            @Override
            public void onFinish(String str) {
                Toast.makeText(PayPassportActivity.this, "显示明文：" + str, Toast.LENGTH_SHORT).show();
                CustomPayEditText2.setShowPwd(false);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayDialog payDialog = new PayDialog(PayPassportActivity.this);
                payDialog.show();
            }
        });

    }
}
