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

    public GlideRecycleViewAdapter(Context context, ArrayList<GlideBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.glide_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GlideBean bean = mDatas.get(position);
        switch (bean.getType()) {
            case 0:
                // 强制静态图
                Glide.with(context)
                        .load(bean.getUrl())
                        .asBitmap()
                        .placeholder(R.mipmap.place_pic)
                        .error(R.mipmap.failed_pic)
                        .into(holder.img);
                holder.txt.setText("设置静态");
                break;
            case 1:
                // 强制动态图(注意如果不是动态图，会报错，但是添加了error()方法之后可以捕获)
                Glide.with(context)
                        .load(bean.getUrl())
                        .asGif()
                        .placeholder(R.mipmap.place_pic)
                        .error(R.mipmap.failed_pic)
                        .into(holder.img);
                holder.txt.setText("设置动态");
                break;
                default:
                    Glide.with(context)
                            .load(bean.getUrl())
                            .error(R.mipmap.failed_pic)
                            .placeholder(R.mipmap.place_pic)
                            .into(holder.img);
                    holder.txt.setText("普通显示");
                    break;
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView txt;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.glide_img);
            txt = itemView.findViewById(R.id.glide_txt);
        }
    }
}
