package com.lixm.animationdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.fragment.TestDialogFragment;

public class DialogFragmentActivity extends FragmentActivity {

    private  TestDialogFragment mdf;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_fragment);
        mdf  = new TestDialogFragment();
        findViewById(R.id.show_dialog_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdf.show(getFragmentManager(),"ff");
            }
        });
    }

}
