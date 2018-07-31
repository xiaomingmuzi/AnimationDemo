package com.lixm.animationdemo.activity;

import android.os.Bundle;
import android.widget.GridView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lixm.animationdemo.R;
import com.lixm.animationdemo.adapter.LiveGiftGridAdapter;
import com.lixm.animationdemo.bean.LiveGiftBean;
import com.lixm.animationdemo.listener.PostReceiverDataListener;

import org.xutils.common.util.LogUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GiftAnimationActivity extends BaseActivity implements PostReceiverDataListener {

    @BindView(R.id.gv)
    GridView gv;
    private LiveGiftGridAdapter mAdapter;
    private ArrayList<LiveGiftBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_animation);
        ButterKnife.bind(this);
        mList = new ArrayList<>();
        String content = "[{\"ProductID\":\"318425\",\"ProductSeq\":\"2\",\"ProductName\":\"\\u638c\\u58f0\",\"ProductPrice\":\"10\",\"GiftName\":\"\\u638c\\u58f0\"},{\"ProductID\":\"318426\",\"ProductSeq\":\"3\",\"ProductName\":\"\\u6709\\u624d\",\"ProductPrice\":\"10\",\"GiftName\":\"\\u6709\\u624d\"}," +
                "{\"ProductID\":\"318427\",\"ProductSeq\":\"4\",\"ProductName\":\"\\u9c9c\\u82b1\",\"ProductPrice\":\"20\",\"GiftName\":\"\\u9c9c\\u82b1\"},{\"ProductID\":\"318428\",\"ProductSeq\":\"5\",\"ProductName\":\"\\u5e72\\u676f\",\"ProductPrice\":\"20\",\"GiftName\":\"\\u5e72\\u676f\"}," +
                "{\"ProductID\":\"318429\",\"ProductSeq\":\"6\",\"ProductName\":\"\\u4e48\\u4e48\\u54d2\",\"ProductPrice\":\"100\",\"GiftName\":\"\\u4e48\\u4e48\\u54d2\"},{\"ProductID\":\"318430\",\"ProductSeq\":\"7\",\"ProductName\":\"\\u725b\",\"ProductPrice\":\"100\",\"GiftName\":\"\\u725b\"}," +
                "{\"ProductID\":\"318431\",\"ProductSeq\":\"8\",\"ProductName\":\"666\",\"ProductPrice\":\"660\",\"GiftName\":\"666\"},{\"ProductID\":\"318432\",\"ProductSeq\":\"9\",\"ProductName\":\"\\u5f31\",\"ProductPrice\":\"800\",\"GiftName\":\"\\u5f31\"}," +
                "{\"ProductID\":\"318433\",\"ProductSeq\":\"10\",\"ProductName\":\"\\u81ed\\u9e21\\u86cb\",\"ProductPrice\":\"1800\",\"GiftName\":\"\\u81ed\\u9e21\\u86cb\"},{\"ProductID\":\"318434\",\"ProductSeq\":\"11\",\"ProductName\":\"\\u6da8\\u505c\",\"ProductPrice\":\"8800\",\"GiftName\":\"\\u6da8\\u505c\"}," +
                "{\"ProductID\":\"318435\",\"ProductSeq\":\"12\",\"ProductName\":\"\\u91d1\\u7816\",\"ProductPrice\":\"18800\",\"GiftName\":\"\\u91d1\\u7816\"}]";
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<LiveGiftBean>>() {
        }.getType();
        ArrayList<LiveGiftBean> datas = gson.fromJson(content, listType);
        for (LiveGiftBean liveGiftBean : datas) {
            LogUtil.i(liveGiftBean.toString());
        }
        mList.addAll(datas);
        mAdapter = new LiveGiftGridAdapter(this, mList, 1, this, 0);
        gv.setAdapter(mAdapter);
    }

    @Override
    public void postReceiverData(int flag, int action, Object dataObj) {

    }
}
