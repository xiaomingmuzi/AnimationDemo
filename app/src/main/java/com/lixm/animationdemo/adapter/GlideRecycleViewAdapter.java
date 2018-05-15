package com.lixm.animationdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lixm.animationdemo.R;
import com.lixm.animationdemo.bean.GlideBean;

import java.util.ArrayList;

/**
 * @author Lixm
 * @date 2018/5/15
 * @detail
 */

public class GlideRecycleViewAdapter extends RecyclerView.Adapter<GlideRecycleViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<GlideBean> mDatas;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.glide_item,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GlideBean bean=mDatas.get(position);
        switch (bean.getType()){
            case 0:
                Glide.with(context).load(bean.getUrl()).error(R.mipmap.failed_pic).placeholder(R.mipmap.place_pic).into(holder.img);
                break;

        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView img;
        private TextView txt;

        public ViewHolder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.glide_img);
            txt=itemView.findViewById(R.id.glide_txt);
        }
    }
}
