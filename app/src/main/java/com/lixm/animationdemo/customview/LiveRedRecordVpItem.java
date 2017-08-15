package com.lixm.animationdemo.customview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.adapter.LiveRedRecordAdapter;
import com.lixm.animationdemo.bean.RedRecordBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author Lixm
 * @date 2017/7/13
 * @detail
 */

public class LiveRedRecordVpItem extends LinearLayout {

    private Context mContext;
    private LinearLayout noRed;
    private ListView mRedList;
    private int type = 0;//默认显示我发出的界面
    private LiveRedRecordAdapter mAdapter;
    private ArrayList<RedRecordBean> merLists;
    private int Page = 1;
    private boolean flag = true;//是否还有更多内容

    public LiveRedRecordVpItem(Context context) {
        super(context);
        mContext = context;
        init();
    }

    private void init() {
        LayoutInflater.from(mContext).inflate(R.layout.red_record_fragment_list, this);
        noRed = (LinearLayout) findViewById(R.id.no_red);
        mRedList = (ListView) findViewById(R.id.list_red_record);
        mRedList.setHeaderDividersEnabled(false);
        merLists = new ArrayList<>();
    }

    public void setData(int type) {
        this.type = type;
        mAdapter = new LiveRedRecordAdapter(mContext, merLists, type);
        mRedList.setAdapter(mAdapter);
        new Thread(){
            @Override
            public void run() {
                getData();
            }
        }.start();
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                        dealData(msg.obj.toString());
                    break;
            }
        }
    };

    private void getData() {
        try {
            OkHttpClient client = new OkHttpClient();
//        http://financial.test.cnfol.com/index.php?r=RedPacket/GetMyReceiveRedPacketList
//        Param ==》&userId=348392&CheckCode=355848063470929&page=1&type=1&pageSize=20
            String url = "";
            if (type == 0) {
                url = "http://financial.test.cnfol.com/index.php?r=RedPacket/GetMySendRedPacketList";
            } else {
                url = "http://financial.test.cnfol.com/index.php?r=RedPacket/GetMyReceiveRedPacketList";
            }
            RequestBody formBody = new FormBody.Builder()
                    .add("userId", "348392")
                    .add("CheckCode", "355848063470929")
                    .add("page", "1")
                    .add("type", type + "")
                    .add("pageSize", "20")
                    .build();
            Request request = new Request.Builder().url(url)
                    .post(formBody).build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String result = response.body().string();
                Log.i("DialogActivity",result);
                Message message=Message.obtain();
                message.what=0;
                message.obj=result;
                handler.sendMessage(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void dealData(String result)  {
        try{
            Log.e("dealData",result);
        JSONObject jsonObject = new JSONObject(result);
        if (jsonObject.optInt("result") == 10000) {
            JSONArray jsonArray = jsonObject.optJSONArray("MerList");
            if (Page == 1) {
                merLists.clear();
                if (jsonArray.length() == 0) {
                    noRed.setVisibility(VISIBLE);
                    mRedList.setVisibility(GONE);
                    return;
                } else {
                    noRed.setVisibility(GONE);
                    mRedList.setVisibility(VISIBLE);
                }
            }
            for (int i = 0; i < jsonArray.length(); i++) {
                RedRecordBean redRecordBean = new RedRecordBean();
                JSONObject jso = jsonArray.optJSONObject(i);
                redRecordBean.setSysRedID(jso.optString("SysRedID"));
                redRecordBean.setRedPacketType(jso.optString("RedPacketType"));
                if (type == 0) {//我发出的列表
                    redRecordBean.setRedNumber(jso.optString("RedNumber"));
                    redRecordBean.setStockRedPck(jso.optString("StockRedPck"));
                    redRecordBean.setDataTime(jso.optString("DataTime"));
                    redRecordBean.setRedBonus(jso.optString("RedTBonus"));
                } else {//我收到的列表
                    redRecordBean.setGetTime(jso.optString("GetTime"));
                    redRecordBean.setSendUser(jso.optString("SendUser"));
                    redRecordBean.setRedBonus(jso.optString("RedBonus"));
                }
                merLists.add(redRecordBean);
            }
            mAdapter.bindData(merLists);
            mAdapter.notifyDataSetChanged();
        } else
            Toast.makeText(mContext, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();}catch (Exception e){
            e.printStackTrace();
        }
    }
}
