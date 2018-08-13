package com.lixm.animationdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Describe:
 * <p>
 * Author: Lixm
 * Date: 2018/8/9
 */
public class NormalFragment extends Fragment {

    private String tag="Normal";

    @Override
    public void setArguments(Bundle args) {
        tag=args.getString("TAG");
        super.setArguments(args);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView=new TextView(getActivity());
        textView.setText(tag);
        return textView;
    }
}
