package com.lixm.animationdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.bean.MeiZi;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @author Lixm
 * @date 2017/8/17
 * @detail
 */

public class GridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    private Context mContext;
    private List<MeiZi> datas;//数据

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null)
            mOnItemClickListener.onItemClick(view);
    }

    public void bindData(List<MeiZi> datas){
        this.datas=datas;
    }

    @Override
    public boolean onLongClick(View view) {
        if (mOnItemClickListener != null)
            mOnItemClickListener.onItemLongClick(view);
        return false;
    }

    //自定义监听事件
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view);

        void onItemLongClick(View view);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public GridAdapter(Context mContext, List<MeiZi> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    @Override
    public int getItemViewType(int position) {
        if (!TextUtils.isEmpty(datas.get(position).getUrl()))
            return 0;
        else
            return 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.grid_meizi_item, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            //给布局设置点击和长点击监听
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
            return holder;
        } else {
            MyViewHolder2 holder2 = new MyViewHolder2(LayoutInflater.from(mContext).inflate(R.layout.page_item, parent, false));
            return holder2;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //将数据与item视图进行绑定，如果是MyViewHolder就加载网络图片，如果是MyViewHolser2就显示网页页数
        if (holder instanceof MyViewHolder) {
            Picasso.with(mContext).load(datas.get(position).getUrl()).into(((MyViewHolder) holder).iv);
        } else if (holder instanceof MyViewHolder2) {
            ((MyViewHolder2) holder).tv.setText(datas.get(position).getPage() + "页");
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

//

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.imageButton);
        }
    }

    class MyViewHolder2 extends RecyclerView.ViewHolder {

        private TextView tv;

        public MyViewHolder2(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.textView2);
        }
    }
}
