package com.lixm.animationdemo.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lixm.animationdemo.R;

/**
 * @author Lixm
 * @date 2018/3/5
 * @detail
 */

public class TestDialogFragment extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.test_dialog_fragment_layout,container,false);
        view.findViewById(R.id.show_toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"toast",Toast.LENGTH_SHORT).show();
                TestDialogFragment.this.dismiss();
            }
        });
        return view;
    }
}
