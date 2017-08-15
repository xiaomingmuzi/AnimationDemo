package com.lixm.animationdemo.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.dialog.LiveRedRecordDialog;
import com.lixm.animationdemo.utils.Contants;

public class DialogActivity extends FragmentActivity {

    private LiveRedRecordDialog mRedRecordDialog;
    private ConstraintLayout mRoot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        Contants.manager=getSupportFragmentManager();
        mRoot= (ConstraintLayout) findViewById(R.id.root);
        findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRedRecordDialog==null)
                    mRedRecordDialog=new LiveRedRecordDialog(DialogActivity.this);
                mRedRecordDialog.show();
//                mRedRecordDialog.showAtLocation(mRoot, Gravity.CENTER,0,0);
//                startActivity(new Intent(DialogActivity.this,LiveRedRecordDialog.class));
            }
        });
    }
}
