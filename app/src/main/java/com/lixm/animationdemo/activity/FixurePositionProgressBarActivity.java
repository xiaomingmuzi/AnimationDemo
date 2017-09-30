package com.lixm.animationdemo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.customview.FixurePositionProgressBar;
import com.lixm.animationdemo.customview.FixureProgressBar;

import org.xutils.common.util.LogUtil;

import static com.lixm.animationdemo.R.id.editText;

public class FixurePositionProgressBarActivity extends AppCompatActivity {
    private FixurePositionProgressBar fixurePositionProgressBar;
    private FixureProgressBar fixureProgressBar;
    private EditText mEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixure_position_progress_bar);
        fixurePositionProgressBar = (FixurePositionProgressBar) findViewById(R.id.fixure_pb);
        fixurePositionProgressBar.setMax(100);

        fixureProgressBar = (FixureProgressBar) findViewById(R.id.fixure_progress_bar);
        fixureProgressBar.setMax(100);

        mEt = (EditText) findViewById(editText);
        mEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {//EditorInfo.IME_ACTION_SEARCH、EditorInfo.IME_ACTION_SEND等分别对应EditText的imeOptions属性
                    //TODO回车键按下时要执行的操作
                    LogUtil.e("==onEditorAction==IME_ACTION_GO=");
                    String content = mEt.getText().toString().trim();
                    if (TextUtils.isEmpty(content)) {
                        fixurePositionProgressBar.setFixurePosition(100);
                        fixureProgressBar.setFixurePoi(100);
                    } else {
                        int poi = Integer.parseInt(content);
                        fixurePositionProgressBar.setFixurePosition(poi);
                        fixureProgressBar.setFixurePoi(poi);
                    }
                    new Thread() {
                        @Override
                        public void run() {
                            int i = 0;
                            while (i <= 100) {
                                Message message = Message.obtain();
                                message.what = 0;
                                message.arg1 = i;
                                handler.sendMessage(message);
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                i++;
                            }
                        }
                    }.start();
                }
                return false;
            }
        });


    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    fixurePositionProgressBar.setProgress(msg.arg1);
                    fixureProgressBar.setProgress(msg.arg1);
                    break;
            }
        }
    };
}
