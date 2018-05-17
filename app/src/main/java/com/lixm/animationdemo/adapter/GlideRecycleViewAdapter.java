package com.lixm.animationdemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lixm.animationdemo.R;
import com.lixm.animationdemo.bean.GlideBean;
import com.lixm.animationdemo.utils.UIUtils;

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
        int width = (UIUtils.getScreenWidth((Activity) context) - 16) / 2;
        int height = width / 2;
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(width, height);
        holder.img.setLayoutParams(params);
        switch (bean.getType()) {
            case 0:
                // 强制静态图
                Glide.with(context)
                        .load("https://upfile2.asqql.com/upfile/2009pasdfasdfic2009s305985-ts/gif_spic/2018-4/20184301815699395.gif")
                        .asBitmap()
                        .placeholder(R.mipmap.place_pic)
                        .error(R.mipmap.failed_pic)
                        .into(holder.img);
                holder.txt.setText("设置静态");
                break;
            case 1:
                // 强制动态图(注意如果不是动态图，会报错，但是添加了error()方法之后可以捕获)
                Glide.with(context)
                        .load("https://upfile2.asqql.com/upfile/2009pasdfasdfic2009s305985-ts/gif_spic/2018-4/20184301815699395.gif")
                        .asGif()
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .placeholder(R.mipmap.place_pic)
                        .error(R.mipmap.failed_pic)
                        .into(holder.img);
                holder.txt.setText("设置动态");
                break;
            case 2://加载资源文件
                Glide.with(context).load(R.mipmap.college_course_cover_iv_bg).into(holder.img);
                holder.txt.setText("加载资源文件");
                break;
            case 3://缓存各种图片
                Glide.with(context)
                        .load("https://goss.vcg.com/73b5fe60-4156-11e8-9857-f1a3765f57f3.jpg")
                        .placeholder(R.mipmap.place_pic)
                        .error(R.mipmap.failed_pic)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.img);
                holder.txt.setText("缓存文件到SD卡");
                break;
            case 4://设定图片大小加载
                Glide.with(context)
                        .load("https://goss.vcg.com/73b5fe60-4156-11e8-9857-f1a3765f57f3.jpg")
                        .placeholder(R.mipmap.place_pic)
                        .error(R.mipmap.failed_pic)
                        .override(200, 200)
                        .into(holder.img);
                holder.txt.setText("设定图片大小");
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
