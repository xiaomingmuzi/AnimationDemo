package com.lixm.animationdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.bean.RedRecordBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lixm
 * @date 2017/7/12
 * @detail 视频直播室天降红包记录适配器
 */

public class LiveRedRecordAdapter extends BaseAdapter {
    private Context mContext;
    private int type = 0;//0 我发出的，1我收到的
    private ArrayList<RedRecordBean> datas;

    public LiveRedRecordAdapter(Context context, List<? extends Object> datas, int type) {
        mContext = context;
        this.datas = (ArrayList<RedRecordBean>) datas;
        this.type = type;
    }

    public void bindData(ArrayList<RedRecordBean> list) {
        this.datas = list;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    ViewHolder holder;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.live_red_record_item, null);
            holder = new ViewHolder();
            holder.recordName = (TextView) convertView.findViewById(R.id.record_name);
            holder.recordType = (TextView) convertView.findViewById(R.id.red_type);
            holder.recordMoney = (TextView) convertView.findViewById(R.id.record_money);
            holder.recordTime = (TextView) convertView.findViewById(R.id.record_time);
            holder.recordStatus = (TextView) convertView.findViewById(R.id.record_status);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final RedRecordBean redRecordBean = datas.get(position);
        Double dou = Double.parseDouble(redRecordBean.getRedBonus()) / 100;
        holder.recordMoney.setText(dou + "元");
        try {
            if (type == 0) {//我发出的
                holder.recordName.setText("天降红包");
                holder.recordType.setVisibility(View.GONE);
                holder.recordTime.setText(redRecordBean.getDataTime().split(" ")[0]);
                holder.recordStatus.setVisibility(View.VISIBLE);
                int stockRedPck = Integer.parseInt(redRecordBean.getStockRedPck());
                int totalPck = Integer.parseInt(redRecordBean.getRedNumber());
                if (stockRedPck == 0) {//已领完
                    holder.recordStatus.setText("已领完" + totalPck + "/" + totalPck + "个");
                } else
                    holder.recordStatus.setText("未领完" + (totalPck - stockRedPck) + "/" + totalPck + "个");
            } else {
                holder.recordName.setText(redRecordBean.getSendUser());
                holder.recordType.setVisibility(View.VISIBLE);
                holder.recordTime.setText(redRecordBean.getGetTime().split(" ")[0]);
                holder.recordStatus.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "查看详情！！！", Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    class ViewHolder {
        public TextView recordName;
        public TextView recordType;
        public TextView recordMoney;
        public TextView recordTime;
        public TextView recordStatus;
    }
}
